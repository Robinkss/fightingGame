package org.acme.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.smallrye.mutiny.Multi;
import jakarta.json.Json;
import org.acme.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MqttServiceTest {

    private MqttService mqttService;
    private Game game;

    private String json;

    @BeforeEach
    void setUp() {
        Game.resetInstance();
        mqttService = new MqttService();
        game = Game.getInstance();
        json =
                "{" +
                        "\"gameOver\":" + game.gameOver() + "," +
                        "\"DEFAULT_HP\":" + Game.DEFAULT_HP + "," +
                        "\"DEFAULT_ATTACK_DAMAGE\":" + Game.DEFAULT_ATTACK_DAMAGE + "," +
                        "\"fighter1\":{" +
                                "\"name\":\"" + game.getFighters().x().getName() + "\"," +
                                "\"hp\":" + game.getFighters().x().getHp() + "," +
                                "\"pos\":" + game.getFighters().x().getPos() + "," +
                                "\"direction\":" + game.getFighters().x().isDirection() + "," +
                                "\"status\":\"" + game.getFighters().x().getStatus() + "\"" +
                        "}," +
                                "\"fighter2\":{" +
                                "\"name\":\"" + game.getFighters().y().getName() + "\"," +
                                "\"hp\":" + game.getFighters().y().getHp() + "," +
                                "\"pos\":" + game.getFighters().y().getPos() + "," +
                                "\"direction\":" + game.getFighters().y().isDirection() + "," +
                                "\"status\":\"" + game.getFighters().y().getStatus() + "\"" +
                        "}" +
                "}";
    }

    @Test
    void generate() {
        Multi<String> result = mqttService.generate();

        assertEquals(result.collect().first().await().indefinitely(), json);
    }

    @Test
    void testGetGame() {
        assertEquals(mqttService.getGame(), json);
    }

}
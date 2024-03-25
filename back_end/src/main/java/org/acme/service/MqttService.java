package org.acme.service;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Multi;
import java.time.Duration;

import org.acme.model.Game;

@ApplicationScoped
public class MqttService{

    @Outgoing("fuckQuarkus")
    public Multi<String> generate() {
        return Multi
                .createFrom()
                .ticks()
                .every(Duration.ofMillis(250))
                .map(
                        tick -> getGame()
                );
    }

    public String getGame() {
        return Game.getInstance()
                .toJson()
                .toString();
    }

}
import React, { useState, useEffect} from "react";
import mqtt from "mqtt";
import { Stage, Sprite, Text } from "@pixi/react";
import axios from "axios";
import Fighter from "./Fighter";

const Game = () => {

  const BACK_URL = "http://localhost:8080";
  const TOPIC = "fuckQuarkus";
  const BACKGROUND_IMAGE = "Free Pixel Art Forest/Preview/Background.png"

  /* useState imported by mqtt */
  const [gameOver, setGameOver] = useState(false);
  const [defaultHP, setDefaultHP] = useState(0);
  const [defaultAttackDamage, setDefaultAttackDamage] = useState(0);
  const [fighter1, setFighter1] = useState(null);
  const [fighter2, setFighter2] = useState(null);

  /* useState */
  const [player, setPlayer] = useState(true);

  useEffect(() => {
    const client = mqtt.connect("ws://mqtt.eclipseprojects.io/mqtt:1883");
    console.log(defaultAttackDamage);
    client.on("connect", () => {
      client.subscribe(TOPIC);
    });

    client.on("message", (_, message) => {
      const data = JSON.parse(message.toString());

      setGameOver(data.gameOver);
      setDefaultHP(data.DEFAULT_HP);
      setDefaultAttackDamage(data.DEFAULT_ATTACK_DAMAGE);
      setFighter1(data.fighter1);
      setFighter2(data.fighter2);

    });

  }, []);

  useEffect(() => {

    const handleKey = (event) => {

      switch (event.key) {
        case "d":
          axios.post(`${BACK_URL}/fighter/${player ? '0' : '1'}/moveRight`)
          .catch(error => {
            console.error(error);
          });
          break;
        case "q":
          axios.post(`${BACK_URL}/fighter/${player ? '0' : '1'}/moveLeft`)
          .catch(error => {
            console.error(error);
          });
          break;
        case "s":
          axios.post(`${BACK_URL}/fighter/${player ? '0' : '1'}/attack`)
          .catch(error => {
            console.error(error);
          });
          break;
        case "z":
          axios.post(`${BACK_URL}/fighter/${player ? '0' : '1'}/defend`)
          .catch(error => {
            console.error(error);
          });
          break;
      }
  
    };

    window.addEventListener("keydown", handleKey);

    // Nettoyer l'écouteur d'événements lorsque le composant est démonté
    return () => {
      window.removeEventListener("keydown", handleKey);
    };

  }, [player]);

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100vh", width: "100vw", overflow: "hidden" }}>
      <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }}>
        <h1 style={{ marginRight: "50px" }}>Our fighting game</h1>
        <button onClick={() => setPlayer((prev) => !prev)} style={{ backgroundColor: player ? "blue" : "red", marginRight: "50px" }}>
          {player ? "J1" : "J2"}
        </button>
        <button onClick={() => axios.post(`${BACK_URL}/game/restart`)}>Restart</button>
      </div>
      <div style={{ display: "flex", flexDirection: "row", alignItems: "center", marginBottom: "10px" }}>
      <Stage>
        <Sprite image={BACKGROUND_IMAGE} x={0} y={-180} />
        { gameOver &&
          <Text text={fighter1 ? "Player 2 wins" : "Player 1 wins"} style={{ fontSize: 64, fill: 'white', align: 'center' }} x={400} y={200} anchor={0.5} />
        }
        { fighter1 && <Fighter fighter={fighter1} DEFAULT_HP={defaultHP} numJ={0} /> }
        { fighter2 && <Fighter fighter={fighter2} DEFAULT_HP={defaultHP} numJ={1} /> }
      </Stage>
      </div>
    </div>
  );
};

export default Game;

# FRONT

The framework used is React with Vite.
It also uses PixiJS.

# BACK

The framework used is Quarkus.
With the following dependencies:
- Mosquitto
- Lombok
- ...

# COMMUNICATION

The communication between the front and the back is done through HTTP requests.
And the communication between the back and the MQTT broker is done through the MQTT protocol.

# DEPLOYMENT

The front is deployed on a static server.
The back is deployed on a server with kubernetes.

# CI

The CI is done with GitLab CI.

# COMPILER

This is a simple grammar that recognizes a sequence of integers on a line, separated by commas. The integers can be positive or negative.
The compiler can be run with the following command in the appropriate directory:

```shell script
./compile.sh
```

The result is a jar file with the sum of each line of integers in the file with the extension **".sum"**.

# AUTHORS

- Léon Boudier
- Robin Aveline 
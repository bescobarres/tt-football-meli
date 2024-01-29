# Prueba Técnica MELI - Equipo de Fútbol

Un equipo de fútbol  busca calcular 
los jugadores principales del equipo, con la posibilidad 
de parametrizar la cantidad de jugadores deseados. La condición es que estos jugadores cumplan con los requisitos básicos, 
que incluyen tener al menos 3 entrenamientos por semana.


## Diseño y estructura de los paquetes del Proyecto

- `/domain`: Contiene las clases relacionadas con el dominio del proyecto.
- `/application`: Contiene las clases de aplicación que interactúan con el dominio y la logica de negocio.
- `/infrastructure`: Contiene clases relacionadas con la infraestructura, como la comunicación con la base de datos y los endpoints expuestos.


## Como ejecutarlo

1. **Clonar el Repositorio:**
   ```bash
   git clone https://github.com/bescobarres/tt-football-meli.git
   cd tt-football-meli
   
2. **Ejecutar proyecto:**
   ```bash
   ./gradlew clean build
   java -jar build/libs/football-0.0.1-SNAPSHOT.jar


3. **Acceder a la Consola de Desarrollo H2:**
   - [Dev Console H2](http://localhost:8080/h2-console)
   - **JDBC URL:** `jdbc:h2:mem:footballdb`
   - **Usuario:** `sa`
   - **Contraseña:** _(sin contraseña)_

4. **Acceder a la Documentación Técnica Swagger UI:**
   - [Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Uso del API

5. **Registrar Jugadores y Entrenamientos**

- **Endpoint:** `POST /training`
- **Ejemplo de solicitud con curl:**
  ```bash
  curl -X 'POST' \
    'http://localhost:8080/training' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "playerId": 1,
      "name": "string",
      "power": 100,
      "distance": 0.1,
      "time": 1,
      "passes": 100,
      "date": "2024-01-28"
    }'

- **Endpoint:** `GET /team`
- **Ejemplo de solicitud con curl:**
-  Este endpoint puede recibir opcionalmente 2 parametros 'http://localhost:8080/team?startingLineUpQuantity=8&day=2024-01-26'
- **startingLineUpQuantity** Recibe un numero entero que parametriza la cantidad de equipo principal que desea calcular
- **day** Recibe una fecha de la semana que queremos calcular el equipo principal
  ```bash
  curl -X 'GET' \
    'http://localhost:8080/team' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \


## Detalles Adicionales

- La aplicación fue desarrollada con Java 17 y Spring Boot 3.2.2.
- Se utilizan dos tablas en la base de datos para cumplir con los requisitos de la aplicación.
- Se implementaron librerías como MapStruct para mapear datos entre DTO y entidades, Lombok para reducir boilerplate, y OpenAPI para generar la documentación Swagger UI.
- Las pruebas unitarias se realizaron con JUnit 5, alcanzando una cobertura del 86%.
- El proyecto utiliza Gradle como sistema de construcción.

...





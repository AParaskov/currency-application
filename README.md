# Currency Rate Downloader Application

### Overview:
The purpose of this application is to download currency rates from "bnb.bg" using the Bulgarian Lev as base currency.
It should persist the currencies in a relational database and after a successful save, notify and send the persisted data to a consumer service through WebSocket.
The consumer service should receive the data and save it in its own database.

### Instructions to start the application:
 - You will need a PostgreSQL Server in order to access the database.
 - By using the "docker compose up -d" command in the terminal from the project a postgresql server should start in a docker container and create both the needed databases automatically.
 - First you should start the currency-rate-downloader service since it is a WebSocket Server as well.
 - After that you should start the currency-rate-consumer service which serves as a WebSocket client and will automatically connect to the currency-rate-downloader service upon startup.

### Endpoint Request:
curl --location 'http://localhost:8090/download-currencies' \
--data ''









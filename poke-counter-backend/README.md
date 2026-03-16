```
docker run --name poke-counter -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
docker exec -it poke-counter bash 
psql -U postgres
CREATE USER pokecounter WITH PASSWORD 'pokecounter'; CREATE DATABASE pokecounter OWNER pokecounter;
```
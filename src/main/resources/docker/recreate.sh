docker stop postgres-bank

docker rm postgres-bank

docker run -d --name postgres-bank -p 10432:5432 postgres:12.0

docker start postgres-bank

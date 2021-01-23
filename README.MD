# MY_RESTAURANT 

## CMD

```bash
./mvnw spring-boot:run
```

## Docker build
```bash
docker build -f docker/Dockerfile -t eu.gcr.io/deploy-276111/myrestaurant:v1 .
```

## Deploy

```
docker push eu.gcr.io/deploy-276111/myrestaurant:v1
```

## Docker run

```bash
docker run -e SPRING_DATASOURCE_URL=jdbc:mysql://root:Obrigada@docker.for.mac.localhost:3306/my_restaurant -p 8080:8080 test
```

## how to connect to gcloud sql

https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory
# java-patient-api

docker-compose -f docker-compose.dev.yml up --build

# test

docker build -t ahebibovic/transferhfir --target test .

docker run -it --rm --name springboot-test ahebibovic/transferhfir ./mvnw test
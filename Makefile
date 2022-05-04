sudo: required

test:
	@ ./mvnw test

package:
	@ ./mvnw clean package -DskipTests

docker-image-build: package
	@ docker build -t productcatalog-api .

deploy: docker-image-build
	@ HEROKU_API_KEY=${HEROKU_API_KEY} heroku run ls --app productcatalog-api
	@ heroku login
	@ heroku container:login
	@ heroku container:push web -a productcatalog-api
	@ heroku container:release web -a productcatalog-api

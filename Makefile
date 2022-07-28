start:
	source .env && ./gradlew bootrun

test:
	source .env && /usr/bin/time ./gradlew clean build

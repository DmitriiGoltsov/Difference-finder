.DEFAULT_GOAL := build-run

PATH1=src/test/resources/file1.json
PATH2=src/test/resources/file2.json
PATH3=src/test/resources/file3.json

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app $(PATH1) $(PATH2)

run-distW:
	./build/install/app/bin/app $(PATH1) $(PATH3)

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build
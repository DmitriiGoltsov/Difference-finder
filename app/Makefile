.DEFAULT_GOAL := build-run

PATH1=src/test/resources/file1.json
PATH2=src/test/resources/file2.json
PATH3=src/test/resources/file3.json
YML1=src/test/resources/file1.yml
YML2=src/test/resources/file2.yml
YML3=src/test/resources/file3.yml
NEST1=src/test/resources/nested_file1.json
NEST2=src/test/resources/nested_file2.json
NEST3=src/test/resources/nested_file1.yml
NEST4=src/test/resources/nested_file2.yml

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app $(PATH1) $(PATH2)

run-dist-wrong:
	./build/install/app/bin/app $(PATH1) $(PATH3)

run-dist-yml:
	./build/install/app/bin/app $(YML1) $(YML2)

run-dist-yml-wrong:
	./build/install/app/bin/app $(YML1) $(YML3)

run-dist-nest:
	./build/install/app/bin/app $(NEST1) $(NEST2)

run-dist-nest-yml:
	./build/install/app/bin/app $(NEST3) $(NEST4)

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
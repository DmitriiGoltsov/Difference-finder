PATH1=app/src/test/resources/file1.json
PATH2=app/src/test/resources/file2.json

run-dist:
	app/build/install/app/bin/app

install:
	app/build/install/app/bin/app $(PATH1) $(PATH2)

help:
	app/build/install/app/bin/app -h



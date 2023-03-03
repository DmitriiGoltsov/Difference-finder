.DEFAULT_GOAL := build-run

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist:
	make -C run-dist

run-dist-wrong:
	make -C run-dist-wrong

run-dist-yml:
	make -C run-dist-yml

run-dist-yml-wrong:
	make -C run-dist-yml-wrong

run-dist-nest:
	make -C run-dist-nest

run-dist-nest-yml:
	make -C app run-dist-nest-yml

run:
	make -C app run

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

update-deps:
	make -C app update-deps


build-run: build run

.PHONY: build

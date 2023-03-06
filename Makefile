.DEFAULT_GOAL := build-run

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist-nest:
	make -C app run-dist-nest

run-dist-nest-yml:
	make -C app run-dist-nest-yml

run-dist-plain:
	make -C app run-dist-plain

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

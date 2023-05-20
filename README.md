## Description: 

Simple, small and fast CL utility that can find differences between two configuration files.

### *Supported input formats:*

1) .json
2) .yml

### *Main features:*

The utility finds the differences between two configuration files and shows the result of this comparing in three possible styles:

1) Stylish (default style);
2) Plain;
3) Json (the utility create a String in accordance with .json format);

You can find examples of all supported styles below in the attached asciinemas. Please, take into account that nested YAML and JSON 
files are also supported by the utility.

### *Brief instruction:*

1) Download the utility to the directory you like;
2) Open the directory with your terminal and type: "make install";
3) Wait;
4) Type in directory of the utility: ~$ ./build/install/app/bin/app file1 file2, where "file1" is a path to the first configuration file 
and "file2" is a path to the second one respectively. You can use Makefile commands as well. 

If you need help, please type ~$ ./build/install/app/bin/app -h for more information. 

### *Hexlet tests and linter status:*
[![Actions Status](https://github.com/DmitriiGoltsov/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/DmitriiGoltsov/java-project-71/actions)

### *CodeClimate maintainability and test coverage statuses:*

[![Maintainability](https://api.codeclimate.com/v1/badges/5d5850914a6dfe1f6724/maintainability)](https://codeclimate.com/github/DmitriiGoltsov/java-project-71/maintainability)

<a href="https://codeclimate.com/github/DmitriiGoltsov/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/5d5850914a6dfe1f6724/test_coverage" /></a>

### *Asciinemas of how application works with different tasks:*

[![asciicast](https://asciinema.org/a/vKB3EizHdx1V2nW0EiASNUSOR.svg)](https://asciinema.org/a/vKB3EizHdx1V2nW0EiASNUSOR)

[![asciicast](https://asciinema.org/a/AZwaj2rFbRXUyh8pF4eGGZolj.svg)](https://asciinema.org/a/AZwaj2rFbRXUyh8pF4eGGZolj)

[![asciicast](https://asciinema.org/a/H02jEd0qgDi2djdaBvbOn0JwZ.svg)](https://asciinema.org/a/H02jEd0qgDi2djdaBvbOn0JwZ)

[![asciicast](https://asciinema.org/a/S1HL4O8nde9O27fTc65nMo8jn.svg)](https://asciinema.org/a/S1HL4O8nde9O27fTc65nMo8jn)

[![asciicast](https://asciinema.org/a/pYxrob3jXYXAXVOF52RBnfPuW.svg)](https://asciinema.org/a/pYxrob3jXYXAXVOF52RBnfPuW)

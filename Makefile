all:
	-mkdir ./bin
	javac -d ./bin ./src/bazar/* ./src/org/apache/commons/cli/* ./src/parser/* ./src/tree/*
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

clean:
	-rm -r ./bin
	-rm Bazar.jar

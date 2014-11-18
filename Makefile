all: jar doc

jar:
	-mkdir ./bin
	javac -d ./bin ./src/bazar/* ./src/org/apache/commons/cli/* ./src/parser/* ./src/tree/*
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

doc:
	-mkdir ./doc/javadoc
	javadoc -public -splitindex -author -version -charset UTF-8 -d ./doc/javadoc bazar parser tree -sourcepath ./src

clean:
	-rm -r ./bin
	-rm Bazar.jar

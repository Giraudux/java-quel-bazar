all: jar doc

jar:
	-mkdir ./bin
	javac -d ./bin ./src/bazar/* ./src/org/apache/commons/cli/* ./src/parser/* ./src/tree/*
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

doc:
	-mkdir ./doc/javadoc
	javadoc -public -splitindex -author -version -charset UTF-8 -d ./doc/javadoc bazar parser tree -sourcepath ./src

test: jar
	java -jar Bazar.jar -k 4 -dico ./doc/Jeux/dictionnaire ./doc/Jeux/page_01 ./doc/Jeux/page_02 ./doc/Jeux/page_03 ./doc/Jeux/page_04 ./doc/Jeux/page_05 ./doc/Jeux/page_06 ./doc/Jeux/page_07 ./doc/Jeux/page_08 ./doc/Jeux/page_09 ./doc/Jeux/page_10

clean:
	-rm -r ./bin
	-rm Bazar.jar

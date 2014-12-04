all: jar doc

jar:
	-mkdir ./bin
	find ./src -name "*.java" > source_list
	javac -d ./bin @source_list
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

doc:
	-mkdir ./doc/javadoc
	javadoc -public -splitindex -author -version -charset UTF-8 -d ./doc/javadoc bazar parser tree -sourcepath ./src

test: jar
	java -jar Bazar.jar -k 4 -dico ./test/jeu0/dictionnaire ./test/jeu0/page_??

clean:
	-rm -r ./bin
	-rm Bazar.jar
	-rm source_list

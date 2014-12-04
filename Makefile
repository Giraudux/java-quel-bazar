all: jar doc

.PHONY: jar
jar:
	-mkdir ./bin
	find ./src -name "*.java" > source_list
	javac -d ./bin @source_list
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

.PHONY: doc
doc:
	-mkdir ./doc/javadoc
	javadoc -public -splitindex -author -version -charset UTF-8 -d ./doc/javadoc bazar parser tree -sourcepath ./src

.PHONY: test
test: jar
	@echo "########"
	@echo "# jeu0 #"
	@echo "########"
	java -jar Bazar.jar -k 4 -dico ./test/jeu0/dictionnaire ./test/jeu0/page_??
	@echo "########"
	@echo "# jeu1 #"
	@echo "########"
	java -jar Bazar.jar -k 4 -dico ./test/jeu1/dico.txt ./test/jeu1/P?*.txt

.PHONY: clean
clean:
	-rm -r ./bin
	-rm Bazar.jar
	-rm source_list

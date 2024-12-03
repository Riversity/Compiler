JAVA_SRC = $(shell find src -name '*.java')

ANTLR_JAR = /ulib/antlr-4.13.0-complete.jar

.PHONY: all
all: build

.PHONY: build
build: $(JAVA_SRC)
	javac -d bin $(JAVA_SRC) -cp $(ANTLR_JAR) -encoding UTF-8

.PHONY: clean
clean:
	find bin -name '*.class' -or -name '*.jar' | xargs rm -f

.PHONY: run
run:
	java -cp bin:$(ANTLR_JAR) Compiler && cat builtin.s
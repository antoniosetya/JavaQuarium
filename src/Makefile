JC = javac
JLIBS = -cp "arkavquarium/junit-4.12.jar"
CLASSES = arkavquarium/*.java arkavquarium/lib/*.java
OBJECTS = arkavquarium/*.class arkavquarium/lib/*.class

all:
	$(JC) $(JFLAGS) $(JLIBS) $(CLASSES)

clean:
	$(RM) arkavquarium/*.class
	$(RM) arkavquarium/lib/*.class

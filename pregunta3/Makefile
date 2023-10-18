KC=	kotlinc
KFLAG=	-cp

all:	\
	buddySystem.class \
	clienteBSKt.class

buddySystem.class: buddySystem.kt
	$(KC) $(KFLAG) . buddySystem.kt

clienteBSKt.class: clienteBS.kt 
	$(KC) $(KFLAG) . clienteBS.kt

clean:
	rm -rf *.class META-INF

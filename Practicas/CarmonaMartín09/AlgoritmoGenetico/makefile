compilar: 
	rm -rf build
	mkdir build
	javac -cp ./build:./lib/core-3.3.6.jar -d ./build ./src/*/*.java

javadoc: 
	rm -rf javadoc
	mkdir javadoc
	javadoc -cp ./build:./lib/core-3.3.6.jar -d ./javadoc ./src/*/*.java

clean: 
	rm -rf build javadoc

ejecutar:
	cd build
	java -cp ./build:./lib/core-3.3.6.jar reinas/VisualizacionProblemaReinas
	cd ..

rem set PATH=../../lib/;../../jre/bin/
rem javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar -Djava.library.path=../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java

java -Dfile.encoding=UTF-8 -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;./lib/json-20150729.jar dvt.run.Main
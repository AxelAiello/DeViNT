rem set PATH=../../lib/;../../jre/bin/
rem javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar -Djava.library.path=../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java

javac -encoding UTF-8 -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;../ressources/lib/json-20150729.jar; -d ../bin ../src/dvt/run/*.java ../src/dvt/devint/*.java ../src/dvt/score/*.java ../src/dvt/menu/*.java ../src/dvt/bloquee/*.java ../src/dvt/consigne/*.java ../src/dvt/controle/*.java ../src/dvt/fond/*.java ../src/dvt/inventaire/*.java ../src/dvt/itemtrouve/*.java ../src/dvt/labyrinthe/*.java ../src/dvt/map/*.java ../src/dvt/option/*.java ../src/dvt/partie/*.java ../src/dvt/question/*.java ../src/dvt/quitter/*.java ../src/dvt/formulairenom/*.java
pause

@echo off
cd "C:\Users\kushal2\IdeaProjects\Scientific Calculator"
javac -d ./ src/*.java
jar mfcv "out/production/Scientific Calculator/META-INF/MANIFEST.MF" calc.jar *.class
pause
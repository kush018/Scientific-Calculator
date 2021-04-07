@echo off
javac -d ./ src/*.java
jar mfcv "out/production/Scientific Calculator/META-INF/MANIFEST.MF" calc.jar *.class
java -jar D:\launch4j\launch4j.jar create_exe_launch4j.xml
"C:\Program Files (x86)\Inno Setup 6\ISCC.exe" create_installer.iss
pause
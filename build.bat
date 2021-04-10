@echo off

::Change the below path to the path to launch4j
set launch4j="D:\launch4j"
::Change the below path to the path to inno setup
set innosetup="C:\Program Files (x86)\Inno Setup 6"

javac -d ./ src/*.java
jar mfcv "out/production/Scientific Calculator/META-INF/MANIFEST.MF" calc.jar *.class
java -jar %launch4j%\launch4j.jar create_exe_launch4j.xml
%innosetup%\ISCC.exe create_installer.iss
pause
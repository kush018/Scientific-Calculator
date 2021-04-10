# Compilation Instructions

## Prerequisites

- To compile the code as a .jar file, you will need to have JDK installed.
- To create a windows installer file, you will need to have JDK installed, Inno Setup Compiler and Launch 4j installed.
    - Install launch4j [here](http://launch4j.sourceforge.net/)
    - Install Inno Setup Compiler [here](https://jrsoftware.org/isdl.php)

## Downloading a local copy of the code

First, clone the repository by:

```
git clone https://github.com/kush018/Scientific-Calculator.git
```

Then, change the directory to the directory where the repository got cloned by:

```
cd Scientific-Calculator
```

## Compiling the code as a .jar file

Now, to compile the code into a single .jar (java archive) file:

```
javac -d ./ src/*.java
jar mfcv "out/production/Scientific Calculator/META-INF/MANIFEST.MF" calc.jar *.class
```

You may replace calc.jar with a name of your choice. (this is the name of the .jar file)

While running the .jar file, make sure that the folders "fonts" and the file "icon.png" are in the same directory as the .jar file.
If these files are not present in the same directory as the .jar file, the program will run unexpectedly or fail to run altogether

## Creating a windows installer

To create a windows installer for the program, you need not create a .jar file as it is created automatically by an automated batch script that is responsible for creating the installer executable.

First go to the file, [create_installer.iss](create_installer.iss) and look for:

```
#define JREPath "C:\Users\kushal2\java"
```

Here, replace the path in quotes with the path to the JRE present in your computer. (if you do not have JRE installed on your computer,  you can just enter the path to JDK present on your computer)

Then, go to the file, [build.bat](build.bat) and look for:

```
set launch4j="D:\launch4j"
set innosetup="C:\Program Files (x86)\Inno Setup 6"
```

Here, replace the path in quotes in the first line with the path to launch4j on your computer and replace the path in quotes in the second line with the path to Inno Setup on your computer.

Then, save the files and run the batch script, build.bat by double-clicking it or from the command line. (NOTE: If you are running the batch script from the command line, make sure that the command line is in the same directory as the batch file)
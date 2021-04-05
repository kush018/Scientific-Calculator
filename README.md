# Scientific-Calculator
 A scientific calculator written in Java

## Running the calculator program

Instructions on how to compile and run the calculator program

### Prerequisites
In order to compile the program, it is necessary to have JDK installed on your system.
To run the compiled program, you need to have JRE (if you already have JDK installed JRE need not be installed)

### Compiling and Running the Program
First, clone the repository by:

```
git clone https://github.com/kush018/Scientific-Calculator.git
```

Then, change the directory to the directory where the repository got cloned by:

```
cd Scientific-Calculator
```

Now, to compile the code:

```
javac -d ./ src/*.java
```

To run the code:

```
java Main
```

After typing the above command, the calculator should be running on your computer

Although this works, its an inconvenient way of running the program as one would have to open the command line everytime he/she wants to calculate something
To solve this problem, we can create a .jar (Java Archive) which can just be double clicked to run the application

To create the .jar file, type the following command:

```
jar mfcv "out/production/Scientific Calculator/META-INF/MANIFEST.MF" calc.jar *.class
```

You may replace calc.jar with a name of your choice. (this is the name of the .jar file)

While running, make sure that the folders "fonts" and the file "icon.png" are in the same directory as the .jar file.
If these files are not present in the same directory as the .jar file, the program will run unexpectedly or fail to run altogether

## User Manual

A guide to anyone who wants to use the calculator program

### Keyboard Shortcuts

To make the user's life easier, keyboard shortcuts have been added.
These shortcuts have been given below.

- Numbers 0 to 9 - Used to enter numbers in the calculator
- '.', '+', '-', '*', '/' and '%' keys can be used for performing their respective operations or, in the case of the '.' for simply entering a period on the calculators display.
- 'E' can be used to press the 'EXP' button on the calculator
- 'A' can be used to press the 'ANS' button on the calculator
- 'D' can be used to convert an angle in radians to degrees
- 'R' can be used to convert an angle in degrees to radians
- 'M' can be used to press the '+/-' button on the calculator
- The ENTER key can be used to find the result of an operation
- The Backspace or Delete key can be used to delete a number from the calculators display
- The ESC key can be used to press the 'AC' button to reset everything
- F1 can be used to clear the display
- F12 can be used to clear the calculators memory
- F8 can be used to display the calculators memory
- F5 can be used to add whatever is in the display to the calculators memory

## Updating the program

Required to make sure the program is up-to-date with all the latest updates

To update the source code in your local clone of the repository, type:

```
git clone https://github.com/kush018/Scientific-Calculator.git
```

Then, you will have to execute the commands given above to compile the program

## License

This project is licensed under the MIT License - see the [LICENCE.md](LICENSE.md) file for details

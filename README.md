# 1.📖 Introduction <br>

This project provides an implementation of arbitrary precision arithmetic in Java. The main motivation is to overcome the limitations of built-in numeric types, which cannot handle large inputs due to size bounds. <br>
By representing numbers as strings, we can perform fundamental arithmetic operations manually, giving full control over precision and sign handling.<br>

The system supports both integer and floating point operations, organized into separate classes: `AInteger`, `AFloat`. The main class `MyInfArith` handles command line arguments and performs required arithmetic operations. <br>

Supported operations: **Addition, Subtraction, Multiplication, Division** for both integer and float types.You can get up to 30 decimal precision without any errors.

# 2.📁 project Folder structure <br>
project_folder/

    • javasrc/
        - arbitraryarithmetic/
            - AInteger.java
            - AInteger.class
            - AFloat.java
            - AFloat.class
        - MyInfArith.java
        - MyInfArith.class
    • build/
        - arbitraryarithmetic/
            - AInteger.class
            - AFloat.class
        -MyInfArith.class
    • arbitraryarithmetic/
        -aarithmetic.jar
    • build.xml
    • execute_java.py
    • latex_report/
          - latex_report.pdf
          - latex_report.tex

          
# 3. 🛠️ Code overview <br>
AInteger.java: <br>
This class handles arbitrary-precision integer operations. Numbers are represented as strings, and digit-wise operations addition, subtraction, multiplication, division are implemented manually and perform accurate results with very 
large inputs. It also includes utility methods for sign handling and removing leading zeroes. This class is similar to the  standard integer arithmetic without relying on Java's built-in numeric limits.

AFloat.java: <br>
This class provides support for arbitrary-precision floating-point arithmetic numbers. Like AInteger, it represents numbers as strings, while also carefully aligning decimal points and truncating results to a consistent 30-digit precision. 
Operations are designed to preserve accuracy and correct decimal placement, and also handles edge cases like trailing zeroes and negative values.

MyInfArith.java: <br>
This is the entry point of the program. It reads command-line inputs and interprets the operand types (int or float) and operations (add, sub, mul, div), and delegates the computation to the appropriate class (AInteger or AFloat). 
It ensures correct input format and prints the result to the console.

# 4.🚀 Usage: <br>
## 1.Using java:<br>
first go into javasrc Directory to run using java commands. <br>
commands: First compile the main file "MyInfArith.java"-> javac MyInfArith.java <br>
Then run as-> java MyInfArith type operation operand1 operand2 <br>
Examples:<br>
Input: java MyInfArith int mul 14  12 <br>
Output: 168 <br>

## 2.Using Ant: <br>
In terminal run this command. <br>
command: ant run -Darg1=`<type>` -Darg2=`<operation>` -Darg3=`<operand1>` -Darg4=`<operand2>`  <br>
Example: <br>
• Input: ant run -Darg1=float -Darg2=sub -Darg3=84.2 -Darg4=127 <br>
Output: -42.8 <br>

## 3.Using python script: <br>
In terminal run this command. <br>
command: python3 execute java.py `<type>` `<operation>` `<operand1>` `<operand2>`  <br>
Example: <br>
• Input: python3 execute java.py float add 84 70 <br>
Output: 154.0 <br>

## 4.Using JAR file: <br>
In terminal run this command. <br>
command: java -cp arbitraryarithmetic/aarithmetic.jar:javasrc MyInfArith `<type>` `<operation>` `<operand1>` `<operand2>`  <br>
Example: <br>
Input: java -cp arbitraryarithmetic/aarithmetic.jar:javasrc MyInfArith float mul 640 2 <br>
Output: 1280 <br>

# 5.Verification: <br>
## 1.Using Java: <br>
• Integer Mutiplication- <br>
Input: java MyInfArith int mul 14344163160445929942680697312322  23017167694823904478474013730519 <br>
Output: 330162008905899217578310782382075660760972861550182008086155118 <br>
• Integer division - <br>
Input: java MyInfArith int div 8792726365283060579833950521677211  493835253617089647454998358 <br>
Output: 17804979 <br>

## 2.Using Ant: <br>
• Input: ant run -Darg1=float -Darg2=sub -Darg3=840196454.51725 -Darg4=127609490.81442 <br>
Output: 3.221603634537752111008551505615 <br>
• Input: ant run -Darg1=int -Darg2=div -Darg3=23650078224912949497310933240250 -Darg4=42939783262467113798386384401498 <br>
Output: 66589861487380063295697317641748 <br>

## 3.Using python script: <br>
• Input: python3 execute java.py float add 84486723.420039 70974199.843732 <br>
Output: 155460923.263771 <br>
• Input: python3 execute java.py float mul 6400251.9377695 2326541.6827934 <br>
Output: 14890452913599.9717457253213 <br>

## 4.Using JAR file: <br>
• Input: java -cp arbitraryarithmetic/aarithmetic.jar:javasrc MyInfArith float mul 6400251.9377695 2326541.6827934 <br>
• Output: 14890452913599.9717457253213 <br>

# 6.Limitations: <br>
• The division logic uses repeated subtraction, which can be slow for large inputs and when dividing by small numbers. <br>
• It can perform four operations Addition,subtraction,Multiplication.Division. Other than this it cannot perform any other operations.<br>
• Numbers in exponential form (e.g., 1e10) are not supported. <br>

# 7.Key learnings: <br>
• Java oops and packaging <br>
• Ant makefile <br>
• Python scripting <br>
• Git commands <br>

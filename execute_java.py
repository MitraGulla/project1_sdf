import os,sys

# Compiles the Java source files
def compile_java():

    #compiles all java files in "javasrc/arbitraryarithmetic" directory
    result1=os.system("javac javasrc/arbitraryarithmetic/*.java")

    #compilng the main file "MyInfArith.java" ,including the javasrc directory which has arbitraryarithmetic package
    result2=os.system("javac -cp javasrc javasrc/MyInfArith.java")

    if (result1!=0 or result2!=0):
        print("Compilation failed")
        sys.exit(1)
    print("Compilation completed")

#Execute the compiled java program with arguments
def run_java(type,operation,num1,num2):

    #Runs the main file "MyInfArith.java"
    os.system(f"java -cp javasrc MyInfArith {type} {operation} {num1} {num2}")
        
#Accepts only 4 command-line arguments
if len(sys.argv)!=5:
    print("usage error: Enter 4 arguments after the file name")
    sys.exit(1)

type,operation,num1,num2=sys.argv[1:]

compile_java()
run_java(type,operation,num1,num2)
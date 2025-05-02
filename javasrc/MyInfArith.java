// Import classes from the arbitraryarithmetic package
import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;

public class MyInfArith{
    public static void main(String[] args){

        // Checking if 4 arguments are provided
        if (args.length!=4){
            System.out.println("Enter 4 arguments");
        }
        //Storing input arguments in other variables for acessing them

        String type=args[0];           // int or float
        String operation=args[1];      // add or sub or mul or div
        String num1=args[2];           //First number
        String num2=args[3];           //Second number

        // If type is integer
        if (type.equals("int")){

            // Check if any decimal points are present as Int supports only Integers
            int checkdecimal=-1;
            if (num1.indexOf(".")!=checkdecimal||num2.indexOf(".")!=checkdecimal) {
                System.out.println("Usage error:Only integers are allowed");
            }
            else{

                // Create AInteger instances for num1 and num2
                AInteger str1=new AInteger(num1);
                AInteger str2=new AInteger(num2);
                AInteger finalresult = null;

                // Performing the operation
                switch (operation){
                    case "add":
                        finalresult=str1.add(str2);
                        break;
                    case "sub":
                        finalresult=str1.sub(str2);
                        break;
                    case "mul":
                        finalresult=str1.mul(str2);
                        break;
                    case "div":
                        finalresult=str1.div(str2);
                        break;
                    default:

                    //If any other operations are enetered
                        System.out.println("This operation is not supported.");
                        return;
                }

                // Print the result of the operation
                System.out.println(finalresult);
            }
        }

        // If type is float
        else if (type.equals("float")){

            // Create AFloat instances for num1 and num2
            AFloat str1=new AFloat(num1);
            AFloat str2=new AFloat(num2);
            AFloat finalresult = null;

            // Performing the operation
            switch (operation){
                case "add":
                    finalresult=str1.add(str2);
                    break;
                case "sub":
                    finalresult=str1.sub(str2);
                    break;
                case "mul":
                    finalresult=str1.mul(str2);
                    break;
                case "div":
                    finalresult=str1.div(str2);
                    break;
                default:

                //If any other operations are enetered
                    System.out.println("This operation is not supported.");
                    return;
            }

            // Print the result of the operation
            System.out.println(finalresult);
        }
    }
}

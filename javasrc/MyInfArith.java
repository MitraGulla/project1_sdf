import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;
public class MyInfArith{
    public static void main(String[] args){
        if (args.length!=4){
            System.out.println("Enter 4 arguments");
        }
        String type=args[0];
        String operation=args[1];
        String num1=args[2];
        String num2=args[3];

        if (type.equals("int")){

            int checkdecimal=-1;
            if (num1.indexOf(".")!=checkdecimal||num2.indexOf(".")!=checkdecimal) {
                System.out.println("Usage error:Only integers are allowed");
            }
            else{
                AInteger str1=new AInteger(num1);
                AInteger str2=new AInteger(num2);
                AInteger finalresult = null;

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
                        System.out.println("This operation is not supported.");
                        return;
                }
                System.out.println("Output: "+finalresult);
            }
        }

        else if (type.equals("float")){

            AFloat str1=new AFloat(num1);
            AFloat str2=new AFloat(num2);
            AFloat finalresult = null;

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
                    System.out.println("This operation is not supported.");
                    return;
            }
            System.out.println("Output: "+finalresult);
        }
    }
}

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

            AInteger s1=new AInteger(num1);
            AInteger s2=new AInteger(num2);
            AInteger finalresult = null;

            switch (operation){
                case "add":
                    finalresult=s1.add(s2);
                    break;
                case "sub":
                    finalresult=s1.sub(s2);
                    break;
                case "mul":
                    finalresult=s1.mul(s2);
                    break;
                case "div":
                    finalresult=s1.div(s2);
                    break;
                default:
                    System.out.println("This operation is not supported.");
                    return;
            }
            System.out.println("Output: "+finalresult);
        }

        else if (type.equals("float")){

            AFloat s1=new AFloat(num1);
            AFloat s2=new AFloat(num2);
            AFloat finalresult = null;

            switch (operation){
                case "add":
                    finalresult=s1.add(s2);
                    break;
                case "sub":
                    finalresult=s1.sub(s2);
                    break;
                case "mul":
                    finalresult=s1.mul(s2);
                    break;
                case "div":
                    finalresult=s1.div(s2);
                    break;
                default:
                    System.out.println("This operation is not supported.");
                    return;
            }
            System.out.println("Output: "+finalresult);
        }
    }
}
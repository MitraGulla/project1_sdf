package arbitraryarithmetic;
public class AInteger{

    // Stores the integer value as a string
    public String strvalue;
    
    // Default constructor,initializes strvalue to 0
    public AInteger(){
        this.strvalue="0";
    }

    // Constructor from string,Handles negative numbers and removes leading zeros from strvalue
    public AInteger(String str) {
        if (str.charAt(0)=='-') {
            this.strvalue="-"+removezeroes(str.substring(1));
        }
        else{
            this.strvalue=removezeroes(str);
        }
    }

    // Copy constructor
    public AInteger(AInteger other){
        this.strvalue=other.strvalue;
    }

    // Removes zeroes from the starting of string
    public String removezeroes(String str){
        int i=0;

        //Finding the index of the first non zero number
        while (i<str.length()) {
            if (str.charAt(i)!='0'){
                break;
            }
            i+=1;
        }

        //If the index goes to the last of the string then all numbers are zeroes,so returning 0
        if (i==str.length()) {
            return "0";
        }

        //else remove zeroes and return the remaining string
        else{
            return str.substring(i,str.length());

        }
    }

    // Determines the sign of the result based on values
    // Used in subraction method
    public String Sign(String str1,String str2){
        String sign="";
        if (str1.length()<str2.length()){
            sign="-";
        }                                          //keep sign as "-" if length of first number is less than second number
        else if(str1.length()==str2.length()) {
            int i=0;
            while (i<str1.length()) {
                if (str1.charAt(i)!=str2.charAt(i)) {
                    if (str1.charAt(i)<str2.charAt(i)){
                        sign="-";
                    }
                    break;                            //If lengths are equal then check the first non equal number and decide the sign
                }
                i+=1;
            }
        }
        return sign;
    }

    // Returns the smaller of two string based on values
    public String less(String str1, String str2) {
        if (str1.length()==str2.length()) {
            int index=0;
            while (index<str1.length()) {
                if (str1.charAt(index)!=str2.charAt(index)) {
                    break;
                }
                index++;                            //Finding the index of the first non equal number
            }
            if (index==str2.length()) {
                return str2;
            }

            //If lengths of two numbers are equal then check the non equal number and return the less number
            else if (str1.charAt(index)<str2.charAt(index)) {
                return str1;
            }
            
            else {
                return str2;
            }
        } 
        else if (str1.length()<str2.length()) {
            return str1;                             //If length of first number is less than second number then return first number
        } 
        else {
            return str2;                             //If length of second number is less than first number then return second number
        }
    }
    
    // Static method to parse string to AInteger
    public static AInteger parse(String str) {
        return new AInteger(str);
    }  

    // Returns the string
    public String toString() {
        return this.strvalue;
    }

    
    // Performs digit wise addition of two non negative integer strings
    public String addition(String str1,String str2) {
        String finalStr="";                           //Final result initialized to zero
        int extra=0;

        //Running while loop until both strings become empty
        while (!str1.isEmpty()||!str2.isEmpty()) { 

            int num1=0,num2=0;

            // Store last digit of str1 in num1 if string is not empty
            if (!str1.isEmpty()){
                num1=Integer.parseInt(str1.charAt(str1.length()-1)+"");
                str1=str1.substring(0,str1.length()-1);                 //Removing the last number from str1
            }

            // Store last digit of str2 in num2 if string is not empty
            if (!str2.isEmpty()){
                num2= Integer.parseInt(str2.charAt(str2.length()-1)+"");
                str2=str2.substring(0,str2.length()-1);                  //Removing the last number from str2
            }

            //Adding num1 num2 and also adding extra which contains the carry
            int sum=num1+num2+extra;
            finalStr=Integer.toString(sum%10)+finalStr;
            extra=sum/10;                                   //updating carry
        }

        //finally adding if any carry is left
        if (extra>0) {
            finalStr=Integer.toString(extra)+finalStr;
        }

        // Remove leading zeroes and return result
        return removezeroes(finalStr);
    }


    // Performs digit wise subtraction of two non-negative integer strings
    public String subtract(String str1,String str2){
        String finalstr="";                           //Final result initialized to zero

        //Stores sign as negative if str1 is less than str2
        String sign=Sign(str1,str2);

        // If both strings are equal, return "0"
        if(str1.length()==str2.length()) {
            int index=0;
            while (index<str1.length()) {
                if (str1.charAt(index)!=str2.charAt(index)) {
                    break;
                }
                index+=1;
            }
            if (index==str1.length()) {
                return "0";                // All digits are equal,so result is zero
            } 
            else if (str1.charAt(index)<str2.charAt(index)) {

                // Swap str1 and str2 if str1 < str2
                String temp=str1;
                str1=str2;
                str2=temp;
            }
        }
        else if(str1.length()<str2.length()) {

            // If length of str1 is less than str2,swap them
            String temp=str1;
            str1=str2;
            str2=temp;
        }

        // Perform subtraction digit by digit from right to left
        while (!str1.isEmpty()||!str2.isEmpty()) {
            int num1=0,num2=0;

            // Store last digit of str1 in num1 if string is not empty
            if (!str1.isEmpty()) {
                num1=Integer.parseInt(str1.charAt(str1.length()-1)+"");
                str1=str1.substring(0,str1.length()-1);                     //Remove the last number from str1
            }

            // Store last digit of str2 in num2 if string is not empty
            if (!str2.isEmpty()) {
                num2=Integer.parseInt(str2.charAt(str2.length()-1)+"");
                str2=str2.substring(0,str2.length()-1);                     //Remove the last number from str2
            }

            // If num1 < num2,borrow from higher digits
            if (num1<num2) {
                int len=str1.length()-1;
                while (len>=0) {

                         //go until the non zero digit
                    if (str1.charAt(len)!='0') {
                        char borrowed=(char)(str1.charAt(len)-1);
                        str1=str1.substring(0,len)+borrowed+str1.substring(len+1);
                        break;
                    }

                    //Make the zero digits to 9 and move left
                    else {
                        str1=str1.substring(0,len)+'9'+str1.substring(len+1);
                        len-=1;
                    }
                }
                num1+=10;
            }

            // Calculate the difference and prepend to result
            int diff=num1-num2;
            finalstr=Integer.toString(diff)+finalstr;
        }

        finalstr=removezeroes(finalstr);                // Remove any leading zeroes

        // Add sign if necessary and return the result
        return sign+finalstr;
    }


    // Adds two AInteger values with proper sign handling
    public AInteger add(AInteger string2) {

        String str1=this.strvalue;
        String str2=string2.strvalue;
        boolean sign1=false;                                       //If sign is negative then boolean contains true
        boolean sign2=false;                                    //Initally keeping both sign1 and sign2 as positive which means false
        if (str1.charAt(0)=='-') {
            //Making sign1 boolean true
            sign1=true;
        }
        if (str2.charAt(0)=='-') {
            //Making sign2 boolean true
            sign2=true;
        }

        if (sign1) {
            //If sign1 is negative then removing the negative sign from the starting of string
            str1=str1.substring(1);
        }
        if (sign2) {
            //If sign2 is negative then removing the negative sign from the starting of string
            str2=str2.substring(1);
        }

        /*Checking signs and calling respective functions like add or subtract with the respective arguments
         Atlast adding negative sign at starting of result if required.
         Create a new AInteger object with the proper sign and return it as the final result.*/
        if (sign1&&sign2) {
            return new AInteger("-"+addition(str1,str2));               
        } 
        else if (sign1) {
            return new AInteger(subtract(str2,str1));
        } 
        else if (sign2) {
            return new AInteger(subtract(str1,str2));
        } 
        else {
            return new AInteger(addition(str1,str2));
        }
    }
    
    // Subtracts two AInteger values with proper sign handling
    public AInteger sub(AInteger string2) {

        String str1=this.strvalue;
        String str2=string2.strvalue;
        boolean sign1=false;                                               //If sign is negative then boolean contains true
        boolean sign2=false;                                            //Initally keeping both sign1 and sign2 as positive which means false
        if (str1.charAt(0)=='-') {
            //Making sign1 boolean true
            sign1=true;
        }
        if (str2.charAt(0)=='-') {
            //Making sign2 boolean true
            sign2=true;
        }
        if (sign1) {
            //If sign1 is negative then removing the negative sign from the starting of string
            str1=str1.substring(1);
        }
        if (sign2) {

            //If sign2 is negative then removing the negative sign from the starting of string
            str2=str2.substring(1);
        }

        /*Checking signs and calling respective functions like subtract or add with the respective arguments
         Atlast adding negative sign at starting of result if required.
         Create a new AInteger object with the proper sign and return it as the final result.*/
        if (sign1&&!sign2) {
            return new AInteger("-"+addition(str1,str2));
        } 
        else if (!sign1&&sign2) {
            return new AInteger(addition(str1,str2));
        } 
        else if (sign1&&sign2) {
            return new AInteger(subtract(str2,str1));
        } 
        else {
            return new AInteger(subtract(str1,str2));
        }
    }
    
    
    // Performs digit-wise multiplication of two non-negative integer strings
    public String multiply(String str1,String str2) {

        String finalstr="0";           //Final product initialized to zero

        for (int i=str2.length()-1;i>=0;i--) {
            int num2=Integer.parseInt(str2.charAt(i)+"");
            String temp="";                                 //taking each number in str2 from last and stroing in num2
            int extra=0;
            
            for (int j=str1.length()-1;j>=0;j--) {
                int num1=Integer.parseInt(str1.charAt(j)+"");         //Taking each number in str1 from last and storing in num1 
                int product=(num1*num2)+extra;                     //Mutiplying two numbers and adding carry
                temp=Integer.toString(product%10)+temp;
                extra=product/10;              //updating carry
            }

            //If there is any carry left add it to the front of temp
            if (extra!=0) {
                temp=Integer.toString(extra)+temp;            
            }

            // Append zeroes at the end based on the current digit position of str2
            for (int z=0;z<str2.length()-1-i;z++) {
                temp+="0";
            }

            // Adding current partial product to total sum stored in str
            finalstr=addition(finalstr,temp);
        }

        // Remove leading zeroes and return result
        return removezeroes(finalstr);
    }

    // Multiplies two AInteger values with proper sign handling
    public AInteger mul(AInteger string2) {

        String str1=this.strvalue;
        String str2=string2.strvalue;
        String signformul="";
        if ((str1.charAt(0)=='-')^(str2.charAt(0)=='-')){
            //using xor beacuse we want to store negative sign if only one number is negative.

            if (str1.charAt(0)=='-'){

                //if number is negative then remove negative sign from starting
                str1=str1.substring(1);
            }
            if (str2.charAt(0)=='-'){

                //if number is negative then remove negative sign from starting
                str2=str2.substring(1);
            }
            signformul="-";      //Storing negative sign in signformul variable
            }


        /*If both numbers are negative then final sign is positive 
          Removing the negative sign from starting of both numbers*/
        if ((str1.charAt(0)=='-')&&(str2.charAt(0)=='-')){
            str1=str1.substring(1); 
            str2=str2.substring(1);
        }

        //calling multiply function with arguments as the two strings
        String result=multiply(str1, str2);

        if (result.equals("0")){
            return new AInteger(result);        //If final result is "0" no sign is added.
        }
        else{

            // Create a new AInteger object with the proper sign and return it as the final product
            return new AInteger(signformul+result);
        }
    }

    // Divides this AInteger by another AInteger using repeated subtraction
    //Also handles proper sign
    public AInteger div(AInteger string2){
        String str1=this.strvalue;
        String str2=string2.strvalue;

        String signfordiv="";
        if ((str1.charAt(0)=='-')^(str2.charAt(0)=='-')){
            //using xor beacuse we want to store negative sign if only one number is negative.

            if (str1.charAt(0)=='-'){

                //if number is negative then remove negative sign from starting
                str1=str1.substring(1);
            }
            if (str2.charAt(0)=='-'){

                //if number is negative then remove negative sign from starting
                str2=str2.substring(1);
            }
            signfordiv="-";               //Store negative sign in signfordiv variable
        }

        /*If both numbers are negative then final sign is positive 
        Removing the negative sign from starting of both numbers*/
        if ((str1.charAt(0)=='-')&&(str2.charAt(0)=='-')){
            str1=str1.substring(1);
            str2=str2.substring(1);
        }

        if ((removezeroes(str2)).equals("0")){
            throw new ArithmeticException("Division by zero");
        }                                 //If divided by "0" throws error
        String finalstr="";
        String temp="";
        for (int j=0;j<str1.length();j++){
            int count=0;
            temp+=str1.charAt(j);
            temp=removezeroes(temp);

            /*Adding each digit to temp until temp is greater than str2,
            then subtracting str2 from temp until it is less than str2,
            store number of subtractions in count variable and append it to finalstr*/
            while (less(temp,str2).equals(str2)){
                    temp=subtract(temp,str2);
                    count+=1;
            }
            finalstr+=Integer.toString(count);
        }

        //Removing zeroes from starting
        finalstr=removezeroes(finalstr);
        if (finalstr.equals("0")){
            return new AInteger(finalstr);
            //If final result is "0" no sign is added.
        }
        else{

            // Create a new AInteger object with the proper sign and return it as the final result.
            return new AInteger(signfordiv+finalstr);
        }
    }
}

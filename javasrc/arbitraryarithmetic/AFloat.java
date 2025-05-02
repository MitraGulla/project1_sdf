package arbitraryarithmetic;
public class AFloat{

    // Stores the value of the floating-point number as a string
    public String strvalue;

    // Default constructor sets value to "0"
    public AFloat(){
        this.strvalue="0";
    }

    // Constructor from string
    public AFloat(String str) {
        this.strvalue=str;
    }

    // Copy constructor
    public AFloat(AFloat other){
        this.strvalue=other.strvalue;
    }

    // Removes zeroes from the starting of string
    public String removezeroesatstart(String str){
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
            str=str.substring(i,str.length());
        }
        if (str.charAt(0)=='.'){
            str="0"+str;
        }
        return str;
    }

    // Determines the sign of the result based on values
    // Used in subraction method
    public String Sign(String str1,String str2){
        String sign="";
        if (str1.length()<str2.length()){
            sign="-";
        }                                                   //keep sign as "-" if length of first number is less than second number
        else if(str1.length()==str2.length()) {
            int i=0;
            while (i<str1.length()) {
                if (str1.charAt(i)!=str2.charAt(i)) {
                    if (str1.charAt(i)<str2.charAt(i)){
                        sign="-";
                    }
                    break;                              //If lengths are equal then check the first non equal number and decide the sign
                }
                i+=1;
            }
        }
        return sign;
    }

    // Returns the index of the decimal point in the string
    public int decimalindex(String str){
        int deciindex=0;
        while (deciindex<str.length()){
            if (str.charAt(deciindex)=='.'){
                break;
            }
            deciindex++;
        }
        return deciindex;
    }

    // Appends a decimal point if not present
    public String addzeroes(int deciindex,String str){
        if (deciindex==str.length()){
            str=str+".0";
        }
        return str;
    }

    //Removing zeroes at the end of string 
    public String removezeroesatend(String str){
        if (str.contains(".")) {
            int i=(str.length()-1);
            if (str.charAt(i)=='0'){
                while (str.charAt(i)=='0'){
                    i-=1;
                }
            }
            str=str.substring(0, i+1);

            //After decimal if all digits are zeores then adding only one zero.
            if (str.charAt(i)=='.'){
                str+='0';
            }
        }
        return str;
    }

    // Returns the smaller of two string based on values
    public String less(String str1, String str2) {
        if (str1.length()==str2.length()) {
            int index=0;
            while (index<str1.length()) {
                if (str1.charAt(index)!=str2.charAt(index)) {
                    break;
                }
                index++;                                //Finding the index of the first non equal number
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
            return str1;
        } 
        else {
            return str2;
        }
    }

    // Static method to parse string to AFloat
    public static AFloat parse(String str) {
        return new AFloat(str);
    }   
    
    // Returns the string
    public String toString() {
        return this.strvalue;
    }

    // Performs digit wise addition of two non negative integer strings
    public String addition(String str1,String str2) {
        int extra=0;                                    //Final result initialized to zero

        String finalStr="";

        //Running while loop until both strings become empty
        while (!str1.isEmpty()||!str2.isEmpty()) {
            int num1=0,num2=0;

            // Store last digit of str1 in num1 if string is not empty and remove the last one.
            if (!str1.isEmpty()){
                num1=Integer.parseInt(str1.charAt(str1.length()-1)+"");
                str1=str1.substring(0,str1.length()-1);
            }

            // Store last digit of str2 in num2 if string is not empty and remove the last one.
            if (!str2.isEmpty()){
                num2= Integer.parseInt(str2.charAt(str2.length()-1)+"");
                str2=str2.substring(0,str2.length()-1);
            }
            int sum=num1+num2+extra;
            finalStr=Integer.toString(sum%10)+finalStr;
            extra=sum/10;                           //updating carry as extra
        }

        //finally adding if any carry is left
        if (extra>0) {
            finalStr=Integer.toString(extra)+finalStr;
        }

        // Remove leading zeroes and return result
        return removezeroesatstart(finalStr);
    }
    

    public AFloat add(AFloat string2){

        // Taking values from objects
        String str1=this.strvalue;
        String str2=string2.strvalue;

        // Check and store signs
        boolean sign1=false;
        boolean sign2=false;
        if (str1.charAt(0)=='-') {
            sign1=true;
        }
        if (str2.charAt(0)=='-') {
            sign2=true;
        }

        //If negative sign is present then remove it.
        if (sign1) {
            str1=str1.substring(1);
        }
        if (sign2) {
            str2=str2.substring(1);
        }

        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

        // Ensure decimal point exists
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);

        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        // Taking  decimal part from string
        String ss1=str1.substring(a+1,str1.length());
        String ss2=str2.substring(b+1,str2.length());
        int k=0;

        // Equalize decimal part lengths by appending zeroes at last
        if (ss1.length()>ss2.length()){
            k=ss1.length();
        }
        else{
            k=ss2.length();
        }
        while (ss1.length()<k) {
            ss1+="0";
        }
        while (ss2.length()<k) {
            ss2+="0";
        }
        
        // combine integer and fractional parts without the decimal
        str1=str1.substring(0,a)+ss1;
        str2=str2.substring(0,b)+ss2;
        String str="";

        // Handle signs and perform addition/subtraction correctly
        if (sign1&&sign2) {
            str=("-"+addition(str1,str2));
        } 
        else if (sign1) {
            str=subtract(str2,str1);
        } 
        else if (sign2) {
            str=subtract(str1,str2);
        } 
        else {
            str=addition(str1,str2);
        }

        // Make result has enough digits before placing the decimal
        while (str.length()<=k) {
            str="0"+str;
        }
        str=str.substring(0,str.length()-k)+"."+str.substring(str.length()-k);

        if ((str.charAt(0)=='-')&&(str.charAt(1)=='.')){
            str="-0"+str.substring(1);
        }
        if (str.equals("-0.0")){
            str="0.0";
        }

        // Remove zeroes at end of string and limit to 30 decimals precision
        str=removezeroesatend(str);
        return new AFloat(thirtydecimals(str));
    }

    // Performs digit-wise multiplication of two non-negative integer strings
    public String multiply(String str1,String str2) {
        String str="0";                                       //Final product initialized to zero

        for (int i=str2.length()-1;i>=0;i--) {

            //taking each number in str2 from last and stroing in num2
            int num2=Integer.parseInt(str2.charAt(i)+"");
            String temp="";
            int extra=0;
            
            for (int j=str1.length()-1;j>=0;j--) {

                //Taking each number in str1 from last and storing in num1 
                int num1=Integer.parseInt(str1.charAt(j)+"");   
                
                //Mutiplying two numbers and adding carry
                int product=(num1*num2)+extra;
                
                temp=Integer.toString(product%10)+temp;
                extra=product/10;                       //updating carry as extra
            }
            if (extra!=0) {
                temp=Integer.toString(extra)+temp;
            }
            for (int z=0;z<str2.length()-1-i;z++) {
                temp+="0";
            }

            // Adding current partial product to total sum stored in str
            str=addition(str,temp);
        } 

        //Remove leading zeroes and return result.
        return removezeroesatstart(str);
    }
    public AFloat mul(AFloat string2){

        // Taking values from objects
        String str1=this.strvalue;
        String str2=string2.strvalue;

        //Check and store signs
        String signformul="";
        if ((str1.charAt(0)=='-')^(str2.charAt(0)=='-')){
            if (str1.charAt(0)=='-'){
                str1=str1.substring(1);
            }
            if (str2.charAt(0)=='-'){
                str2=str2.substring(1);
            }
            signformul="-";
        }

        //If negative sign is present then remove it.
        if ((str1.charAt(0)=='-')&&(str2.charAt(0)=='-')){
            str1=str1.substring(1); 
            str2=str2.substring(1);
        }
        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

        //Ensure decimal point exists
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);
        
        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        // Taking  decimal part from string
        String ss1=str1.substring(a+1,str1.length());
        String ss2=str2.substring(b+1,str2.length());

        // combine integer and fractional parts without the decimal
        str1=str1.substring(0,a)+ss1;
        str2=str2.substring(0,b)+ss2;

        String str="";
        //Calling mutiply function with str1 and str2 as arguments
        str=multiply(str1,str2);
        int k=ss1.length()+ss2.length();

        // Make result has enough digits before placing the decimal
        while (str.length()<=k) {
            str="0"+str;
        }
        str=str.substring(0,str.length()-k)+"."+str.substring(str.length()-k,str.length());
        str=removezeroesatend(str);
        if (str.equals("0.0")||str.equals("0")){
            signformul="";
        }

        // limit to 30 decimals precision and add sign and return the final result
        return new AFloat(signformul+thirtydecimals(str));
    }

    // Performs digit wise subtraction of two non-negative integer strings
    public String subtract(String str1,String str2){
        String finalStr="";                             //Final result initialized to zero

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
                return "0";
            } 
            else if (str1.charAt(index)<str2.charAt(index)) {

                // Swap str1 and str2 if str1 < str2
                String temp=str1;
                str1=str2;
                str2=temp;
            }
        }

        // If length of str1 is less than str2,swap them
        else if(str1.length()<str2.length()) {
            String temp=str1;
            str1=str2;
            str2=temp;
        }

        // Perform subtraction digit by digit from right to left
        while (!str1.isEmpty()||!str2.isEmpty()) {
            int num1=0,num2=0;

            // Store last digit of str1 in num1 if string is not empty and remove the last one.
            if (!str1.isEmpty()) {
                num1=Integer.parseInt(str1.charAt(str1.length()-1)+"");
                str1=str1.substring(0,str1.length()-1);
            }

            // Store last digit of str2 in num2 if string is not empty and remove the last one.
            if (!str2.isEmpty()) {
                num2=Integer.parseInt(str2.charAt(str2.length()-1)+"");
                str2=str2.substring(0,str2.length()-1);
            }

            // If num1 < num2,borrow from higher digits
            if (num1<num2) {
                int i=str1.length()-1;
                while (i>=0) {
                    if (str1.charAt(i)!='0') {
                        char borrowed=(char)(str1.charAt(i)-1);
                        str1=str1.substring(0,i)+borrowed+str1.substring(i+1);
                        break;
                    }
                    else {

                        //Make the zero digits to 9 and move left
                        str1=str1.substring(0,i)+'9'+str1.substring(i+1);
                        i-=1;
                    }
                }
                num1+=10;
            }

            // Calculate the difference and prepend to result
            int diff=num1-num2;
            finalStr=Integer.toString(diff)+finalStr;
        }

        //Remove any leading zeroes and add sign if needed and return the result
        String finalstr=removezeroesatstart(finalStr);
        finalstr=sign+finalstr;
        return finalstr;
    }

    public AFloat sub(AFloat string2){

        // Taking values from objects
        String str1=this.strvalue;
        String str2=string2.strvalue;

        //Check and store signs
        boolean sign1=false;
        boolean sign2=false;
        if (str1.charAt(0)=='-') {
            sign1=true;
        }
        if (str2.charAt(0)=='-') {
            sign2=true;
        }

        //If negative sign is present then remove it.
        if (sign1) {
            str1=str1.substring(1);
        }
        if (sign2) {
            str2=str2.substring(1);
        }

        //remove leading zeroes
        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

        //Ensure decimal point exists
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);

        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        //Taking decimal part from string
        String ss1=str1.substring(a+1,str1.length());
        String ss2=str2.substring(b+1,str2.length());

        int k=0;
        // Equalize decimal part lengths by appending zeroes at last
        if (ss1.length()>ss2.length()){
            k=ss1.length();
        }
        else{
            k=ss2.length();
        }
        while (ss1.length()<k) {
            ss1+="0";
        }
        while (ss2.length()<k) {
            ss2+="0";
        }
        
        // combine integer and fractional parts without the decimal
        str1=str1.substring(0,a)+ss1;
        str2=str2.substring(0,b)+ss2;
        String str="";

        // Handle signs and perform addition/subtraction correctly
        if (sign1&&!sign2) {
            str=("-"+addition(str1,str2));
        } 
        else if (!sign1&&sign2) {
            str=addition(str1,str2);
        } 
        else if (sign1&&sign2) {
            str=subtract(str2,str1);
        } 
        else {
            str=subtract(str1,str2);
        }

        // Make result has enough digits before placing the decimal
        while (str.length()<=k) {
            str="0"+str;
        }
        str=str.substring(0,str.length()-k)+"."+str.substring(str.length()-k);

        if ((str.charAt(0)=='-')&&(str.charAt(1)=='.')){
            str="-0"+str.substring(1);
        }
        if (str.equals("-0.0")){
            str="0.0";
        }

        // Remove zeroes at end of string and limit to 30 decimals precision
        str=removezeroesatend(str);
        return new AFloat(thirtydecimals(str));
    }

    //Make the string precise upto 30 decimal places
    public String thirtydecimals(String str){
         int index=str.indexOf('.');
         String str1=str.substring(0, index);
         String str2=str.substring(index);
         if (str2.length()>30){
            str2=str2.substring(0,31);
         }
         return str1+str2;
    }

    public AFloat div(AFloat string2){

        // Taking values from objects
        String str1=this.strvalue;
        String str2=string2.strvalue;

        //check and store signs
        String signfordiv="";
        if ((str1.charAt(0)=='-')^(str2.charAt(0)=='-')){
            if (str1.charAt(0)=='-'){
                str1=str1.substring(1);
            }
            if (str2.charAt(0)=='-'){
                str2=str2.substring(1);
            }
            signfordiv="-";
        }

        //If negative sign is present then remove it.
        if ((str1.charAt(0)=='-')&&(str2.charAt(0)=='-')){
            str1=str1.substring(1);
            str2=str2.substring(1);
        }

        //Remove leading zeroes
        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

        //Ensure decimal point exists
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);

        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        // Count digits after decimal
        int numa=str1.length()-1-a;
        int numb=str2.length()-1-b;

        // Remove decimal point from string
        str1=str1.substring(0,a)+str1.substring(a+1);
        str2=str2.substring(0,b)+str2.substring(b+1);

        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

        // Normalize number of digits after decimal
        //Like if needed appending zeroes 
        if (numa>numb){
            int diff1=numa-numb;
            while(diff1>0){
                str2+="0";
                diff1--;
            }
        }
        else if (numa<numb){
            int diff2=numb-numa;
            while(diff2>0){
                str1+="0";
                diff2--;
            }
        }

        //Check for division by zero and throw error if divisor is zero.
        if ((removezeroesatend(removezeroesatstart(str2))).equals("0")||(removezeroesatend(removezeroesatstart(str2))).equals("0.0")){
            throw new ArithmeticException("Division by zero");
        }
        String str="";
        String temp="";

        // Do long division for integer part
        for (int j=0;j<str1.length();j++){
            int count=0;
            temp+=str1.charAt(j);
            temp=removezeroesatstart(temp);
            while (less(temp,str2).equals(str2)){
                    temp=subtract(temp,str2);
                    count+=1;
            }
            str+=Integer.toString(count);
        }
        str+=".";
        temp+="0";

        //Making precision to 30 decimals 
        int precision=30;
        if (str2.length()>30){
            precision=str2.length();
        }

        // Do long division to calculate decimal digits
        for (int i=0;i<precision;i++) {
            temp=removezeroesatstart(temp);
            int count2=0;
            while (less(temp,str2).equals(str2)) {
                temp=subtract(temp,str2);
                count2++;
            }
            temp+="0";
            str+=Integer.toString(count2);
        }

        //Remove zeroes at end of string
        str=removezeroesatend(str);
        str=removezeroesatstart(str);
        if (str.charAt(0)=='.'){
            str="0"+str;
        }
        if (str.equals("0.0")||str.equals("0")){
            signfordiv="";
        }

        //Making the string precise upto 30 decimals and adding sign and return the result.
        return new AFloat(signfordiv+thirtydecimals(str));
    }
}



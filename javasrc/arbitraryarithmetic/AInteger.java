package arbitraryarithmetic;
public class AInteger{
    public String strvalue;
    /**
     * Default constructor initializes the value to "0".
     */
    public AInteger(){
        this.strvalue="0";
    }

    /**
     * Constructs an AInteger from string and removes zeroes at starting.
     * @param str Number 
     */
    public AInteger(String str) {
        if (str.charAt(0)=='-') {
            this.strvalue="-"+removezeroes(str.substring(1));
        }
        else{
            this.strvalue=removezeroes(str);
        }
    }

    /**
     * Copy constructor
     * @param other another instance of AInteger
     */
    public AInteger(AInteger other){
        this.strvalue=other.strvalue;
    }

    /**
     * Removes zeroes from the starting of a string
     * @param str string
     * @return string without leading zeroes
     */
    public String removezeroes(String str){
        int i=0;
        while (i<str.length()) {
            if (str.charAt(i)!='0'){
                break;
            }
            i+=1;
        }
        if (i==str.length()) {
            return "0";
        }
        else{
            return str.substring(i,str.length());
        }
    }

    /**
     * Determines the sign of the result of str1 - str2
     * @param str1 first number string
     * @param str2 second number string
     * @return "-" if str1<str2 otherwise empty string ""
     */
    public String Sign(String str1,String str2){
        String sign="";
        if (str1.length()<str2.length()){
            sign="-";
        }
        else if(str1.length()==str2.length()) {
            int i=0;
            while (i<str1.length()) {
                if (str1.charAt(i)!=str2.charAt(i)) {
                    if (str1.charAt(i)<str2.charAt(i)){
                        sign="-";
                    }
                    break;
                }
                i+=1;
            }
        }
        return sign;
    }

    /**
     * Returns the lesser of two strings
     * @param str1 first number string
     * @param str2 second number string
     * @return smaller string
     */
    public String less(String str1, String str2) {
        if (str1.length()==str2.length()) {
            int index=0;
            while (index<str1.length()) {
                if (str1.charAt(index)!=str2.charAt(index)) {
                    break;
                }
                index++;
            }
            if (index==str2.length()) {
                return str2;
            }
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
    
    /**
     * Parses a string into an AInteger object
     * @param str string to parse
     * @return AInteger object
     */
    public static AInteger parse(String str) {
        return new AInteger(str);
    }  

    public String toString() {
        return this.strvalue;
    }

    
    /**
     * Adds two numeric strings
     * @param str1 first number string
     * @param str2 second number string
     * @return sum as string
     */
    public String addition(String str1,String str2) {
        int extra=0;
        String finalStr="";
        while (!str1.isEmpty()||!str2.isEmpty()) {
            int num1=0,num2=0;
            if (!str1.isEmpty()){
                num1=Integer.parseInt(str1.charAt(str1.length()-1)+"");
                str1=str1.substring(0,str1.length()-1);
            }
    
            if (!str2.isEmpty()){
                num2= Integer.parseInt(str2.charAt(str2.length()-1)+"");
                str2=str2.substring(0,str2.length()-1);
            }
            int sum=num1+num2+extra;
            finalStr=Integer.toString(sum%10)+finalStr;
            extra=sum/10;
        }
    
        if (extra>0) {
            finalStr=Integer.toString(extra)+finalStr;
        }
        return removezeroes(finalStr);
    }

    /**
     * Subtracts two numeric strings 
     * @param str1 first number string
     * @param str2 second number string
     * @return difference as string with sign if str1<str2
     */
    public String subtract(String str1,String str2){
        String finalstr="";
        String sign=Sign(str1,str2);
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
                String temp=str1;
                str1=str2;
                str2=temp;
            }
        }
        else if(str1.length()<str2.length()) {
            String temp=str1;
            str1=str2;
            str2=temp;
        }
        while (!str1.isEmpty()||!str2.isEmpty()) {
            int num1=0,num2=0;

            if (!str1.isEmpty()) {
                num1=Integer.parseInt(str1.charAt(str1.length()-1)+"");
                str1=str1.substring(0,str1.length()-1);
            }

            if (!str2.isEmpty()) {
                num2=Integer.parseInt(str2.charAt(str2.length()-1)+"");
                str2=str2.substring(0,str2.length()-1);
            }

            if (num1<num2) {
                int len=str1.length()-1;
                while (len>=0) {
                    if (str1.charAt(len)!='0') {
                        char borrowed=(char)(str1.charAt(len)-1);
                        str1=str1.substring(0,len)+borrowed+str1.substring(len+1);
                        break;
                    }
                    else {
                        str1=str1.substring(0,len)+'9'+str1.substring(len+1);
                        len-=1;
                    }
                }
                num1+=10;
            }
            int diff=num1-num2;
            finalstr=Integer.toString(diff)+finalstr;
        }
        finalstr=removezeroes(finalstr);
        return sign+finalstr;
    }

    /**
     * Adds another AInteger to the current instance
     * Handles negative sign properly based on the input strings
     * @param string2 another instance of AInteger to add
     * @return sum as AInteger
     */
    public AInteger add(AInteger string2) {
        String str1=this.strvalue;
        String str2=string2.strvalue;
        boolean sign1=false;
        boolean sign2=false;
        if (str1.charAt(0)=='-') {
            sign1=true;
        }
        if (str2.charAt(0)=='-') {
            sign2=true;
        }
        if (sign1) {
            str1=str1.substring(1);
        }
        if (sign2) {
            str2=str2.substring(1);
        }
    
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
    
    /**
     * Subtracts another AInteger from the current instance
     * Handles negative sign properly based on the input strings
     * @param string2 another instance of AInteger to subtact
     * @return difference as AInteger
     */
    public AInteger sub(AInteger string2) {
        String str1=this.strvalue;
        String str2=string2.strvalue;
        boolean sign1=false;
        boolean sign2=false;
        if (str1.charAt(0)=='-') {
            sign1=true;
        }
        if (str2.charAt(0)=='-') {
            sign2=true;
        }
        if (sign1) {
            str1=str1.substring(1);
        }
        if (sign2) {
            str2=str2.substring(1);
        }
    
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
    
    
    /**
     * Mutiples two numeric strings
     * @param str1 first number string
     * @param str2 second number string
     * @return product as string
     */
    public String multiply(String str1,String str2) {
        String str="0";
        for (int i=str2.length()-1;i>=0;i--) {
            int num2=Integer.parseInt(str2.charAt(i)+"");
            String temp="";
            int extra=0;
            
            for (int j=str1.length()-1;j>=0;j--) {
                int num1=Integer.parseInt(str1.charAt(j)+"");
                int product=(num1*num2)+extra;
                temp=Integer.toString(product%10)+temp;
                extra=product/10;
            }
            if (extra!=0) {
                temp=Integer.toString(extra)+temp;
            }
            for (int z=0;z<str2.length()-1-i;z++) {
                temp+="0";
            }
            str=addition(str,temp);
        }
        return removezeroes(str);
    }

    /**
     * Multiplies another AInteger to the current instance
     * Handles negative sign properly based on the input strings
     * @param string2 another instance of AInteger to mutiply
     * @return product as AInteger
     */
    public AInteger mul(AInteger string2) {
        String str1=this.strvalue;
        String str2=string2.strvalue;
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
        if ((str1.charAt(0)=='-')&&(str2.charAt(0)=='-')){
            str1=str1.substring(1); 
            str2=str2.substring(1);
        }
        String result=multiply(str1, str2);
        if (result.equals("0")){
            return new AInteger(result);
        }
        else{
            return new AInteger(signformul+result);
        }
    }

    /**
     * Divides the current AInteger instance by another AInteger
     * Handles negative sign properly based on the input strings
     * @param string2 divisor as another instance of AInteger
     * @return quotient as AInteger
     */
    public AInteger div(AInteger string2){
        String str1=this.strvalue;
        String str2=string2.strvalue;
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
        if ((str1.charAt(0)=='-')&&(str2.charAt(0)=='-')){
            str1=str1.substring(1);
            str2=str2.substring(1);
        }

        if ((removezeroes(str2)).equals("0")){
            throw new ArithmeticException("Division by zero");
        }
        String str="";
        String temp="";
        for (int j=0;j<str1.length();j++){
            int count=0;
            temp+=str1.charAt(j);
            temp=removezeroes(temp);
            while (less(temp,str2).equals(str2)){
                    temp=subtract(temp,str2);
                    count+=1;
            }
            str+=Integer.toString(count);
        }
        str=removezeroes(str);
        if (str.equals("0")){
            return new AInteger(str);
        }
        else{
            return new AInteger(signfordiv+str);
        }
    }
}

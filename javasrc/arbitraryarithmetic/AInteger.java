package arbitraryarithmetic;
public class AInteger{
    public String string1;
    /**
     * Default constructor initializes the value to "0".
     */
    public AInteger(){
        this.string1="0";
    }

    /**
     * Constructs an AInteger from string and removes zeroes at starting.
     * @param s Number 
     */
    public AInteger(String s) {
        if (s.charAt(0)=='-') {
            this.string1="-"+removezeroes(s.substring(1));
        }
        else{
            this.string1=removezeroes(s);
        }
    }

    /**
     * Copy constructor
     * @param other another instance of AInteger
     */
    public AInteger(AInteger other){
        this.string1=other.string1;
    }

    /**
     * Removes zeroes from the starting of a string
     * @param s string
     * @return string without leading zeroes
     */
    public String removezeroes(String s){
        int i=0;
        while (i<s.length()) {
            if (s.charAt(i)!='0'){
                break;
            }
            i+=1;
        }
        if (i==s.length()) {
            return "0";
        }
        else{
            return s.substring(i,s.length());
        }
    }

    /**
     * Determines the sign of the result of s1 - s2
     * @param s1 first number string
     * @param s2 second number string
     * @return "-" if s1<s2 otherwise empty string ""
     */
    public String Sign(String s1,String s2){
        String sign="";
        if (s1.length()<s2.length()){
            sign="-";
        }
        else if(s1.length()==s2.length()) {
            int i=0;
            while (i<s1.length()) {
                if (s1.charAt(i)!=s2.charAt(i)) {
                    if (s1.charAt(i)<s2.charAt(i)){
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
     * @param s1 first number string
     * @param s2 second number string
     * @return smaller string
     */
    public String less(String s1, String s2) {
        if (s1.length()==s2.length()) {
            int i=0;
            while (i<s1.length()) {
                if (s1.charAt(i)!=s2.charAt(i)) {
                    break;
                }
                i++;
            }
            if (i==s2.length()) {
                return s2;
            }
            else if (s1.charAt(i)<s2.charAt(i)) {
                return s1;
            }
            else {
                return s2;
            }
        } 
        else if (s1.length()<s2.length()) {
            return s1;
        } 
        else {
            return s2;
        }
    }
    
    /**
     * Parses a string into an AInteger object
     * @param s string to parse
     * @return AInteger object
     */
    public static AInteger parse(String s) {
        return new AInteger(s);
    }  

    public String toString() {
        return this.string1;
    }

    
    /**
     * Adds two numeric strings
     * @param s1 first number string
     * @param s2 second number string
     * @return sum as string
     */
    public String addition(String s1,String s2) {
        int extra=0;
        String finalStr="";
        while (!s1.isEmpty()||!s2.isEmpty()) {
            int num1=0,num2=0;
            if (!s1.isEmpty()){
                num1=Integer.parseInt(s1.charAt(s1.length()-1)+"");
                s1=s1.substring(0,s1.length()-1);
            }
    
            if (!s2.isEmpty()){
                num2= Integer.parseInt(s2.charAt(s2.length()-1)+"");
                s2=s2.substring(0,s2.length()-1);
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
     * @param s1 first number string
     * @param s2 second number string
     * @return difference as string with sign if s1<s2
     */
    public String subtract(String s1,String s2){
        String finalstr="";
        String sign=Sign(s1,s2);
        if(s1.length()==s2.length()) {
            int i=0;
            while (i<s1.length()) {
                if (s1.charAt(i)!=s2.charAt(i)) {
                    break;
                }
                i+=1;
            }
            if (i==s1.length()) {
                return "0";
            } 
            else if (s1.charAt(i)<s2.charAt(i)) {
                String temp=s1;
                s1=s2;
                s2=temp;
            }
        }
        else if(s1.length()<s2.length()) {
            String temp=s1;
            s1=s2;
            s2=temp;
        }
        while (!s1.isEmpty()||!s2.isEmpty()) {
            int num1=0,num2=0;

            if (!s1.isEmpty()) {
                num1=Integer.parseInt(s1.charAt(s1.length()-1)+"");
                s1=s1.substring(0,s1.length()-1);
            }

            if (!s2.isEmpty()) {
                num2=Integer.parseInt(s2.charAt(s2.length()-1)+"");
                s2=s2.substring(0,s2.length()-1);
            }

            if (num1<num2) {
                int i=s1.length()-1;
                while (i>=0) {
                    if (s1.charAt(i)!='0') {
                        char borrowed=(char)(s1.charAt(i)-1);
                        s1=s1.substring(0,i)+borrowed+s1.substring(i+1);
                        break;
                    }
                    else {
                        s1=s1.substring(0,i)+'9'+s1.substring(i+1);
                        i-=1;
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
        String s1=this.string1;
        String s2=string2.string1;
        boolean sign1=false;
        boolean sign2=false;
        if (s1.charAt(0)=='-') {
            sign1=true;
        }
        if (s2.charAt(0)=='-') {
            sign2=true;
        }
        if (sign1) {
            s1=s1.substring(1);
        }
        if (sign2) {
            s2=s2.substring(1);
        }
    
        if (sign1&&sign2) {
            return new AInteger("-"+addition(s1,s2));
        } 
        else if (sign1) {
            return new AInteger(subtract(s2,s1));
        } 
        else if (sign2) {
            return new AInteger(subtract(s1,s2));
        } 
        else {
            return new AInteger(addition(s1,s2));
        }
    }
    
    /**
     * Subtracts another AInteger from the current instance
     * Handles negative sign properly based on the input strings
     * @param string2 another instance of AInteger to subtact
     * @return difference as AInteger
     */
    public AInteger sub(AInteger string2) {
        String s1=this.string1;
        String s2=string2.string1;
        boolean sign1=false;
        boolean sign2=false;
        if (s1.charAt(0)=='-') {
            sign1=true;
        }
        if (s2.charAt(0)=='-') {
            sign2=true;
        }
        if (sign1) {
            s1=s1.substring(1);
        }
        if (sign2) {
            s2=s2.substring(1);
        }
    
        if (sign1&&!sign2) {
            return new AInteger("-"+addition(s1,s2));
        } 
        else if (!sign1&&sign2) {
            return new AInteger(addition(s1,s2));
        } 
        else if (sign1&&sign2) {
            return new AInteger(subtract(s2,s1));
        } 
        else {
            return new AInteger(subtract(s1,s2));
        }
    }
    
    
    /**
     * Mutiples two numeric strings
     * @param s1 first number string
     * @param s2 second number string
     * @return product as string
     */
    public String multiply(String s1,String s2) {
        String s="0";
        for (int i=s2.length()-1;i>=0;i--) {
            int num2=Integer.parseInt(s2.charAt(i)+"");
            String a="";
            int extra=0;
            
            for (int j=s1.length()-1;j>=0;j--) {
                int num1=Integer.parseInt(s1.charAt(j)+"");
                int product=(num1*num2)+extra;
                a=Integer.toString(product%10)+a;
                extra=product/10;
            }
            if (extra!=0) {
                a=Integer.toString(extra)+a;
            }
            for (int z=0;z<s2.length()-1-i;z++) {
                a+="0";
            }
            s=addition(s,a);
        }
        return removezeroes(s);
    }

    /**
     * Multiplies another AInteger to the current instance
     * Handles negative sign properly based on the input strings
     * @param string2 another instance of AInteger to mutiply
     * @return product as AInteger
     */
    public AInteger mul(AInteger string2) {
        String s1=this.string1;
        String s2=string2.string1;
        String signformul="";
        if ((s1.charAt(0)=='-')^(s2.charAt(0)=='-')){
            if (s1.charAt(0)=='-'){
                s1=s1.substring(1);
            }
            if (s2.charAt(0)=='-'){
                s2=s2.substring(1);
            }
            signformul="-";
            }
        if ((s1.charAt(0)=='-')&&(s2.charAt(0)=='-')){
            s1=s1.substring(1); 
            s2=s2.substring(1);
        }
        String result=multiply(s1, s2);
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
        String s1=this.string1;
        String s2=string2.string1;
        String signfordiv="";
        if ((s1.charAt(0)=='-')^(s2.charAt(0)=='-')){
            if (s1.charAt(0)=='-'){
                s1=s1.substring(1);
            }
            if (s2.charAt(0)=='-'){
                s2=s2.substring(1);
            }
            signfordiv="-";
        }
        if ((s1.charAt(0)=='-')&&(s2.charAt(0)=='-')){
            s1=s1.substring(1);
            s2=s2.substring(1);
        }

        if ((removezeroes(s2)).equals("0")){
            throw new ArithmeticException("Division by zero");
        }
        String s="";
        String temp="";
        for (int j=0;j<s1.length();j++){
            int count=0;
            temp+=s1.charAt(j);
            temp=removezeroes(temp);
            while (less(temp,s2).equals(s2)){
                    temp=subtract(temp,s2);
                    count+=1;
            }
            s+=count;
        }
        s=removezeroes(s);
        if (s.equals("0")){
            return new AInteger(s);
        }
        else{
            return new AInteger(signfordiv+s);
        }
    }
}

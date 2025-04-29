package arbitraryarithmetic;
public class AFloat{
    public String string1;
    public AFloat(){
        this.string1="0";
    }
    public AFloat(String str) {
        this.string1=str;
    }
    public AFloat(AFloat other){
        this.string1=other.string1;
    }
    public String removezeroesatstart(String str){
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
            str=str.substring(i,str.length());
        }
        if (str.charAt(0)=='.'){
            str="0"+str;
        }
        return str;
    }
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
    public int decimalindex(String str){
        int a=0;
        while (a<str.length()){
            if (str.charAt(a)=='.'){
                break;
            }
            a++;
        }
        return a;
    }
    public String addzeroes(int a,String str){
        if (a==str.length()){
            str=str+".0";
        }
        return str;
    }
    public String removezeroesatend(String str){
        if (str.contains(".")) {
            int i=(str.length()-1);
            if (str.charAt(i)=='0'){
                while (str.charAt(i)=='0'){
                    i-=1;
                }
            }
            str=str.substring(0, i+1);
            if (str.charAt(i)=='.'){
                str+='0';
            }
        }
        return str;
    }
    public String less(String str1, String str2) {
        if (str1.length()==str2.length()) {
            int i=0;
            while (i<str1.length()) {
                if (str1.charAt(i)!=str2.charAt(i)) {
                    break;
                }
                i++;
            }
            if (i==str2.length()) {
                return str2;
            }
            else if (str1.charAt(i)<str2.charAt(i)) {
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
    public static AFloat parse(String str) {
        return new AFloat(str);
    }    
    public String toString() {
        return this.string1;
    }

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
        return removezeroesatstart(finalStr);
    }
    
    public AFloat add(AFloat string2){
        String str1=this.string1;
        String str2=string2.string1;

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

        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

        int a=decimalindex(str1);
        str1=addzeroes(a,str1);

        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        String ss1=str1.substring(a+1,str1.length());
        String ss2=str2.substring(b+1,str2.length());
        int k=0;
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
        
        str1=str1.substring(0,a)+ss1;
        str2=str2.substring(0,b)+ss2;
        String str="";
    
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

        str=removezeroesatend(str);
        return new AFloat(thirtydecimals(str));
    }
    public String multiply(String str1,String str2) {
        String str="0";
        
        for (int i=str2.length()-1;i>=0;i--) {
            int num2=Integer.parseInt(str2.charAt(i)+"");
            String a="";
            int extra=0;
            
            for (int j=str1.length()-1;j>=0;j--) {
                int num1=Integer.parseInt(str1.charAt(j)+"");
                int product=(num1*num2)+extra;
                a=Integer.toString(product%10)+a;
                extra=product/10;
            }
            if (extra!=0) {
                a=Integer.toString(extra)+a;
            }
            for (int z=0;z<str2.length()-1-i;z++) {
                a+="0";
            }
            str=addition(str,a);
        } 
        return removezeroesatstart(str);
    }
    public AFloat mul(AFloat string2){
        String str1=this.string1;
        String str2=string2.string1;
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
        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);
        
        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        String ss1=str1.substring(a+1,str1.length());
        String ss2=str2.substring(b+1,str2.length());
        
        str1=str1.substring(0,a)+ss1;
        str2=str2.substring(0,b)+ss2;
        String str="";
        str=multiply(str1,str2);
        int k=ss1.length()+ss2.length();
        while (str.length()<=k) {
            str="0"+str;
        }
        str=str.substring(0,str.length()-k)+"."+str.substring(str.length()-k,str.length());
        str=removezeroesatend(str);
        if (str.equals("0.0")||str.equals("0")){
            signformul="";
        }

        return new AFloat(signformul+thirtydecimals(str));
    }
    public String subtract(String str1,String str2){
        String finalStr="";
        String sign=Sign(str1,str2);
        if(str1.length()==str2.length()) {
            int i=0;
            while (i<str1.length()) {
                if (str1.charAt(i)!=str2.charAt(i)) {
                    break;
                }
                i+=1;
            }
            if (i==str1.length()) {
                return "0";
            } 
            else if (str1.charAt(i)<str2.charAt(i)) {
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
                int i=str1.length()-1;
                while (i>=0) {
                    if (str1.charAt(i)!='0') {
                        char borrowed=(char)(str1.charAt(i)-1);
                        str1=str1.substring(0,i)+borrowed+str1.substring(i+1);
                        break;
                    }
                    else {
                        str1=str1.substring(0,i)+'9'+str1.substring(i+1);
                        i-=1;
                    }
                }
                num1+=10;
            }
            int diff=num1-num2;
            finalStr=Integer.toString(diff)+finalStr;
        }
        String finalstr=removezeroesatstart(finalStr);
        finalstr=sign+finalstr;
        return finalstr;
    }

    public AFloat sub(AFloat string2){
        String str1=this.string1;
        String str2=string2.string1;
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
    
        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);

        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        String ss1=str1.substring(a+1,str1.length());
        String ss2=str2.substring(b+1,str2.length());
        int k=0;
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
        
        str1=str1.substring(0,a)+ss1;
        str2=str2.substring(0,b)+ss2;
        String str="";
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
        str=removezeroesatend(str);
        return new AFloat(thirtydecimals(str));
    }
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
        String str1=this.string1;
        String str2=string2.string1;
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
        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);
        int a=decimalindex(str1);
        str1=addzeroes(a,str1);

        int b=decimalindex(str2);
        str2=addzeroes(b,str2);

        int numa=str1.length()-1-a;
        int numb=str2.length()-1-b;
        str1=str1.substring(0,a)+str1.substring(a+1);
        str2=str2.substring(0,b)+str2.substring(b+1);

        str1=removezeroesatstart(str1);
        str2=removezeroesatstart(str2);

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
        if ((removezeroesatend(removezeroesatstart(str2))).equals("0")||(removezeroesatend(removezeroesatstart(str2))).equals("0.0")){
            throw new ArithmeticException("Division by zero");
        }
        String str="";
        String temp="";
        for (int j=0;j<str1.length();j++){
            int count=0;
            temp+=str1.charAt(j);
            temp=removezeroesatstart(temp);
            while (less(temp,str2).equals(str2)){
                    temp=subtract(temp,str2);
                    count+=1;
            }
            str+=count;
        }
        str+=".";
        temp+="0";
        int precision = 1000;
        for (int i=0;i<precision;i++) {
            temp=removezeroesatstart(temp);
            int count2=0;
            while (less(temp,str2).equals(str2)) {
                temp=subtract(temp,str2);
                count2++;
            }
            temp+="0";
            str+=count2;
        }
        str=removezeroesatend(str);
        str=removezeroesatstart(str);
        if (str.charAt(0)=='.'){
            str="0"+str;
        }
        if (str.equals("0.0")||str.equals("0")){
            signfordiv="";
        }
        return new AFloat(signfordiv+thirtydecimals(str));
    }
}



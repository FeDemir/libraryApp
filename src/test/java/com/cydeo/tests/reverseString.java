package com.cydeo.tests;

import java.util.Arrays;

public class reverseString {
    public static void main(String[] args) {
        System.out.println("reserse = " + reserse("java"));
        System.out.println("reverseWords = " + reverseWords("Hadi bakalim bu cumleyi cevir"));
        //finra(30);
        String str= "jav45ai15sgre1at82";
        String[] arr = str.split("\\D+");
        System.out.println(Arrays.toString(arr));
        int total=0;
        for (String st:arr){
            if (st.equals("")) continue;
            total+=Integer.parseInt(st);
        }
        System.out.println("total = " + total);
    }

    private static void finra(int i) {
        for (int j = 1; j <=i; j++) {
            if (j%15==0) System.out.println("finra");
            else if (j%3==0) System.out.println("fin");
            else if (j%5==0) System.out.println("ra");
            else  System.out.println(j);
            

        }
    }

    public static String reverseWords(String str) {
        String[] arr = str.split(" ");
        String text="";
        for (String st:arr) {
           text=reserse(st)+" "+text;
        }
        return text.substring(0,text.length()-1);
    }


    public static String reserse(String java) {
        return new StringBuilder(java).reverse().toString();
    }
}

package com.example.codebase.utils;

import java.util.*;

public class CrackingCodingInterview {
    public static void main(String[] args){
        var func = new CrackingCodingInterview();
        System.out.print(func.replaceString("abc sampel"));
    }

    //Is Unique: Implement an algorithm to determine if a string has all unique characters.
    // What if you cannot use additional data structures?
    public boolean isUnique(String str){
        for(int i = 0; i<str.length()-1;i++){
            for(int j = i+1;j<str.length();j++){
                if(str.charAt(i) == str.charAt(j))
                    return false;
            }
        }
        return true;
    }

    //Check Permutation: Given two strings,
    // write a method to decide if one is a permutation of the other
    public boolean checkPermutation(String str1, String str2){
        if (str1 == null || str1.length() != str2.length()){
            return false;
        }
        Map<Character, Integer> counter1 = new HashMap<>();
        Map<Character, Integer> counter2 = new HashMap<>();

        for (char c : str1.toCharArray()) {
            counter1.put(c, counter1.getOrDefault(c, 0) + 1);
        }

        for (char c : str2.toCharArray()) {
            counter2.put(c, counter2.getOrDefault(c, 0) + 1);
        }

        return counter1.equals(counter2);
    }

//    Write a method to replace all spaces in a string with '%20'. You may assume that the string
//    has sufficient space at the end to hold the additional characters, and that you are given the "true"
//    length of the string. (Note: If implementing in Java, please use a character array so that you can
//    perform this operation in place.)
    public String replaceString(String str){
        char[] strArray = str.toCharArray();
        int length = strArray.length;
        for (int i = 0; i<strArray.length; i++){
            if(str.charAt(i) == ' ')
                length += 2;
        }
        char[] res = new char[length];
        int index  =0;
        for (int i = 0; i<strArray.length; i++){
            if(strArray[i] == ' '){
                res[index] = '%';
                res[index+1] = '2';
                res[index+2] = '0';
                index+=3;
            } else {
                res[index] = strArray[i];
                index+=1;
            }
        }
        return String.valueOf(res);
    }
}

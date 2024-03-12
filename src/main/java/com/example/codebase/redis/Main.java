package com.example.codebase.redis;

public class Main {
    public static void main(String[] args){
        System.out.print(TokenBucketAlgo.limitChecking("abc", 60, 4));

    }
}

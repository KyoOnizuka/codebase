package com.example.codebase.redis;

public class Main {
    public static void main(String[] args){
        var token = new TokenBucketAlgo();
        for (int i = 0; i< 1000; i++){
            boolean res = token.allow("user1", 60, 100);
            if (res) {
                System.out.println("Request " + i + " is allowed");
            } else {
                System.out.println("Request " + i + " is denied");
            }
        }

    }
}

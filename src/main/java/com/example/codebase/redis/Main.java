package com.example.codebase.redis;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var token = new TokenBucketAlgo();
        int z = 0;
        while (true) {
            z+=1;
            for (int i = 0; i< 1000; i++){
                boolean res = token.allow("user1", 1, 100);
                if (res) {
                    System.out.println("Request " + i + " is allowed");
                } else {
                    System.out.println("Request " + i + " is denied");
                }
            }
            Thread.sleep(2000);
            if (z == 1000){
                break;
            }
        }
    }
}

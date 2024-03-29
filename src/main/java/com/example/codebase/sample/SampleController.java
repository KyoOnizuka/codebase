package com.example.codebase.sample;

import com.example.codebase.redis.LeakingBucketAlgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private final Environment environment;
    @Autowired
    LeakingBucketAlgo myBean;

    public SampleController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/sample")
    public String sample(){
        /*StringBuilder builder = new StringBuilder();
        for(int i = 0; i<1000 ; i++){
            builder.append("Success ");
        }*/
        return environment.getProperty("active.profile");
    }
}

package com.L.org.n3r.idworker;

import org.springframework.stereotype.Component;

@Component
public class Sid {

    public String nextShort(){
        return Long.toString(System.currentTimeMillis(), 36) + Long.toString((long)(Math.random() * 1296), 36);
    }

}

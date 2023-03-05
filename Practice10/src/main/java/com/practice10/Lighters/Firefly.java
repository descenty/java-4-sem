package com.practice10.Lighters;

import org.springframework.stereotype.Component;

public class Firefly implements Lighter {
    @Override
    public void doLight() {
        System.out.println("Firefly is lighting");
    }
}
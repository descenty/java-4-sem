package com.practice10.Lighters;

import org.springframework.stereotype.Component;

public class Flashlight implements Lighter {
    @Override
    public void doLight() {
        System.out.println("Flashlight is lighting");
    }
}

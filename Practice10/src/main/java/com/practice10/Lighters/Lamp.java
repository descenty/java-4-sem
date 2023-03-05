package com.practice10.Lighters;

import org.springframework.stereotype.Component;

public class Lamp implements Lighter {
    @Override
    public void doLight() {
        System.out.println("Lamp is lighting");
    }
}

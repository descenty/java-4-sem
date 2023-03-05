package com.practice10;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.practice10.Lighters.Firefly;
import com.practice10.Lighters.Flashlight;
import com.practice10.Lighters.Lamp;

@Configuration
public class LighterConfig {
    @Bean(name = "lamp")
    public Lamp getLamp() {
        return new Lamp();
    }

    @Bean(name = "flashlight")
    public Flashlight getFlashlight() {
        return new Flashlight();
    }

    @Bean(name = "firefly")
    public Firefly getFirefly() {
        return new Firefly();
    }
}
package com.example.demo.Data;

import javax.persistence.Entity;

@Entity
public class Shrub {
    private int heightCm;
    private int widthCm;

    public int getHeightCm() {
        return heightCm;
    }

    public int getWidthCm() {
        return widthCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    public void setWidthCm(int widthCm) {
        this.widthCm = widthCm;
    }
}

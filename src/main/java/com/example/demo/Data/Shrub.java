package com.example.demo.Data;

import javax.persistence.Entity;

@Entity
public class Shrub {
    private int heightCm;
    private int widthCm;

    public int getHeightCm() {
        return heightCm;
    }

    public Shrub(int heightCm, int widthCm) {
        this.heightCm = heightCm;
        this.widthCm = widthCm;
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

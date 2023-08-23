package com.dermont.ResidentialInfo;

public class UsableAreaSpace {
    private double usableAreaLength;
    private double usableAreaWidth;
    private double usableAreaHeight;
    private double usableAreaCapacity;


    public UsableAreaSpace(double usableAreaLength, double usableAreaWidth, double usableAreaHeight) {
        this.usableAreaLength = usableAreaLength;
        this.usableAreaWidth = usableAreaWidth;
        this.usableAreaHeight = usableAreaHeight;
        this.usableAreaCapacity = usableAreaHeight * usableAreaLength * usableAreaWidth;
    }

    public UsableAreaSpace(double usableAreaCapacity) {
        this.usableAreaCapacity = usableAreaCapacity;
    }

    public double getUsableAreaLength() {
        return usableAreaLength;
    }

    public void setUsableAreaLength(double usableAreaLength) {
        this.usableAreaLength = usableAreaLength;
    }

    public double getUsableAreaWidth() {
        return usableAreaWidth;
    }

    public void setUsableAreaWidth(double usableAreaWidth) {
        this.usableAreaWidth = usableAreaWidth;
    }

    public double getUsableAreaHeight() {
        return usableAreaHeight;
    }

    public void setUsableAreaHeight(double usableAreaHeight) {
        this.usableAreaHeight = usableAreaHeight;
    }

    public double getUsableAreaCapacity() {
        return usableAreaCapacity;
    }

    public void setUsableAreaCapacity(double usableAreaCapacity) {
        this.usableAreaCapacity = usableAreaCapacity;
    }
}

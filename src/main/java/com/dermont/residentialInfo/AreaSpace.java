package com.dermont.residentialInfo;

public class AreaSpace {
    private double Length;
    private double Width;
    private double Height;
    private double Capacity;


    public AreaSpace(double Length, double Width, double Height) {
        this.Length = Length;
        this.Width = Width;
        this.Height = Height;
        this.Capacity = Height * Length * Width;
    }

    public AreaSpace(double Capacity) {
        this.Capacity = Capacity;
    }

    @Override
    public String toString() {
        return "AreaSpace{" +
                "Length=" + Length +
                ", Width=" + Width +
                ", Height=" + Height +
                ", Capacity=" + Capacity +
                '}';
    }

    public double getLength() {
        return Length;
    }

    public void setLength(double length) {
        this.Length = length;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double width) {
        this.Width = width;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        this.Height = height;
    }

    public double getCapacity() {
        return Capacity;
    }

    public void setCapacity(double capacity) {
        this.Capacity = capacity;
    }
}

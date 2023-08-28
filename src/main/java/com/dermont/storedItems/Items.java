package com.dermont.storedItems;

import com.dermont.residentialInfo.AreaSpace;

public class Items {
    private String name;
    private AreaSpace dimensions;

    public Items(String name, AreaSpace dimensions) {
        this.name = name;
        this.dimensions = new AreaSpace(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", dimensions=" + dimensions +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaSpace getDimensions() {
        return dimensions;
    }

    public void setDimensions(AreaSpace dimensions) {
        this.dimensions = dimensions;
    }
}

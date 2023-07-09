package org.example.java.basic._1_copy;

/**
 * @author: whtli
 * @date: 2023/07/08
 * @description:
 */
public class Address implements Cloneable{
    private String name;

    public Address(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

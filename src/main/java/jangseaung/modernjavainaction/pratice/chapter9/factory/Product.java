package jangseaung.modernjavainaction.pratice.chapter9.factory;

public abstract class Product {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
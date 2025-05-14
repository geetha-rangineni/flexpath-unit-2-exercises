package org.example;

public class Parrot extends Animal {
    public Parrot(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return name + " says '" + name + " wants a cracker!'";
    }

    @Override
    public String eat() {
        return name + " is eating a cracker.";
    }

    public String fly() {
        return name + " is flying around the zoo.";
    }
}

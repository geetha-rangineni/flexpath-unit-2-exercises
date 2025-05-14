package org.example;

public class Lion extends Animal {
    public Lion(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return name + " lets out a loud ROAR!";
    }

    @Override
    public String sleep() {
        return super.sleep() + " " + name + " dreams of a delicious steak.";
    }
}

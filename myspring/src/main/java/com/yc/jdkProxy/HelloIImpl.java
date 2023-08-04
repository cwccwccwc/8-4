package com.yc.jdkProxy;

public class HelloIImpl implements HelloI{
    @Override
    public void sayHello() {
        System.out.println("HelloIImpl中的sayHello");
    }

    @Override
    public void showByebye() {
        System.out.println("HelloIImpl中的showByebye");
    }
}

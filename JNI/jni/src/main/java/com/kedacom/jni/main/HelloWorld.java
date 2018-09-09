package com.kedacom.jni.main;

public class HelloWorld {

    public static native String sayHello(String name);

    public static void main(String[] args) {
        String text = sayHello("kedacom");
        System.out.println(text);
    }

    static {
        System.loadLibrary("HelloWorld");
    }
}
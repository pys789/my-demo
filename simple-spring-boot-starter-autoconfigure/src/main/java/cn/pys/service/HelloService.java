package cn.pys.service;

public class HelloService {

    private String name;

    private Integer age;

    private String hometown;

    public HelloService(String name, Integer age, String hometown) {
        this.name = name;
        this.age = age;
        this.hometown = hometown;

    }

    public String sayHello(String name) {
        return "Hello, " + name;
    }

    public String helloWorld() {
        return String.format("[name=%s, age=%d, hometown=%s]", this.name, this.age, this.hometown);
    }



}

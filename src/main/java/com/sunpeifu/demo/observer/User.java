package com.sunpeifu.demo.observer;
// 具体观察者
public class User implements  Observer{

    private String name;

    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message=message;
        System.out.println(name+"收到消息:"+message);
    }
}

package com.sunpeifu.demo.observer;

public class TestMain {
    public static void main(String[] args) {
        User a = new User("小明");
        User b = new User("小李");
        User c = new User("小红");
        WeChat weChat = new WeChat();
        weChat.registerObServer(a);
        weChat.registerObServer(b);
        weChat.registerObServer(c);
        weChat.setInfomation("this is a message");
    }
}

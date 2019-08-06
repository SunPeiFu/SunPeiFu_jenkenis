package com.sunpeifu.demo.observer;

import java.util.ArrayList;
import java.util.List;
/***
 * 被观察者
 */
public class WeChat implements IObServer {

     private List<Observer> observerList ;

     private String message;

    public WeChat() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void registerObServer(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObServer(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyAllObServer() {
        if(!observerList.isEmpty()){
            for (Observer observer : observerList) {
                observer.update(message);
            }
        }
    }
    public void setInfomation(String message){
        this.message=message;
        notifyAllObServer();
    }
}

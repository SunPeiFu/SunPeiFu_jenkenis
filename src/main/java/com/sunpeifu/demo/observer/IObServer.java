package com.sunpeifu.demo.observer;
/***
 *  抽象被观察者接口
 */
public interface IObServer {
    /***
     *  添加观察者
     */
    void registerObServer(Observer observer);

    /***
     *  添加观察者
     */
    void removeObServer(Observer observer);

    /***
     *  通知所有观察者
     */
    void notifyAllObServer();
}

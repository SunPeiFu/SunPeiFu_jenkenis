package com.sunpeifu.demo.observer;
/***
 *  观察者接口,被观察者发生改变,观察者进行相关更新
 */
public interface Observer {
    void update (String message);
}

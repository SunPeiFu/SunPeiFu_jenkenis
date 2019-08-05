package com.sunpeifu.demo.ioc.bean;

public class OriginalBean {

    /***
     *  bean全限定类名
     */
    private String beanClassName;
    /***
     *  beanName
     */
    private String beanFactoryName;
    /***
     *  bean Class对象
     */
    private Class beanClazz;

    // 构造方法中实例化Class


    public OriginalBean(String beanClassName, String beanFactoryName){
        this.beanClassName=beanClassName;
        this.beanFactoryName=beanFactoryName;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.beanClazz=clazz;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getBeanFactoryName() {
        return beanFactoryName;
    }

    public void setBeanFactoryName(String beanFactoryName) {
        this.beanFactoryName = beanFactoryName;
    }

    public Class getBeanClazz() {
        return beanClazz;
    }

    public void setBeanClazz(Class beanClazz) {
        this.beanClazz = beanClazz;
    }




}

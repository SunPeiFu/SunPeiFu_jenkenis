package com.sunpeifu.demo.ioc.core;

import com.sunpeifu.demo.ioc.bean.BeanWrapper;
import com.sunpeifu.demo.ioc.bean.OriginalBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 *  模拟Spring中的核心容器,存储所有原生bean和被代理的bean
 */

public class BeanFactoryContainer {
    /**
     *  实例化的bean容器
     */
    public Map<String, BeanWrapper> instanceBeanMap = new ConcurrentHashMap<>();

    /**
     *  原生bean容器
     */
    public Map<String, OriginalBean> originalBeanMap = new ConcurrentHashMap<>();
    /**
     *  Bean名称的容器
     */
    public List<String> beanNameList = new ArrayList<>();

    public Map<String, BeanWrapper> getInstanceBeanMap() {
        return instanceBeanMap;
    }

    public void setInstanceBeanMap(Map<String, BeanWrapper> instanceBeanMap) {
        this.instanceBeanMap = instanceBeanMap;
    }

    public Map<String, OriginalBean> getOriginalBeanMap() {
        return originalBeanMap;
    }

    public void setOriginalBeanMap(Map<String, OriginalBean> originalBeanMap) {
        this.originalBeanMap = originalBeanMap;
    }

    public List<String> getBeanDefinitionNames() {
        return beanNameList;
    }

    public void setBeanDefinitionNames(List<String> beanDefinitionNames) {
        this.beanNameList  = beanDefinitionNames;
    }
}

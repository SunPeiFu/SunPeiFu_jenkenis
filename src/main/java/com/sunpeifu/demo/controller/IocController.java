package com.sunpeifu.demo.controller;

import com.sunpeifu.demo.ioc.annotation.MyAutowired;
import com.sunpeifu.demo.ioc.bean.BeanWrapper;
import com.sunpeifu.demo.ioc.context.ApplicationContext;
import com.sunpeifu.demo.service.IService;

public class IocController {

    @MyAutowired("IocServiceImpl")
    private IService service;

    public void testService(){
        service.testService();
    }

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        BeanWrapper beanWrapper = (BeanWrapper)context.getBean("IocController");
        IocController controller = (IocController)beanWrapper.getOrginalBean();
        controller.testService();

    }
}

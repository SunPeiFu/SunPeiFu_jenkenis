package com.sunpeifu.demo.ioc.bean;
/***
 *  包装bean
 */
public class BeanWrapper {
    /***
     *  原始bean
     */
    private Object originalBean;
    /***
     *  包装bean
     */
    private Object wrapperBean;

    public BeanWrapper(Object orginalBean) {
        this.originalBean = orginalBean;
        // 原生bean赋值给包装bean
        this.wrapperBean = orginalBean;
    }

    public Object getOrginalBean() {
        return originalBean;
    }

    public void setOrginalBean(Object orginalBean) {
        this.originalBean = orginalBean;
    }

    public Object getWrapperBean() {
        return wrapperBean;
    }

    public void setWrapperBean(Object wrapperBean) {
        this.wrapperBean = wrapperBean;
    }
}

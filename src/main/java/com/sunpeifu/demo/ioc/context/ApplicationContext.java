package com.sunpeifu.demo.ioc.context;

import com.sunpeifu.demo.ioc.core.BeanReader;
import com.sunpeifu.demo.ioc.annotation.MyAutowired;
import com.sunpeifu.demo.ioc.bean.BeanWrapper;
import com.sunpeifu.demo.ioc.bean.OriginalBean;
import com.sunpeifu.demo.ioc.core.BeanFactory;
import com.sunpeifu.demo.ioc.core.BeanFactoryContainer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApplicationContext extends BeanFactoryContainer implements BeanFactory {

    private BeanReader reader;

    private String[] configLocations;

    /***
     * 注册时的单列容器
     */
    private Map<String, Object> cacheBeanMap = new HashMap<>();

    /***
     *  构造方法,传入配置,并且刷新Ioc应用
     */
    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    /***
     * 刷新IOC容器中的所有对象,IOC加载bean的4个步骤 1 定位 2 加载 3 注册 4 依赖注入
     */
    public void refresh() {
        // 定位
        if (reader == null) {
            reader = new BeanReader(configLocations[0]);
        }
        // 加载,过去加载好的bean名称集合
        List<String> originalBeanClassNameList = reader.getOriginalBeanClassNameList();
        // 注册
        doRegister(originalBeanClassNameList);
        // 依赖注入
        doAutowired();
    }

    private void doRegister(List<String> originalBeanClassNameList) {
        for (String beanClassName : originalBeanClassNameList) {
            // 分别添加到包装beanMap 和原生Beanmap中
            try {
                Class<?> clazz = Class.forName(beanClassName);
                if (clazz.isInterface()) {
                    continue;
                }
                OriginalBean originalBean = reader.RegisterOriginalBean(beanClassName);
                if (null == originalBean) {
                    continue;
                }
                // 添加到原生bean容器中
                this.originalBeanMap.put(originalBean.getBeanFactoryName(), originalBean);
                // 把bean的名称添加到容器
                this.beanNameList.add(originalBean.getBeanFactoryName());
                // bean有可能实现了多个接口,,把接口名称都作为key也添加到容器中,通过每个接口名,都能找到这个bean
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    String interfaceName = anInterface.getName();
                    originalBeanMap.put(interfaceName, originalBean);
                    this.beanNameList.add(interfaceName);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void doAutowired() {

        Iterator<Map.Entry<String, OriginalBean>> it = this.originalBeanMap.entrySet().iterator();
        while (it.hasNext()) {
            String beanName = it.next().getKey();
            getBean(beanName);
        }
        Iterator<Map.Entry<String, BeanWrapper>> iterator = this.instanceBeanMap.entrySet().iterator();
        while (iterator.hasNext()) {
            BeanWrapper beanWrapper = iterator.next().getValue();
            // 依赖注入
            populateBean(beanWrapper);
        }

    }

    private void populateBean(BeanWrapper beanWrapper) {
        // 从包装bean中获取原始bean
        Object orginalBean = beanWrapper.getOrginalBean();
        // 获取原始bean clazz对象
        try {
            Class<?> clazz = orginalBean.getClass();
            // 获取clazz中的所有属性
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                // 设置访问权限,反射打破所有封装
                declaredField.setAccessible(true);
                // 获取注解中Autowired的value值,即beanName,从instanceBeanMap中获取值,查看是否可以找到,找到注入设置该字段属性为对应
                // 的class对象反射完成的实例,找不到即设置为null
                MyAutowired myAutowired = declaredField.getAnnotation(MyAutowired.class);
                String beanName = myAutowired.value();
                // 设置对象成员属性值
                declaredField.set(orginalBean,this.instanceBeanMap.get(beanName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        // 先从实例容器中取,返回被包装过的bean
        if (this.instanceBeanMap.containsKey(beanName)) {
            BeanWrapper beanWrapper = instanceBeanMap.get(beanName);
            return beanWrapper;
        }
        OriginalBean originalBean = this.originalBeanMap.get(beanName);
        if (originalBean == null) {
            return null;
        }
        // 进行初始化
        Object beanInstance = initBean(originalBean);
        BeanWrapper beanWrapper = new BeanWrapper(beanInstance);
        this.instanceBeanMap.put(beanName, beanWrapper);
        return beanInstance;

    }

    private Object initBean(OriginalBean originalBean) {
        Object instance = null;
        String beanClassName = originalBean.getBeanClassName();
        try {
            Object o = this.cacheBeanMap.get(originalBean.getBeanFactoryName());
            if (o != null) {
                return o;
            }
            // 容器中没有进行初始化
            Class<?> beanClazz = (Class<?>) originalBean.getBeanClazz();
            instance = beanClazz.newInstance();
            this.cacheBeanMap.put(beanClassName, instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

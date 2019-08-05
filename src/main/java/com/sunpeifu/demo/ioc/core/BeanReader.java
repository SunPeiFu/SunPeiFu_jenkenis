package com.sunpeifu.demo.ioc.core;

import com.sunpeifu.demo.controller.User;
import com.sunpeifu.demo.ioc.bean.OriginalBean;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BeanReader {
    /***
     * 存储Bean的全限定类名
     */
    private List<String> originalBeanClassNameList = new ArrayList<>();

    /***
     *  构造方法
     */
    public BeanReader(String configLocations) {
        // 根据configLocations,读取配置文件

        // 从配置文件中获取配置的basePackage
        String basePackage = "E:\\demo_workspace2\\demo\\src\\main\\java\\com\\sunpeifu\\demo\\ioc";
        // 把bean全限定类名封装到集合中
        scanPackage(basePackage);
    }

    public static void main(String[] args) {
        String basePackage = "E:\\demo_workspace2\\demo\\src\\main\\java\\com\\sunpeifu\\demo\\ioc";

        new BeanReader(null).scanPackage(basePackage);
    }
    /***
     *  扫描基础包下所有类,加入到originalBeanMap中
     */
    public void scanPackage(String baseScanPackage) {
        User obejct = new User();
        // 获取url
        URL url = obejct.getClass().getClassLoader().getResource("/" + baseScanPackage.replaceAll("'\\'", "/"));
        // 获取basicFile
        //String basicFilePath = url.getFile();
        String basicFilePath = "E:\\demo_workspace2\\demo\\src\\main\\java\\com\\sunpeifu\\demo\\ioc";
        // 基础文件
        File basicFile = new File(basicFilePath);
        if (basicFile != null) {
            // 获取该目录下所有文件
            File[] files = basicFile.listFiles();
            if (null != files) {
                // 如果是文件夹继续递归,否则反射加入到orginalBeanMap中
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 递归继续
                        scanPackage(baseScanPackage);
                    } else {
                        // 加入到originalBeanClassNameList,此时只是存储了所有bean的全限定类名,并没有实例化
                        String originalBeanClassName = baseScanPackage + "." + file.getName().replace(".java", "");
                        originalBeanClassNameList.add(originalBeanClassName);
                    }
                }
            }

        }
    }

    /***
     *  初测原始bean
     */
    public OriginalBean RegisterOriginalBean(String beanClassName) {
        if (originalBeanClassNameList.contains(beanClassName)) {
            // 创建原始bean,构造方法中需要两个参数 前者是bean全限定类名,后者为bean名称,默认为类名,
            int index = beanClassName.lastIndexOf(".");
            String beanName = beanClassName.substring(index + 1);
            OriginalBean originalBean = new OriginalBean(beanClassName,beanName);
            return originalBean;
        }
        return null;
    }

    public List<String> getOriginalBeanClassNameList() {
        return originalBeanClassNameList;
    }

    public void setOriginalBeanClassNameList(List<String> originalBeanClassNameList) {
        this.originalBeanClassNameList = originalBeanClassNameList;
    }
}

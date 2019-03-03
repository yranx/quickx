package com.ranx.chowder.study.designPatterns.factory_normal;

/**
 * 抽象产品类
 * @author ranx
 * @create 2019-02-15 15:54
 **/
public abstract class Product {
    //产品类的公共方法
    public void method1() {
        //业务逻辑处理
    }

    //抽象方法
    public abstract void method2();
}

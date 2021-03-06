package com.ranx.chowder.designPatterns.decorator_normal;

/**
 * 具体的装饰类
 * @author ranx
 * @create 2019-04-10 17:36
 **/
public class ConcreteDecorator1 extends Decorator{
    //定义被修饰者
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    //定义自己的修饰方法
    private void method1() {
        System.out.println("method1 修饰");
    }

    @Override
    public void operate() {
        this.method1();
        super.operate();
    }
}

package com.ranx.chowder.study.designPatterns.mediator_normal;

/**
 * 通用中介者
 * @author ranx
 * @create 2019-04-04 14:20
 **/
public class ConcreteMediator extends Mediator {
    @Override
    public void doSomething1() {
        //调用同事类的方法，只要是public方法都可以调用
        super.c1.selfMethod1();
        super.c2.selfMethod2();
    }

    @Override
    public void doSomething2() {
        super.c1.selfMethod1();
        super.c2.selfMethod2();
    }
}

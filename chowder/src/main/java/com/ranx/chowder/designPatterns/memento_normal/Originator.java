package com.ranx.chowder.designPatterns.memento_normal;

/**
 * 发起人角色
 * @author ranx
 * @create 2019-04-28 18:07
 **/
public class Originator {
    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //创建一个备忘录
    public Memento createMemento() {
        return new Memento(this.state);
    }

    //恢复一个备忘录
    public void restoreMemento (Memento memento) {
        this.setState(memento.getState());
    }
}

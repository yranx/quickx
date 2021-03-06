package com.ranx.chowder.designPatterns.memento_nestedclass;

/**
 * @author ranx
 * @create 2019-04-28 18:21
 **/
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker  = new Caretaker();
        //创建备忘录
        caretaker.setMemento(originator.createMemento());
        //恢复一个指定标记的备忘录
        originator.restoreMemento(caretaker.getMemento());
    }
}

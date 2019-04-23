package com.ranx.chowder.study.designPatterns.composite_normal;

import java.util.ArrayList;

/**
 * 树枝构件
 * @author ranx
 * @create 2019-04-22 10:40
 **/
public class Composite extends Component {
    //构件容器
    private ArrayList<Component> componentArrayList = new ArrayList<Component>();

    //增加一个叶子构件或树枝构件
    public void add(Component component) {
        this.componentArrayList.add(component);
    }

    //删除一个叶子构件或树枝构件
    public void remve(Component component) {
        this.componentArrayList.remove(component);
    }

    //获得分支下的所有叶子构件或树枝构件
    public ArrayList<Component> getChildren() {
        return this.componentArrayList;
    }
}

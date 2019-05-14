package com.ranx.chowder.study.designPatterns.state_normal;

/**
 * 具体环境角色
 * @author ranx
 * @create 2019-05-14 19:26
 **/
public class Context {
    //定义状态
    public final static State STATE1 = new ConcreteState1();
    public final static State STATE2 = new ConcreteState2();
    //当前状态
    private State CurrentState;

    public State getCurrentState() {
        return CurrentState;
    }

    public void setCurrentState(State currentState) {
        this.CurrentState = currentState;
        //切换状态
        this.CurrentState.setContext(this);
    }

    //行为委托
    public void handle1() {
        this.CurrentState.handle1();
    }
    public void handle2() {
        this.CurrentState.handle2();
    }
}

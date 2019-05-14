package com.ranx.chowder.study.designPatterns.state_lift_state;

/**
 * 电梯接口
 * @author ranx
 * @create 2019-05-13 10:16
 **/
public interface ILift {
    public final static int OPENING_STATE = 1;
    public final static int CLOSEE_STATE = 2;
    public final static int RUNNING_STATE = 3;
    public final static int STOPPING_STATE = 4;

    public void setState(int state);

    public void open();
    public void close();
    public void run();
    public void stop();
}

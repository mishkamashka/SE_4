package ru.ifmo.se.mbean;

public interface ServerCommandsCounterMBean {
    int totalCommandsCount();
    int loadFromFileCount();
    int loadCurrentCount();
    int saveCount();
    int clearCount();
    int addCount();
    int removeCount();
}

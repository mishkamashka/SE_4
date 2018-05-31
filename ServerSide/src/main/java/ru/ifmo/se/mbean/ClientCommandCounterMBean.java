package ru.ifmo.se.mbean;


public interface ClientCommandCounterMBean {

    int getDataRequestCounter();
    int getClientCounter();
    int getSaveCounter();
}

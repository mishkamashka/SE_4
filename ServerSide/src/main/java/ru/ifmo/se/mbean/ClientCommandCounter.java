package ru.ifmo.se.mbean;


public class ClientCommandCounter implements ClientCommandCounterMBean{
    static int clientCounter=0;
    static int dataRequestCounter=0;
    static int saveCounter=0;

    ClientCommandCounter(){
    }

    public static void addClient(){
        clientCounter++;
    }

    public static void addDataRequest(){
        dataRequestCounter++;
    }

    public static void addSaveRequest(){
        saveCounter++;
    }

    @Override
    public int getDataRequestCounter() {
        return dataRequestCounter;
    }

    @Override
    public int getClientCounter() {
        return clientCounter;
    }

    @Override
    public int getSaveCounter() {
        return saveCounter;
    }

}

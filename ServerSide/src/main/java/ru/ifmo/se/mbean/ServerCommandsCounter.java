package ru.ifmo.se.mbean;

import ru.ifmo.se.enums.CommandTypes;

public class ServerCommandsCounter implements ServerCommandsCounterMBean {
    private static int totalCount = 0;
    private static int loadFromFileCount = 0;
    private static int loadCurrentCount = 0;
    private static int saveCount = 0;
    private static int addCount = 0;
    private static int removeCount = 0;
    private static int clearCount = 0;

    public static void recordCommands(CommandTypes type){
        switch (type) {
            case LOAD_FROM_FILE:
                loadFromFileCount++;
                break;
            case LOAD_CURRENT:
                loadCurrentCount++;
                break;
            case SAVE:
                saveCount++;
                break;
            case ADD:
                addCount++;
                break;
            case CLEAR:
                clearCount++;
                break;
            case REMOVE:
                removeCount++;
            default:
                totalCount++;
        }
    }

    @Override
    public int totalCommandsCount() {
        return totalCount;
    }

    @Override
    public int loadFromFileCount() {
        return loadFromFileCount;
    }

    @Override
    public int loadCurrentCount() {
        return loadCurrentCount;
    }

    @Override
    public int saveCount() {
        return saveCount;
    }

    @Override
    public int clearCount() {
        return clearCount;
    }

    @Override
    public int addCount() {
        return addCount;
    }

    @Override
    public int removeCount() {
        return removeCount;
    }

}

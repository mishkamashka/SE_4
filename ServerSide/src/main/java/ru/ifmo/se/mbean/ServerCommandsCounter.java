package ru.ifmo.se.mbean;

import ru.ifmo.se.MainPanel;
import ru.ifmo.se.enums.CommandTypes;

import java.util.HashMap;
import java.util.Map;

public class ServerCommandsCounter implements ServerCommandsCounterMBean {
    private MainPanel mainPanel;
    private Map<CommandTypes, Integer> commandCount = new HashMap<>();
    int totalCount = 0;

    ServerCommandsCounter(MainPanel panel){
        mainPanel = panel;
    }

    public void recordCommands(CommandTypes type){
        commandCount.put(type, commandCount.get(type) + 1);
        totalCount++;
    }

    @Override
    public int totalCommandsCount() {
        return totalCount;
    }

    @Override
    public int loadFromFileCount() {
        return commandCount.getOrDefault(CommandTypes.LOAD_FROM_FILE, 0);
    }

    @Override
    public int loadCurrentCount() {
        return commandCount.getOrDefault(CommandTypes.LOAD_CURRENT, 0);
    }

    @Override
    public int saveCount() {
        return commandCount.getOrDefault(CommandTypes.SAVE, 0);
    }

    @Override
    public int clearCount() {
        return commandCount.getOrDefault(CommandTypes.CLEAR, 0);
    }

    @Override
    public int addCount() {
        return commandCount.getOrDefault(CommandTypes.ADD, 0);
    }

    @Override
    public int removeCount() {
        return commandCount.getOrDefault(CommandTypes.REMOVE, 0);
    }

}

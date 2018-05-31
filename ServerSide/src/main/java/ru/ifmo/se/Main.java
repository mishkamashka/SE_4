package ru.ifmo.se;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        MBeanServer mBeanServer=ManagementFactory.getPlatformMBeanServer();
        ObjectName name=new ObjectName(ru.ifmo.se.mbean.)
        Server a = new Server();
        new Thread(a).start();
        MainPanel mainPanel = new MainPanel();
    }
}

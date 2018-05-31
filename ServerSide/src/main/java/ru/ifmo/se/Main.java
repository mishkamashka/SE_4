package ru.ifmo.se;

import ru.ifmo.se.mbean.ClientCommandCounter;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        MBeanServer mBeanServer=ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name=new ObjectName("ru.ifmo.se.mbean:type=ClientCommandCounter");
            ClientCommandCounter mbean = new ClientCommandCounter();
            mBeanServer.registerMBean(mbean,name);
        }
        catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e){e.printStackTrace();}
        Server a = new Server();
        new Thread(a).start();
        MainPanel mainPanel = new MainPanel();
    }
}

package ru.ifmo.se;

import ru.ifmo.se.mbean.ClientCommandCounter;
import ru.ifmo.se.mbean.ServerCommandsCounter;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        Server a = new Server();
        new Thread(a).start();
        MainPanel mainPanel = new MainPanel();
        MBeanServer mBeanServer=ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName clientCounter = new ObjectName("ru.ifmo.se.mbean:type=ClientCommandCounter");
            ObjectName serverCounter = new ObjectName("ru.ifmo.se.mbean:type=ServerCommandCounter");
            ClientCommandCounter clientMbean = new ClientCommandCounter();
            ServerCommandsCounter serverMbean = new ServerCommandsCounter();
            mBeanServer.registerMBean(clientMbean, clientCounter);
            mBeanServer.registerMBean(serverMbean, serverCounter);
        }
        catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e){e.printStackTrace();}
    }
}

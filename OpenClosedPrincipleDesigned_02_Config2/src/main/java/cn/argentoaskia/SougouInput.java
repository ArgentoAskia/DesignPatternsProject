package cn.argentoaskia;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.Properties;

public class SougouInput implements AbstractSkin{

    @Override
    public void display() {
        try {
            watchService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void watchService() throws Exception {
        String filePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        // System.out.println(filePath);
        String filePath2 = new File(filePath).getPath();
        // 获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get(filePath2).register(watchService
                , StandardWatchEventKinds.ENTRY_CREATE
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_DELETE);

        File file = new File(filePath);
        LinkedList<File> fList = new LinkedList<File>();
        fList.addLast(file);
        while (fList.size() > 0) {
            File f = fList.removeFirst();
            if (f.listFiles() == null)
                continue;
            for (File file2 : f.listFiles()) {
                if (file2.isDirectory()) {//下一级目录
                    fList.addLast(file2);
                    //依次注册子目录
                    Paths.get(file2.getAbsolutePath()).register(watchService
                            , StandardWatchEventKinds.ENTRY_CREATE
                            , StandardWatchEventKinds.ENTRY_MODIFY
                            , StandardWatchEventKinds.ENTRY_DELETE);
                }
            }
        }
        while (true) {
            // 获取下一个文件改动事件
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                // System.out.println(event.context() + " --> " + event.kind());
                if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)){
                    doDisplay(filePath2);
                }
            }
            // 重设WatchKey
            boolean valid = key.reset();
            // 如果重设失败，退出监听
            if (!valid) {
                break;
            }
        }
    }

    private void doDisplay(String filePath2) throws Exception{
        Properties properties = new Properties();
        String implProperties = filePath2 + "\\impl.properties";
        // System.out.println(implProperties);
        properties.load(new FileInputStream(implProperties));
        String impl = properties.getProperty("impl");
        System.out.println("当前实现：" + impl);
        // 反射加载类
        Class<AbstractSkin> aClass = (Class<AbstractSkin>) getClass().getClassLoader().loadClass(impl);
        // 创建对象
        AbstractSkin abstractSkin = aClass.newInstance();
        // 显示对象方法
        abstractSkin.display();
    }
}

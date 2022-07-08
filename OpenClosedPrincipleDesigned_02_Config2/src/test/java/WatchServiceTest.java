import cn.argentoaskia.AbstractSkin;
import cn.argentoaskia.SougouInput;

public class WatchServiceTest {
    // 模拟在皮肤更换窗口中点击按钮更换皮肤的过程
    public static void main(String[] args)
            throws Exception {

//        String filePath = WatchServiceTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "..\\classes\\impl.properties";
//        File file = new File(filePath);
//        System.out.println(file.getPath());
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(filePath));
//        String impl = properties.getProperty("impl");
//        System.out.println(impl);
        AbstractSkin sougouInput = new SougouInput();
        sougouInput.display();
    }
}
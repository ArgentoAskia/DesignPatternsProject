import java.util.Arrays;

public class SkinTestClient {

//    public static void main(String[] args) {
//        System.out.println("=================================================");
//        System.out.println("--> 采用接口形式设计代码，符合开闭原则 <--");
//        System.out.println("当我们需要扩展新实现的时候，只需要实现接口，然后重写方法即可，如我现在显示Windows经典皮肤（对应类WindowsSkinRe）");
//        Skin windowsSkin2 = new WindowsSkinRe();
//        windowsSkin2.display();
//        System.out.println("后期Windows皮肤系统升级了，我只要通过实现WindowsSkin2接口，然后在客户端代码进行替换就行，如我现在替换皮肤（对应新类WindowsSkinNew）：");
//        windowsSkin2 = new WindowsSkinNew();
//        windowsSkin2.display();
//        System.out.println("看！是不是替换成功了，( •̀ ω •́ )y ━((*′д｀)爻(′д｀*))━!!!!");
//        System.out.println("=================================================");
//
//    }

    public void skinDisplay(Skin skin){
        skin.display();
    }

    /**
     * 基础版本changeSkin
     * @param skinName
     */
    public void changeSkin(String skinName){
        Skin skin = new DefaultSkin();
        if ("windowsSkin".equals(skinName)){
            skin = new WindowsSkin();
        }else if ("askiaSkin".equals(skinName)){
            skin = new AskiaSkin();
        }else if("windowsReSkin".equals(skinName)){
           skin = new WindowsReSkin();
        }
        skinDisplay(skin);
    }

    /**
     * 代码统一的changeSkin2
     * @param skinClazz
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void changeSkin2(Class<?> skinClazz) throws IllegalAccessException, InstantiationException {
        // 获取skinClazz所有继承的接口
        Class<?>[] interfaces = skinClazz.getInterfaces();
        // 防止传入Skin接口的class对象和其他对象
        if (skinClazz.getSuperclass() == null ||
                !interfaces[0].equals(Skin.class)){
            skinDisplay(new DefaultSkin());
            return;
        }
        // 反射创建皮肤对象
        Skin skin = (Skin)skinClazz.newInstance();
        // 显示皮肤
        skinDisplay(skin);
    }

    // 模拟在皮肤更换窗口中点击按钮更换皮肤的过程
    public static void main(String[] args) throws Exception{
        SkinTestClient skinTestClient = new SkinTestClient();
        // 优化1
        skinTestClient.changeSkin("windowsSkin");
        skinTestClient.changeSkin("askiaSkin");
        skinTestClient.changeSkin("windowsReSkin");


        // 在优化1的基础上优化changeSkin方法
        skinTestClient.changeSkin2(AskiaSkin.class);
    }
}

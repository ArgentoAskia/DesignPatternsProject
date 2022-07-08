public class SkinTestClient {


//    public static void main(String[] args) {
//        System.out.println("=================================================");
//        System.out.println("--> 不符合开闭原则设计思想的代码 <--");
//        System.out.println("任何东西只能直接使用，当需要修改某个类的时候不能通过重写，只能直接改变这个类的代码");
//        System.out.println("如下面的WindowsSkin类，他能够显示：WindowsSkin皮肤");
//        SkinTestClient skinTestClient = new SkinTestClient();
//        WindowsSkin windowsSkin = new WindowsSkin();
//        skinTestClient.skinDisplay(windowsSkin);
//        System.out.println("如果我现在Windows皮肤系统更新了，我想让其显示新版WindowsSkin皮肤，" +
//                "只能修改WindowsSkin类的源代码！！");
//        System.out.println("这就是为什么说这种设计不符合开闭原则，因为开闭原则需要我们对修改关闭");
//        System.out.println("=================================================");
//
//    }
    public void windowsSkinDisplay(WindowsSkin windowsSkin){
        windowsSkin.display();
    }
    public void askiaSkinDisplay(AskiaSkin askiaSkin){
        askiaSkin.display();
    }
    public void windowsReSkinDisplay(WindowsReSkin windowsReSkin){
        windowsReSkin.display();
    }
    public void changeSkin(String skinName){
        if ("windowsSkin".equals(skinName)){
            WindowsSkin windowsSkin = new WindowsSkin();
            windowsSkinDisplay(windowsSkin);
        }else if ("askiaSkin".equals(skinName)){
            AskiaSkin askiaSkin = new AskiaSkin();
            askiaSkinDisplay(askiaSkin);
        }else if("windowsReSkin".equals(skinName)){
            WindowsReSkin windowsReSkin = new WindowsReSkin();
            windowsReSkinDisplay(windowsReSkin);
        }
    }
    // 模拟在皮肤更换窗口中点击按钮更换皮肤的过程
    public static void main(String[] args) {
        SkinTestClient skinTestClient = new SkinTestClient();
        skinTestClient.changeSkin("askiaSkin");
        skinTestClient.changeSkin("windowsSkin");
    }
}

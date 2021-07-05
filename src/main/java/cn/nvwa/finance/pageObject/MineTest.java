package cn.nvwa.finance.pageObject;

import cn.nvwa.finance.base.BaseTest;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class MineTest {

    /**
     * 积目-点击"我的"按钮
     * @param
     * @return
     */
    public static void clickMyButton(){

        try{
            //判断如果没有找到"我的"按钮，则截图、报错
            if(BaseTest.checkViewVisibilty(By.id("com.fenzotech.jimu:id/anw"))){
                //点击"我的"按钮,进入"我的"页面（积目）
                BaseTest.webDriverWaitById("com.fenzotech.jimu:id/anw").click();
                System.out.println("开始进入'我的'页面");
            }else {
                BaseTest.getScreenshot("mineButton");
                BaseTest.failAndMessage("无法进入'我的'页面，请检查");
            }
        }catch (Exception e){
            BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }

    }

    /**
     * 积目-点击"我的特权"按钮
     * @param
     * @return
     */
    public static void myPrerogative(){
        //校验如果找到"我的特权"按钮，则点击"我的特权"
        for(int attempt=0;attempt <3;attempt++){
            try{
                if(BaseTest.checkViewVisibilty(By.id("com.fenzotech.jimu:id/bep"))){
                    //点击"我的特权"，默认开通的是SVIP
                    BaseTest.webDriverWaitById("com.fenzotech.jimu:id/bep").click();
                    System.out.println("进入'我的特权'页面");
                    BaseTest.wait(5);
                    break;
                }else {
                    clickMyButton();
                }
            }catch (Exception e){
                BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }

        }

    }
}

package cn.nvwa.finance.pageObject;

import cn.nvwa.finance.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class ChatRightPreOrder {

    /**
     * 对聊【会员中心】支付宝购买30天的特权套餐--支付宝普通购买和支付流程
     * @param
     * @return
     */
    public void memberShipCenter(){

        for(int attempt=0;attempt<3;attempt++){
            try{
                //判断如果找的"我"tab，则点击该tab，进入"我"的teb
                if(BaseTest.checkViewVisibilty(By.id("com.hnhuyu.chatlive:id/main_tab_btn_main_me"))){
                    //进入"我"的tab
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/main_tab_btn_main_me").click();
                    System.out.println("【支付宝】普通购买和支付-进入'我的'");
                    BaseTest.wait(3);
                    break;
                }

            }  catch (Exception e){
                BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }

        for(int attempt=0;attempt<3;attempt++){
            try{
                //判断如果找到"会员中心"字样，则表示进入了会员中心
                if(BaseTest.checkViewVisibilty(By.id("com.hnhuyu.chatlive:id/m_home_vip_rlyt"))){
                    //进入"会员中心"
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/m_home_vip_rlyt").click();
                    System.out.println("【支付宝】普通购买和支付-进入'会员中心'");
                    BaseTest.wait(5);
                    break;
                }else {
                    //如果没进入会员中心，则继续点击"我"的tab
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/main_tab_btn_main_me").click();
                    BaseTest.wait(3);
                }
            }catch (Exception e){
                BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }

        for(int attempt=0;attempt<3;attempt++){
            try{
                //判断如果有"获取会员"按钮，则表示进入了"会员中心"页面
                if(BaseTest.checkViewVisibilty(By.id("com.hnhuyu.chatlive:id/play_button"))){
                    //点击"特权套餐-30天"
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/rly_charge_view_cell_bg").click();
                    // 此处上划
                    WebElement element = BaseTest.findElementById("com.hnhuyu.chatlive:id/bottom_view");
                    BaseTest.slidingInElement(element,"up");

                    //点击"支付宝"（默认微信支付）
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/rl_ali_pay").click();
                    //点击"获取会员"
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/play_button").click();
                    System.out.println("【支付宝】普通购买和支付-开始获取会员");
                    BaseTest.wait(5);
                    //点击"立即付款"
                    BaseTest.findElementByXpath("//android.widget.TextView[@text=\"立即付款\"]").click();
                    BaseTest.wait(3);
                    SendPayPassWord sendPayPassWord = new SendPayPassWord();
                    sendPayPassWord.inputPassWordByKeyBoard();
                    System.out.println("【支付宝】普通购买和支付-输入密码成功");
                    BaseTest.wait(5);
                    break;
                }else {
                    //进入"会员中心"
                    BaseTest.webDriverWaitById("com.hnhuyu.chatlive:id/m_home_vip_rlyt").click();
                    BaseTest.wait(5);
                }
            }catch (Exception e){
                BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }


    }

    /**
     * 支付宝支付成功后，点击"完成"按钮
     * @param
     * @return
     */
    public void clickDoneButton(){
        BaseTest.findElementById("com.alipay.android.app:id/nav_right_textview").click();
    }
}

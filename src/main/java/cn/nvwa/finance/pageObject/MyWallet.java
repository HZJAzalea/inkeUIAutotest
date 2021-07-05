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
public class MyWallet {

    SendPayPassWord sendPayPassWord = new SendPayPassWord();

    /**
     * 积目-支付宝购买虚拟币
     * @param
     * @return
     */
    public void virtualCurrencyAlipay(){

            //再次点击"我的"tab
            MineTest.clickMyButton();

            //点击"我的钱包"，默认进入"充值"页面
            BaseTest.webDriverWaitById("com.fenzotech.jimu:id/bev").click();
            BaseTest.wait(3);
            for(int attempt=0;attempt<3;attempt++){
                try{
                    //判断如果页面有"充值金额"字样，则表示进入充值页面
                    if(BaseTest.checkViewVisibilty(By.xpath("//android.widget.TextView[@text=\"充值金额\"]"))){
                        System.out.println("【支付宝】购买虚拟币-进入充值页面");
                        //点击"支付宝支付"
                        BaseTest.webDriverWaitById("com.fenzotech.jimu:id/is").click();
                        BaseTest.wait(5);
                        //点击"立即付款"
                        BaseTest.findElementByXpath("//android.widget.TextView[@text=\"立即付款\"]").click();
                        //等待3s至输入密码弹窗展示
                        BaseTest.wait(5);
                        //输入密码
                        sendPayPassWord.inputPassWordByKeyBoard();
                        System.out.println("【支付宝】购买虚拟币-已输入支付密码");
                        break;
                    }else {
                        //点击"我的钱包"，默认进入"充值"页面
                        BaseTest.webDriverWaitById("com.fenzotech.jimu:id/bev").click();
                        BaseTest.wait(3);
                    }
                }catch (Exception e){
                    BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                }
            }

    }


    /**
     * 积目-支付宝购买虚拟币-点击"完成"，返回"充值"页面
     * @param
     * @return
     */
    public void  clickDoneButton(){
        //点击"完成"，返回"充值"页面
        BaseTest.webDriverWaitById("com.alipay.android.app:id/nav_right_textview").click();
    }


}

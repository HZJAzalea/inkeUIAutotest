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
public class MyPrerogative {

    SendPayPassWord sendPayPassWord = new SendPayPassWord();


    /**
     * 积目-微信购买vip会员--微信周期扣款
     * @param ziDongXuFeiPoint "开通自动续费"按钮坐标
     * @param weixindonePoint 支付完成后，页面中"完成"按钮坐标
     * @return
     */
    public void vipWeixinPay(int[][] ziDongXuFeiPoint,int[][] weixindonePoint){

            //先进入"我的"tab页
            MineTest.clickMyButton();
            //点击"我的特权"，默认开通的是SVIP
            MineTest.myPrerogative();
            //进入开通VIP会员页
            BaseTest.findElementByXpath("//android.widget.TextView[@text=\"尊享7项特权\"]").click();
            BaseTest.wait(3);

            //判断如果页面包含"尊享7项特权"文案，则进入了vip购买页面
            for(int attempt=0;attempt<3;attempt++){
                try{
                    if(BaseTest.checkViewVisibilty(By.xpath("//android.widget.TextView[@text=\"尊享7项特权\"]"))){
                        System.out.println("开始【微信】购买vip会员");
                        //点击"开通VIP会员"或"续费VIP会员按钮"
                        BaseTest.findElementById("com.fenzotech.jimu:id/yw").click();
                        BaseTest.wait(3);
                        //点击"连续包月
                        BaseTest.findElementByXpathWithIndex("//android.widget.TextView[@text=\"连续包月\"]",1).click();
                        //点击"其他支付方式"
                        BaseTest.findElementsById("com.fenzotech.jimu:id/bc2").get(1).click();
                        BaseTest.wait(3);
                        //点击"微信支付"
                        BaseTest.findElementById("com.fenzotech.jimu:id/bew").click();
                        BaseTest.wait(6);
                        //跳转到支付页面，点击"开通自动续费"按钮
                        BaseTest.clickElement_OverScreen(ziDongXuFeiPoint);
                        BaseTest.wait(5);
                        break;
                    }else {
                        MineTest.myPrerogative();
                    }
                }catch (Exception e){
                    BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                }
            }

            for(int attempt=0;attempt<3;attempt++){
                try{
                    //判断如果进入输入密码页，则输入密码
                    if(BaseTest.checkViewVisibilty(By.id("com.tencent.mm:id/jhz"))){
                        //输入支付密码
                        sendPayPassWord.inputPassWordByKeyBoard();
                        System.out.println("【微信】购买vip-已输入支付密码");
                        BaseTest.wait(3);
                        //点击"完成"，弹出是否跳转的弹窗
                        BaseTest.clickElement_OverScreen(weixindonePoint);
                        BaseTest.wait(2);
                        break;
                    }else {
                        //跳转到支付页面，点击"开通自动续费"按钮
                        BaseTest.clickElement_OverScreen(ziDongXuFeiPoint);
                        BaseTest.wait(5);
                    }
                }catch (Exception e){
                    BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                }
            }

    }

    /**
     * 积目-微信购买vip会员-点击"完成后"，出现的是否跳转弹窗，该弹窗选择允许
     * @param
     * @return
     */
    public void clickAllowButton(){
        //弹窗选择"允许"之后，进入微信主页面
        BaseTest.findElementById("com.tencent.mm:id/ffp").click();
        System.out.println("点击'允许'按钮");
    }



    /**
     * 积目-支付宝购买svip会员 --支付宝周期扣款
     * @param payPoint "同意协议并开通自动续费"按钮坐标
     * @return
     */
    public void svipAlipay(int[][] payPoint){
        //以下为支付宝支付SVIP会员

            //先进入"我的"tab页
            MineTest.clickMyButton();
            //点击"我的特权"，默认开通的是SVIP
            MineTest.myPrerogative();
            for(int attempt=0;attempt<3;attempt++){
                try{
                    //校验如果没有找到"特权中心"，则截图、报错
                    if(BaseTest.checkViewVisibilty(By.id("com.fenzotech.jimu:id/be0"))){
                        System.out.println("开始【支付宝】购买svip会员");
                        //点击"开通SVIP会员"
                        BaseTest.findElementById("com.fenzotech.jimu:id/yw").click();
                        //选择连续包月
                        BaseTest.findElementByXpath("//android.widget.TextView[@text=\"连续包月\"]").click();
                        //点击"支付宝支付"
                        BaseTest.findElementById("com.fenzotech.jimu:id/wh").click();
                        System.out.println("【支付宝】购买svip会员-点击'支付宝支付'");
                        BaseTest.wait(8);
                        break;
                    }else {
                        MineTest.myPrerogative();
                    }
                }catch (Exception e){
                    BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                }

            }

            for(int attempt=0;attempt<3;attempt++){
                try{
                    if(BaseTest.checkViewVisibilty(By.xpath("//android.view.View[@text=\"湖南高佳网络信息有限公司自动续费\"]"))){
                        //跳转到支付宝后，点击"同意协议并开通自动续费"
                        BaseTest.clickElement_OverScreen(payPoint);
                        BaseTest.wait(3);

                        //输入支付密码
                        sendPayPassWord.inputPassWordByKeyBoard();
                        System.out.println("【支付宝】购买svip-已输入支付密码");
                        break;
                    }else {
                        //点击"支付宝支付"
                        BaseTest.findElementById("com.fenzotech.jimu:id/wh").click();
                        System.out.println("【支付宝】购买svip会员-再次点击'支付宝支付'");
                        BaseTest.wait(8);
                    }
                }catch (Exception e){
                    BaseTest.androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                }

            }



    }

}

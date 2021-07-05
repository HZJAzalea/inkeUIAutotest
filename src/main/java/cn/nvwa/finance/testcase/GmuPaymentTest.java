package cn.nvwa.finance.testcase;

import cn.nvwa.finance.base.BaseTest;
import cn.nvwa.finance.base.CaseConfig;
import cn.nvwa.finance.pageObject.MyPrerogative;
import cn.nvwa.finance.pageObject.MyWallet;
import cn.nvwa.finance.pageObject.SetUpTestApp;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class GmuPaymentTest {


    //手机屏幕大小
    int[] screenArr = {1080,2044};
    //微信"开通自动续费"坐标
    int[][] weixinPayPoint = {{521,1718},screenArr};
    //"完成"坐标
    int[][] weixindonePoint = {{539,1020},screenArr};
    //"开通VIP会员"坐标
    int[][] vipPoint = {{1033,510},screenArr};
    //支付宝"同意协议并开通自动续费"坐标
    int[][] aliPayPoint = {{540,1805},screenArr};
    //appPackage和appActivity
    String appPackageGmu = "com.fenzotech.jimu";
    String appActivityGmu = "com.eomchat.module.splash.SplashActivity";

    //header
    String headName = "uberctx-_namespace_appkey_";
    String headValue = "gmu";
    //uid
    int uid = 5673188;
    //支付方式
    String mannerAlipay = "alipay";
    String mannerWeixin = "weixin";


    MyPrerogative myPrerogative = new MyPrerogative();
    MyWallet myWallet = new MyWallet();


    @BeforeMethod
    public void beferTest(){
        BaseTest.setUp(appPackageGmu,appActivityGmu);
    }


    /**
     * 微信-购买vip --微信周期扣款
     * @param
     * @return
     */
    @Test
    public void testcase_01_weixinVip(){
        try{
            myPrerogative.vipWeixinPay(weixinPayPoint,weixindonePoint);

            //判断如果页面含有"允许"字样，则支付成功
            if(BaseTest.checkViewVisibilty(By.id("com.tencent.mm:id/ffp"))){
                CaseConfig.TestCasePass++;
                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);

                Reporter.log("微信周期扣款成功！");

               //弹窗选择"允许"之后，进入微信主页面
                myPrerogative.clickAllowButton();
                System.out.println("微信周期扣款成功-----------------");
                //跳转回积目
                SetUpTestApp.setUpTestApp(appPackageGmu,appActivityGmu);
            }else {
                CaseConfig.TestCaseFail++;
                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
                BaseTest.getScreenshot();
                BaseTest.failAndMessage("微信周期扣款未进入指定页面，请检查");
                System.out.println("微信周期扣款失败-----------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 支付宝购买svip --支付宝周期扣款
     * @param
     * @return
     */
    @Test
    public void testcase_02_alipaySvip(){
        try{
            myPrerogative.svipAlipay(aliPayPoint);
            //判断如果页面含有"开通自动续费"，则付款成功
            if(BaseTest.checkViewVisibilty(By.id("com.alipay.mobile.nebula:id/h5_tv_title"))){
                CaseConfig.TestCasePass++;
                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
                Reporter.log("支付宝周期扣款成功！");
                System.out.println("支付宝周期扣款成功-----------------");
                //跳转回积目
                SetUpTestApp.setUpTestApp(appPackageGmu,appActivityGmu);
            }else {
                CaseConfig.TestCaseFail++;
                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
                BaseTest.getScreenshot();
                BaseTest.failAndMessage("支付宝周期扣款未进入指定页面，请检查");
                System.out.println("支付宝周期扣款失败-----------------");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 支付宝购买虚拟币
     * @param
     * @return
     */
    @Test
    public void testcase_03_virtualCurrencyAlipay(){
        try{
            myWallet.virtualCurrencyAlipay();
            if(BaseTest.checkViewVisibilty(By.id("com.alipay.android.app:id/nav_right_textview"))){
                CaseConfig.TestCasePass++;
                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
                Reporter.log("支付宝购买虚拟币成功！");
                System.out.println("支付宝购买虚拟币成功-----------------");
                //点击"完成"按钮，返回"充值"页面
                myWallet.clickDoneButton();
            }else {
                CaseConfig.TestCaseFail++;
                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
                BaseTest.getScreenshot();
                BaseTest.failAndMessage("支付宝购买虚拟币未进入指定页面，请检查");
                System.out.println("支付宝购买虚拟币失败-----------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 支付宝-扣款日期变更
     * @param
     * @return
     */
//    @Test
//    public void testcase_04_updateNextPayDateAlipay(){
//        try{
//            System.out.println("开始【支付宝扣款日期变更】");
//            String result = OtherFinanceInterface.getUpdateNextPayDateResult(uid, mannerAlipay, headName, headValue);
//            //将接口返回值转换成json对象
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            //获取dm_error
//            int dm_error = jsonObject.getInteger("dm_error");
//            if(dm_error == 0){
//                Reporter.log("支付宝扣款日期变更成功! 扣款日期变更返回值：" + result);
//                CaseConfig.TestCasePass++;
//                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
//                System.out.println("支付宝扣款日期变更成功-----------------");
//            }else {
//                Reporter.log("支付宝扣款日期变更成功失败! 扣款日期变更返回值：" + result);
//                CaseConfig.TestCaseFail++;
//                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
//                System.out.println("支付宝扣款日期变更成功失败-----------------");
//            }
//            //断言dm_error
//            Assert.assertEquals(dm_error,0);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 微信-扣款日期变更
     * @param
     * @return
     */
//    @Test
//    public void testcase_05_updateNextPayDateWeixin(){
//        try{
//            System.out.println("开始【微信扣款日期变更】");
//            String result = OtherFinanceInterface.getUpdateNextPayDateResult(uid, mannerWeixin, headName, headValue);
//            //将接口返回值转换成json对象
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            //获取dm_error
//            int dm_error = jsonObject.getInteger("dm_error");
//            if(dm_error == 0){
//                Reporter.log("微信扣款日期变更成功! 扣款日期变更返回值：" + result);
//                CaseConfig.TestCasePass++;
//                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
//                System.out.println("微信扣款日期变更成功-----------------");
//            }else {
//                Reporter.log("微信扣款日期变更成功失败! 扣款日期变更返回值：" + result);
//                CaseConfig.TestCaseFail++;
//                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
//                System.out.println("微信扣款日期变更成功失败-----------------");
//            }
//            //断言dm_error
//            Assert.assertEquals(dm_error,0);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 支付宝-周期扣款解约
     * @param
     * @return
     */
//    @Test
//    public void testcase_06_deleteContractAlipay(){
//        try{
//            System.out.println("开始【支付宝周期扣款解约】");
//            String result = OtherFinanceInterface.getDeleteContractResult(uid,mannerAlipay, headName, headValue);
//            //将接口返回值转换成json对象
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            //获取dm_error
//            int dm_error = jsonObject.getInteger("dm_error");
//            if(dm_error == 0){
//                Reporter.log("支付宝周期扣款解约成功! 周期扣款解约返回值：" + result);
//                CaseConfig.TestCasePass++;
//                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
//                System.out.println("支付宝周期扣款解约成功-----------------");
//            }else {
//                Reporter.log("支付宝周期扣款解约失败! 周期扣款解约返回值：" + result);
//                CaseConfig.TestCaseFail++;
//                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
//                System.out.println("支付宝周期扣款解约失败-----------------");
//            }
//            //断言dm_error
//            Assert.assertEquals(dm_error,0);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     * 微信-周期扣款解约
     * @param
     * @return
     */
//    @Test
//    public void testcase_07_deleteContractWeixin(){
//        try{
//            System.out.println("开始【微信周期扣款解约】");
//            String result = OtherFinanceInterface.getDeleteContractResult(uid,mannerWeixin, headName, headValue);
//            //将接口返回值转换成json对象
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            //获取dm_error
//            int dm_error = jsonObject.getInteger("dm_error");
//            if(dm_error == 0){
//                Reporter.log("微信周期扣款解约成功! 周期扣款解约返回值：" + result);
//                CaseConfig.TestCasePass++;
//                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
//                System.out.println("微信周期扣款解约成功-----------------");
//            }else {
//                Reporter.log("微信周期扣款解约失败! 周期扣款解约返回值：" + result);
//                CaseConfig.TestCaseFail++;
//                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
//                System.out.println("微信周期扣款解约失败-----------------");
//            }
//            //断言dm_error
//            Assert.assertEquals(dm_error,0);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @AfterMethod
    public void afterTest(){
        BaseTest.tearDwon();
    }


}

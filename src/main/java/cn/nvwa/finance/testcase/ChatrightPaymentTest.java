package cn.nvwa.finance.testcase;

import cn.nvwa.finance.base.BaseTest;
import cn.nvwa.finance.base.CaseConfig;
import cn.nvwa.finance.pageObject.ChatRightPreOrder;
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
public class ChatrightPaymentTest {
    //手机屏幕大小
    int[] screenArr = {1080,2044};
    //对聊"立即付款"坐标
    int[][] immediatePaymentPoint={{532,1981},screenArr};
    //对聊的appPackage和appActivity
    String appPackageChatRight = "com.hnhuyu.chatlive";
    String appActivityChatRight = "com.hnhuyu.chatlive.launcher.ChatLiveSplash";
    //退款uid
    int uid= 1310809;
    //退款url
    String url = "http://10.128.0.44:10001/api_server/v1/finance/refund/refund";
    //退款接口header
    String headName = "uberctx-_namespace_appkey_";
    String headValue = "chatright";


    @BeforeMethod
    public void beferTest(){
        BaseTest.setUp(appPackageChatRight,appActivityChatRight);
    }


    /**
     * 支付宝-普通购买和支付
     * @param
     * @return
     */
    @Test
    public void testcase_01_chatrightPreOrderAlipay(){
        ChatRightPreOrder chatRightPreOrder = new ChatRightPreOrder();
        chatRightPreOrder.memberShipCenter();
        try{
            if(BaseTest.checkViewVisibilty(By.xpath("//android.widget.TextView[@text=\"支付成功\"]"))){
                CaseConfig.TestCasePass++;
                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
                Reporter.log("支付宝普通购买和支付接口成功！");
                System.out.println("支付宝普通购买和支付成功-----------------");
                //点击"完成"
                chatRightPreOrder.clickDoneButton();
            }else {
                CaseConfig.TestCaseFail++;
                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
                BaseTest.getScreenshot();
                BaseTest.failAndMessage("支付宝普通购买和支付未进入指定页面，请检查");
                System.out.println("支付宝普通购买和支付失败-----------------");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 支付宝-退款
     * @param
     * @return
     */
//    @Test
//    public void testcase_02_chatrightRefundAlipay(){
//        String manner = "alipay";
//        System.out.println("开始进行支付宝退款 -支付宝退款");
//        try{
//            String result = OtherFinanceInterface.getRefundResult(uid, manner, headName, headValue);
//            //将接口返回值转换成json对象
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            //获取dm_error
//            int dm_error = jsonObject.getInteger("dm_error");
//            if(dm_error == 0){
//                Reporter.log("支付宝退款成功! 退款返回值：" + result);
//                CaseConfig.TestCasePass++;
//                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
//                System.out.println("支付宝退款成功-----------------");
//            }else {
//                Reporter.log("支付宝退款失败! 退款返回值：" + result);
//                CaseConfig.TestCaseFail++;
//                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
//                System.out.println("支付宝退款失败-----------------");
//            }
//            //断言dm_error
//            Assert.assertEquals(dm_error,0);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     * 微信-退款
     * @param
     * @return
     */
//    @Test
//    public void testcase_03_chatrightRefundWeixin(){
////        int uid= 1310809;
//        String manner = "weixin";
//
//        System.out.println("开始进行微信退款 -微信退款");
//        try{
//            String result = OtherFinanceInterface.getRefundResult(uid, manner, headName, headValue);
//            //将接口返回值转换成json对象
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            //获取dm_error
//            int dm_error = jsonObject.getInteger("dm_error");
//            if(dm_error == 0){
//                Reporter.log("微信退款成功! 退款返回值：" + result);
//                CaseConfig.TestCasePass++;
//                System.out.println("TestCasePass=" + CaseConfig.TestCasePass);
//                System.out.println("支付宝退款成功-----------------");
//            }else {
//                Reporter.log("微信退款失败! 退款返回值：" + result);
//                CaseConfig.TestCaseFail++;
//                System.out.println("TestCaseFail=" + CaseConfig.TestCaseFail);
//                System.out.println("微信退款失败-----------------");
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

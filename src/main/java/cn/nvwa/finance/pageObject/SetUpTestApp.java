package cn.nvwa.finance.pageObject;

import cn.nvwa.finance.base.BaseTest;
import io.appium.java_client.android.Activity;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class SetUpTestApp {

    /**
     * 设置appPackage和appActivity
     * @param appPackage,appActivity
     * @return
     */
    public static void setUpTestApp(String appPackage,String appActivity){
        //设置activity
        Activity activity = new Activity(appPackage,appActivity);
        BaseTest.androidDriver.startActivity(activity);
    }
}

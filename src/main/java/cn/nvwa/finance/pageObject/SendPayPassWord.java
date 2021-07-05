package cn.nvwa.finance.pageObject;

import cn.nvwa.finance.base.BaseTest;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class SendPayPassWord {

    /**
     * 支付密码
     * @param
     * @return
     */
    public void inputPassWordByKeyBoard(){
        BaseTest.androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
        BaseTest.androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
        BaseTest.androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
        BaseTest.androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
        BaseTest.androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
        BaseTest.androidDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
        //等待3s
        BaseTest.wait(3);
    }
}

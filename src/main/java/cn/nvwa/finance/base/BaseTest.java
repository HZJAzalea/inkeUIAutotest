package cn.nvwa.finance.base;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class BaseTest {
    public static AndroidDriver androidDriver;

    /**
     * 启动app设置
     * @param appPackage,appActivity
     * @return
     */
    public static void setUp(String appPackage,String appActivity){
        //负责启动服务端时的参数设置，启动session的时候是必须提供的
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置被测试手机的浏览器
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"AndroidBrowser");
        //设置操作平台（android/ios）
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","9");
        //设置操作的手机名称
        capabilities.setCapability("deviceName","RELBB18A12502262");
        //设置app的主包名和主类名
        capabilities.setCapability("appPackage",appPackage);
        capabilities.setCapability("appActivity",appActivity);

        //防止重装app
        capabilities.setCapability("noReset",true);
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        capabilities.setCapability("sessionOverride",true);
        //使用unicode编码方式发送字符串
        capabilities.setCapability("unicodeKeyboard",true);
        //false设置默认键盘为appium的键盘，true为系统键盘
        capabilities.setCapability("resetKeyboard",true);
        //使用uiautomator2自动化引擎
//        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("automationName","appium");
        //使用adb方法进行截图
        capabilities.setCapability("nativeWebScreenshot",true);
        //显示webview日志
        capabilities.setCapability("showChromedriverLog",true);
        //设置命令超时
        capabilities.setCapability("newCommandTimeout",360000);
        //解决 打开第二个webview识别第一个webview元素
        capabilities.setCapability("recreateChromeDriverSessions",true);


        try {
            //初始化
            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试完毕，关闭driver
     * @param
     * @return
     */
    public static void tearDwon(){
        //测试完毕，关闭driver，不关闭将会导致会话还存在，下次启动就会报错
        androidDriver.quit();
    }


     /**
     * 判断控件是否存在，并返回boolean值  (注意：appium 1.5版本之后不再支持by.name ，可以参考使用CheckViewVisibiltyByName()方法  )
     * @param by
     * @return boolean返回按钮存在与否的状态，存在时返回ture，不存在是返回false
     */
     public static boolean checkViewVisibilty(final By by){
         Date start = new Date();
         WebElement wb = null;
         WebDriverWait wait = new WebDriverWait(androidDriver,3);
         for(int attempt = 0;attempt < 3; attempt++){
             try{
                 wb = androidDriver.findElement(by);
                 if(wb.isDisplayed() && wb.isEnabled()){
                     break;
                 }
             }catch (Exception e){
                 androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                 return false;
             }

         }

         if(wb != null){
             Date end = new Date();
             System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
             return true;
         }else {
             Date end = new Date();
             System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
             return false;
         }
     }

    /**
     *通过id查找元素
     * @param id
     * @return
     */
    public static WebElement findElementById(String id){
        WebElement element = androidDriver.findElement(By.id(id));

        return element;
    }

    /**
     *通过id查找元素，返回元素的list集合
     * @param id
     * @return
     */
    public static List<WebElement> findElementsById(String id){
        List<WebElement> elements = androidDriver.findElements(By.id(id));
        return elements;
    }

    /**
     *按照id查找元素(显示等待，并返回元素)
     * @param id
     * @return
     */
    public static WebElement webDriverWaitById(final String id){
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(androidDriver,60);
        element = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.id(id));
            }
        });
        return element;
    }


    /**
     * 通过xpath查找元素
     * @param xpath
     * @return
     * @throws NoSuchElementException
     */
    public static WebElement findElementByXpath(final String xpath){
        WebElement Element = null;
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver,60);
            Element = wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver driver){
                    return driver.findElement(By.xpath(xpath));
                }
            });

        } catch (NoSuchElementException e) {
            Element=null;
            // TODO: handle exception
            failAndMessage("没有找到>>>"+xpath);
        }
        return Element;

    }

    /**
     * 通过xpath和元素下标index查找元素
     * @param xpath
     * @return
     * @throws NoSuchElementException
     */
    public static WebElement findElementByXpathWithIndex(final String xpath,final int index){
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver,60);
             element= wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver driver){
                    return driver.findElements(By.xpath(xpath)).get(index);
                }
            });

        } catch (NoSuchElementException e) {
            element=null;
            // TODO: handle exception
            failAndMessage("没有找到>>>"+xpath);
        }
        return element;

    }

    /**
     * 通过坐标找到元素
     * @param arr
     */
    public static void clickElement_OverScreen(int[][] arr){

        try {
            TouchAction ta = new TouchAction(androidDriver);
            int width = androidDriver.manage().window().getSize().width;
            int height = androidDriver.manage().window().getSize().height;
            System.out.println("手机屏幕width："+width);
            System.out.println("手机屏幕：height"+height);
            //元素基准位置坐标
            int P_base_X = arr[0][0];
            int P_base_Y = arr[0][1];
            //元素所在页的屏幕基准尺寸
            int P_base_Screen_X = arr[1][0];
            int P_base_Screen_Y = arr[1][1];
            //获取到相对系数
            int point_X = formatNumber(P_base_X,P_base_Screen_X,width);
            int point_Y = formatNumber(P_base_Y,P_base_Screen_Y,height);

            //点击元素
            ta.tap(point_X,point_Y).release().perform();
            //点击之后，强制等待3s
            wait(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取相对坐标
     * @param P_1,P_2,P_3
     * @return int
     */
    public static int formatNumber(int P_1,int P_2,int P_3){
        //设定系数,控件在当前手机的坐标位置除以当前手机的最大坐标就是相对的系数了
        float dd = (float)P_1/(float)P_2;
        //格式化小数，不足的补0
        DecimalFormat df = new DecimalFormat("0.00000000");
        String rat = df.format((double)dd);
        float ff = Float.parseFloat(rat);
        //获取相对坐标
        int formatNum = (int)(ff*P_3);
        return formatNum;
    }


    /**
     * 等待N秒
     * @param num
     */
    public static void wait(int num){
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 失败提示
     * @param msg
     */
    public static void failAndMessage(String msg){
        Assert.assertTrue(false,msg);
    }


    /**
     * 截图方法，不需要自定义图片名称时使用
     */
    public static void getScreenshot(){
        getScreenshot("");
    }

    /**
     * 截图方法，必须传入caseName以区别图片
     *@param caseName name图片名称
     */
    public static void getScreenshot(String caseName){
        File screenShotFile = ((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        //目录不存在时创建目录
        if (new File(CaseConfig.CATCH_PICTURE).isDirectory()) {
            System.out.println("创建文件夹");
            new File(CaseConfig.CATCH_PICTURE).mkdir();
        }
        System.out.println("准备复制");
        try {
            FileUtils.copyFile(screenShotFile, new File(CaseConfig.CATCH_PICTURE+File.separator+ caseName + "-" + date+ ".png"));
        } catch (IOException e) {
            failAndMessage("截图失败");
            e.printStackTrace();
        }
    }

    /**
     * 在指定元素内滑动，需传入webElement，及滑动方向
     * @param webElement 传入webEelement
     * @param direction 传入方向值up,down,left,right
     */
    public static void slidingInElement(WebElement webElement,String direction){
        //获取控件开始位置
        Point location = webElement.getLocation();
        int startX = location.getX();
        int startY = location.getY();
        System.out.println("控件开始X："+startX+"控件开始Y："+startY);
        //获取坐标轴差
        Dimension q = webElement.getSize();
        int x = q.getWidth();
        int y = q.getHeight();
        System.out.println("坐标差值X："+x+"坐标差值Y："+y);
        // 计算出控件结束坐标
        int endX = x + startX;
        int endY = y + startY;
        System.out.println("控件结束坐标X："+endX+"控件结束坐标Y："+endY);
        try {
            if (direction.equals("down")) {
                System.out.println("向上滑动，开始像素值"+startY+"滑动到"+endY*9/10);
                androidDriver.swipe(endX/2, startY, endX/2, endY*9/10, 1000);
            }
            if (direction.equals("up")) {
                System.out.println("向下滑动，开始像素值"+endY*9/10+"滑动到"+startY);
                androidDriver.swipe(endX/2, endY*9/10, endX/2, startY, 1000);
            }

            if (direction.equals("left")) {
                System.out.println("向左滑动，开始像素值"+endX*9/10+"滑动到"+endX*1/10);
                androidDriver.swipe(endX*9/10, endY-30, endX*1/10, endY-30, 1000);
            }
            if (direction.equals("right")) {
                System.out.println("向右滑动，开始像素值"+endX*1/10+"滑动到"+endX*9/10);
                androidDriver.swipe(endX*1/10, endY/2, endX*9/10, endY/2, 1000);
            }
        } catch (Exception e) {
            // 异常截图
            getScreenshot();
            Reporter.log("控件内滑动失败");
        }

    }


}

import cn.nvwa.finance.base.DingTalkUtil;
import org.testng.TestNG;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class RunTestNG {

    /**
     * 运行testng.xml
     * @param
     * @return
     */
    public static void runTestNG(){
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("/Users/huangzhijuan/Documents/code/inkeUIAutotest/src/test/java/testng.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }

    /**
     * 定时任务，每天上午10:30定期执行一次
     * @param
     * @return
     */
    public void runTask(){
        //一天的毫秒数
//        long daySpan = 24 * 60 * 60 * 1000;
        long daySpan = 300000;
        //规定的每天时间10:30:00运行
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '10:30:00'");

        try {
            //首次运行时间
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
            //如果今天的已经过了，首次运行时间就改为明天
            if(System.currentTimeMillis() > startTime.getTime()){
                startTime = new Date(startTime.getTime() + daySpan);
            }

            Timer t = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("开始执行-----------------");
                    runTestNG();
                    System.out.println("结束执行-----------------");
                }
            };

            //以每24小时执行一次
            t.scheduleAtFixedRate(task,startTime,daySpan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行ui自动化脚本之前，一定要先开启本地服务，以便其他人能给访问到测试报告
     * 开启本地服务的方法：
     * 1、cd /Users/huangzhijuan/Documents/code/inkeUIAutotest/test-output/html/
     * 2、输入 python -m SimpleHTTPServer ，即在默认端口8000开启本地服务器，在浏览器输入127.0.0.1:8000 就可以访问该路径了
     */
    public static void main(String[] args) {

        //运行testng.xml
        runTestNG();

        String content = DingTalkUtil.getContent();
        DingTalkUtil.sendMsg(content);

    }
}

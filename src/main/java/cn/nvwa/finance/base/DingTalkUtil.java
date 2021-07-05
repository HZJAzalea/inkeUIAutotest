package cn.nvwa.finance.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class DingTalkUtil {


    /**
     *设置消息内容的json格式
     * @param content
     * @param isAtAll
     * @param mobileList
     * @return String
     */
    private static String buildReqStr( String content,boolean isAtAll,List<String> mobileList){

        //消息内容
        Map<String,Object> contentMap = Maps.newHashMap();
        contentMap.put("content",content);

        //通知人
        Map<String,Object> atMap = Maps.newHashMap();
        //1.是否通知所有人
        atMap.put("isAtAll",isAtAll);
        //2.通知具体人的手机号码列表
        atMap.put("atMobiles",mobileList);

        Map<String,Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype","text");
        reqMap.put("text",contentMap);
        reqMap.put("at",atMap);
        return JSON.toJSONString(reqMap);

    }

    /**
     * 发送消息
     * @param content
     * @return
     */
    public static void sendMsg(String content){
        //以下为测试专用的钉钉群信息
        //群机器人复制到的秘钥secret
        String secret = "SEC4343dcbca992804e34eee36778fe873582a002ac0f1dd1da59d42f6ad43661cb";
        //群机器人地址
        String dUrl = "https://oapi.dingtalk.com/robot/send?access_token=3be36925297c57a62122268ef5ee0f091b75b90822ad1306a005285189ccea7e";

        //获取系统时间戳
        Long timestamp = System.currentTimeMillis();
        //拼接
        String stringToSign = timestamp + "\n" + secret;

        try {
            //使用HmacSHA256算法计算签名
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"),"HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            //进行Base64 encode 得到最后的sign，可以拼接进url里
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            //钉钉机器人地址（配置机器人的webhook）
            String dingUrl = dUrl + "&timestamp=" + timestamp + "&sign=" + sign;

            //是否通知所有人
            boolean isAtAll = true;
            //通知具体人的手机号码列表
            List<String> mobileList = Lists.newArrayList();

            //组装请求内容
            String reqStr = buildReqStr(content, isAtAll, mobileList);
            //推送消息（http请求）
            String result = HttpReqUtil.postJson(dingUrl,reqStr);
            System.out.println("result == " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置发送消息的内容，即测试报告内容
     * @param
     * @return String
     */
    public static String getContent(){
        String content = null;

        //回归名称
        String tName = "回归名称";
        String task = "QA环境金融中台支付接口回归";
        String taskName = tName + "：" + task;

        //回归时间
        String timeName = "回归时间";
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        String taskTime = timeName + "：" + time;

        //执行流个数
        String eName = "执行流个数";
        int num = CaseConfig.TestCasePass + CaseConfig.TestCaseFail;
        String executeNum = eName + "：" + num;

        //错误率
        String rateName = "错误率";
        String percentStr = null;
        if(num != 0){
            double percent = (double) CaseConfig.TestCaseFail/num * 100;
            DecimalFormat fnum = new DecimalFormat("##0.00");
            percentStr = fnum.format(percent);
        }
        String rate =  percentStr + "%";
        String errorRate = rateName + "：" + rate;

        //测试报告详细信息
        String name = "详细信息";
//        String url = "/Users/huangzhijuan/Documents/code/inkeUIAutotest/test-output/html/index.html";
        //该地址是开启本地服务之后的地址
        String url = "http://192.168.17.111:8000/index.html";
        String info = name + "：" + url;

        content = taskName + "\n" + taskTime+ "\n" + executeNum + "\n" + errorRate + "\n" + info;
        return content;
    }

    public static void main(String[] args) {

        String content = getContent();
        sendMsg(content);

    }

}

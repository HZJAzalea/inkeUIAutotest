package cn.nvwa.finance.pageObject;

import cn.nvwa.finance.base.HttpReqUtil;
import cn.nvwa.finance.dao.GetOrderID;
import cn.nvwa.finance.entity.RenewSignRecordEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class OtherFinanceInterface {
    private static String host = "http://10.128.0.44:10001";

    /**
     * 扣款日期变更-得到扣款日期变更接口返回值
     * @param
     * @return
     */
    public static String getUpdateNextPayDateResult(int uid,String manner,String headName,String headValue){
        String contract_code = "2021001172683918";
        String result = null;
        try{
            //获取下次扣款日期
            GetOrderID getOrderID = new GetOrderID();
            Date nextPayDate = getOrderID.getNextPayDate(uid, manner);
            System.out.println(nextPayDate);
            //下次扣款日期加1天，获取到变更日期的时间戳
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextPayDate);
            calendar.add(Calendar.DATE,1);
            long changeData = calendar.getTimeInMillis()/1000;

            //扣款日期变更url及参数
            String url = host + "/api_server/v1/finance/subscrib/update_next_pay_date";
            String param = "{\"uid\":" + uid + ",\"contract_code\":\"" + contract_code + "\",\"next_pay_date\":" + changeData + "}";
            System.out.println("扣款日期变更请求body体: " + param);
            //执行扣款日期变更接口，得到接口返回值
            result = HttpReqUtil.sendPost(url, param, headName, headValue);
            System.out.println("扣款日期变更接口result: " + result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 周期扣款解约-得到周期扣款解约接口返回值
     * @param
     * @return
     */
    public static String getDeleteContractResult(int uid,String manner,String headName,String headValue){
        List<RenewSignRecordEntity> list = null;
        String result = null;
        String inke_sign_id = null;
        String app_id = "wxeb211e3b49054ebf";
        try{
            GetOrderID getOrderID = new GetOrderID();
            list = getOrderID.getDataFromRenewSignRecord(uid, manner);
            for(RenewSignRecordEntity rsre : list){
                inke_sign_id = rsre.getInke_sign_id();
                System.out.println("inke_sign_id: " + inke_sign_id);
            }

            //周期扣款解约url及参数
            String url = host + "/api_server/v1/finance/subscrib/delete_contract";
            String param = "{\"uid\":" + uid + ",\"manner\":\"" + manner + "\",\"buz_id\":\"" + inke_sign_id + "\",\"inke_sign_id\":\"" + inke_sign_id + "\",\"app_id\":\"" + app_id + "\",\"reason\":\"测试\"}";
            System.out.println("周期扣款解约body体: " + param);
            result = HttpReqUtil.sendPost(url, param, headName, headValue);
            System.out.println("周期扣款解约接口result: " + result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 退款-得到退款接口返回值
     * @param
     * @return
     */
    public static String getRefundResult(int uid,String manner,String headName,String headValue){

        String result = null;
        String orderId = null;
        try{

            GetOrderID getOrderID = new GetOrderID();
            orderId = getOrderID.getOrderIdFromOrderRecord(uid, manner);

            //退款url及参数
            String url = host + "/api_server/v1/finance/refund/refund";
            String param = "{" + "\"uid\":" + uid + ",\"manner\":\"" + manner + "\",\"buz_id\":\"" + orderId + "\",\"order_id\":\"" + orderId + "\",\"total_fee\":1,\"refund_fee\":1,\"inke_sign_id\":\"" + orderId + "\"}";
            System.out.println("退款请求body体：" + param);

            //操作退款接口请求，得到接口返回值
            result = HttpReqUtil.sendPost(url, param,headName,headValue);
            System.out.println("退款接口result: " + result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        int uid= 5673188;
//        String manner = "alipay";
//        String headName = "uberctx-_namespace_appkey_";
//        String headValue = "gmu";
//
//        String result = getDeleteContractResult(uid, manner, headName, headValue);
//    }
}

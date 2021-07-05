package cn.nvwa.finance.dao;

import cn.nvwa.finance.base.DbcpUtil;
import cn.nvwa.finance.entity.RenewSignRecordEntity;
import cn.nvwa.finance.entity.UserOrderRecordNewEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
public class GetOrderID {
    private static PreparedStatement ps = null;

    /**
     * 从数据库user_order_record_new_2021表查询出【普通支付和购买】的order_id(order_id也是inke_sign_id)
     * @param uid
     * @param manner 支付方式（weixin/alipay）
     * @return String
     */
    public String getOrderIdFromOrderRecord(int uid,String manner){
        //uid=1310809,manner='alipay'
        ResultSet rs = null;
        Connection connection = null;
        String order_id = null;
        String sql = "select * from user_order_record_new_2021 where uid=? and manner=? order by create_time DESC limit 0,1";

        try{
            connection = DbcpUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,manner);
            rs = ps.executeQuery();
            while (rs.next()){
                UserOrderRecordNewEntity userOrderRecordNewEntity = new UserOrderRecordNewEntity();
                 order_id = rs.getString("order_id");
                 userOrderRecordNewEntity.setOrder_id(order_id);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbcpUtil.close(rs,ps,connection);
        }

        return order_id;
    }


    /**
     * 从数据库renew_sign_record表查询出id和inke_sign_id
     * @param uid
     * @param manner 支付方式（weixin/alipay）
     * @return List<RenewSignRecordEntity>
     */
    public List<RenewSignRecordEntity> getDataFromRenewSignRecord(int uid,String manner){
        ResultSet rs = null;
        Connection connection = null;
        List<RenewSignRecordEntity> list = new ArrayList();
//        List list = new ArrayList();
        String sql = "select * from renew_sign_record where uid=? and manner=? order by create_time DESC limit 0,1";
        try{
            connection = DbcpUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,manner);
            rs = ps.executeQuery();
            while (rs.next()){
                RenewSignRecordEntity entity = new RenewSignRecordEntity();
                entity.setId(rs.getInt("id"));
                entity.setInke_sign_id(rs.getString("inke_sign_id"));
                list.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbcpUtil.close(rs,ps,connection);
        }
        return list;
    }

    public Date getNextPayDate(int uid,String manner){
        ResultSet rs = null;
        Connection connection = null;
        int id = 0;
        Date next_pay_date = null;

        List<RenewSignRecordEntity> list = getDataFromRenewSignRecord(uid, manner);
       for (RenewSignRecordEntity rsre : list){
           id = rsre.getId();
           System.out.println("id= " + id);
       }
        String sql = "select * from auto_deduct_task where sign_id=?";
       try{
           connection = DbcpUtil.getConnection();
           ps = connection.prepareStatement(sql);
           ps.setInt(1,id);
           rs = ps.executeQuery();
           while (rs.next()){
               next_pay_date = rs.getDate("next_pay_date");

           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           DbcpUtil.close(rs,ps,connection);
       }
        return next_pay_date;
    }

    public static void main(String[] args) {
//        int uid= 1310809;
//        String manner = "alipay";
//
//        String chatrightAlipayOrderId = null;
//        GetOrderID getOrderID = new GetOrderID();
//        chatrightAlipayOrderId = getOrderID.getChatrightAlipayOrderId(uid, manner);
//
//        //QA环境退款url
//        String url = "http://10.128.0.44:10001/api_server/v1/finance/refund/refund";
//        String param = "{" + "\"uid\":" + uid + ",\"manner\":\"" + manner + "\",\"buz_id\":\"" + chatrightAlipayOrderId + "\",\"order_id\":\"" + chatrightAlipayOrderId + "\",\"total_fee\":1,\"refund_fee\":1,\"inke_sign_id\":\"" + chatrightAlipayOrderId + "\"}";
//        String headName = "uberctx-_namespace_appkey_";
//        String headValue = "chatright";
//
//        //操作退款接口请求
//        String result = HttpReqUtil.sendPost(url, param,headName,headValue);
//        System.out.println(result);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//
//        int dm_error = jsonObject.getInteger("dm_error");
//
//        Assert.assertEquals(dm_error,0);



        int uid=5673188;
        String manner = "alipay";

        GetOrderID getOrderID = new GetOrderID();
        Date nextPayDate = getOrderID.getNextPayDate(uid, manner);
        System.out.println(nextPayDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nextPayDate);
        calendar.add(Calendar.DATE,3);
        long timeInMillis = calendar.getTimeInMillis()/1000;
        System.out.println(timeInMillis);

    }

}

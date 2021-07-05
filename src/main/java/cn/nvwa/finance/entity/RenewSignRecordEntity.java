package cn.nvwa.finance.entity;

import java.util.Date;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class RenewSignRecordEntity {
    private static final long serialVersionUID=1L;

    private int id;
    private int uid;
    private int charge_id;
    private int renew_duration_time;
    private Date create_time;
    private int sign_status;
    private int pay_status;
    private Date update_time;
    private String manner;
    private String app_mark;
    private String inke_sign_id;
    private String out_sign_id;
    private String sign_desc;
    private String extra;
    private int discount;
    private int first_deduct_price;
    private int bind_status;
    private String raw_query;
    private String req_header;

    public RenewSignRecordEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCharge_id() {
        return charge_id;
    }

    public void setCharge_id(int charge_id) {
        this.charge_id = charge_id;
    }

    public int getRenew_duration_time() {
        return renew_duration_time;
    }

    public void setRenew_duration_time(int renew_duration_time) {
        this.renew_duration_time = renew_duration_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getSign_status() {
        return sign_status;
    }

    public void setSign_status(int sign_status) {
        this.sign_status = sign_status;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getManner() {
        return manner;
    }

    public void setManner(String manner) {
        this.manner = manner;
    }

    public String getApp_mark() {
        return app_mark;
    }

    public void setApp_mark(String app_mark) {
        this.app_mark = app_mark;
    }

    public String getInke_sign_id() {
        return inke_sign_id;
    }

    public void setInke_sign_id(String inke_sign_id) {
        this.inke_sign_id = inke_sign_id;
    }

    public String getOut_sign_id() {
        return out_sign_id;
    }

    public void setOut_sign_id(String out_sign_id) {
        this.out_sign_id = out_sign_id;
    }

    public String getSign_desc() {
        return sign_desc;
    }

    public void setSign_desc(String sign_desc) {
        this.sign_desc = sign_desc;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getFirst_deduct_price() {
        return first_deduct_price;
    }

    public void setFirst_deduct_price(int first_deduct_price) {
        this.first_deduct_price = first_deduct_price;
    }

    public int getBind_status() {
        return bind_status;
    }

    public void setBind_status(int bind_status) {
        this.bind_status = bind_status;
    }

    public String getRaw_query() {
        return raw_query;
    }

    public void setRaw_query(String raw_query) {
        this.raw_query = raw_query;
    }

    public String getReq_header() {
        return req_header;
    }

    public void setReq_header(String req_header) {
        this.req_header = req_header;
    }
}

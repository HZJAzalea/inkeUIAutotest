package cn.nvwa.finance.entity;

import java.util.Date;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class UserOrderRecordNewEntity {
    private static final long serialVersionUID=1L;

    private int id;
    private String order_id;
    private int uid;
    private int money;
    private int num;
    private String money_kind;
    private int sys_num;
    private int charge_id;
    private String manner;
    private String sub_manner;
    private int client;
    private String cc;
    private int money_type;
    private int sandbox;
    private int agree;
    private Date create_time;
    private Date agree_time;
    private int oper_action;
    private String mchid;
    private String app_mark;
    private String out_trade_no;
    private Date update_time;
    private String lc;
    private String cv;
    private String ua;
    private String devi_info;
    private String client_ip;
    private String atom;
    private String raw_query;
    private String req_header;

    public UserOrderRecordNewEntity() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMoney_kind() {
        return money_kind;
    }

    public void setMoney_kind(String money_kind) {
        this.money_kind = money_kind;
    }

    public int getSys_num() {
        return sys_num;
    }

    public void setSys_num(int sys_num) {
        this.sys_num = sys_num;
    }

    public int getCharge_id() {
        return charge_id;
    }

    public void setCharge_id(int charge_id) {
        this.charge_id = charge_id;
    }

    public String getManner() {
        return manner;
    }

    public void setManner(String manner) {
        this.manner = manner;
    }

    public String getSub_manner() {
        return sub_manner;
    }

    public void setSub_manner(String sub_manner) {
        this.sub_manner = sub_manner;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public int getMoney_type() {
        return money_type;
    }

    public void setMoney_type(int money_type) {
        this.money_type = money_type;
    }

    public int getSandbox() {
        return sandbox;
    }

    public void setSandbox(int sandbox) {
        this.sandbox = sandbox;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getAgree_time() {
        return agree_time;
    }

    public void setAgree_time(Date agree_time) {
        this.agree_time = agree_time;
    }

    public int getOper_action() {
        return oper_action;
    }

    public void setOper_action(int oper_action) {
        this.oper_action = oper_action;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getApp_mark() {
        return app_mark;
    }

    public void setApp_mark(String app_mark) {
        this.app_mark = app_mark;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getDevi_info() {
        return devi_info;
    }

    public void setDevi_info(String devi_info) {
        this.devi_info = devi_info;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
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

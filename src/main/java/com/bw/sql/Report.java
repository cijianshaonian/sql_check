package com.bw.sql;

/**
 * @Description 检查报告
 * @Date 2021/6/6 2:24 上午
 * @Created by wangbing
 */
public class Report {
    private boolean pass; //通过标识
    private String msg; //错误提示
    private String sql;//sql语句

    public Report(String sql) {
        this.sql = sql;
    }

    public Report(boolean pass, String sql) {
        this.pass = pass;
        this.sql = sql;
    }

    public Report(boolean pass, String msg, String sql) {
        this.pass = pass;
        this.msg = msg;
        this.sql = sql;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "Report{" +
                "pass=" + pass +
                ", msg='" + msg + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}

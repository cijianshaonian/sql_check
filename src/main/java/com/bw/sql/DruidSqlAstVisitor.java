package com.bw.sql;

import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;

/**
 * @Description
 * @Date 2021/6/6 11:07 下午
 * @Created by wangbing
 */
public class DruidSqlAstVisitor extends SQLASTVisitorAdapter {

    private boolean pass = true;

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}

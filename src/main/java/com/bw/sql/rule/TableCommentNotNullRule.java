package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description table 必须有注释
 * @Date 2021/6/6 3:29 上午
 * @Created by wangbing
 */
public class TableCommentNotNullRule extends CheckRule{

    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE);
    }

    @Override
    public String getMSg() {
        return "表必须有注释";
    }

    @Override
    public DruidSqlAstVisitor initVisitor() {
        return new DruidSqlAstVisitor(){
            public void endVisit(SQLCreateTableStatement x) {
                setPass(x.getComment() != null);
            }
        };
    }
}

package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @Description WhereNotNullRule
 * @Date 2021/6/9 17:59
 * @Created by wangbing
 */
public class WhereNotNullRule extends CheckRule {
    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.UPDATE,SqlType.DELETE);
    }

    @Override
    public String getMSg() {
        return "update/delete语句建议指定where条件";
    }

    @Override
    public DruidSqlAstVisitor initVisitor() {
        return new DruidSqlAstVisitor(){
            public void endVisit(SQLUpdateStatement x) {
                SQLExpr where = x.getWhere();
                if(where == null){
                    setPass(false);
                }
            }
            public void endVisit(SQLDeleteStatement x) {
                SQLExpr where = x.getWhere();
                if(where == null){
                    setPass(false);
                }
            }
        };
    }
}

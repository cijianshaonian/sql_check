package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLPrimaryKey;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * @Description
 * @Date 2021/6/6 11:55 下午
 * @Created by wangbing
 */
public class TablePrimaryKeyNotNullRule extends CheckRule{
    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE);
    }

    @Override
    public String getMSg() {
        return "表必须有主键";
    }

    @Override
    protected void initVisitor() {
        druidSqlAstVisitor = new DruidSqlAstVisitor(){
            public void endVisit(SQLCreateTableStatement x) {
                List<SQLTableElement> tableElementList = x.getTableElementList();
                long count = tableElementList.stream().filter(sqlTableElement -> sqlTableElement instanceof SQLPrimaryKey).count();
                this.setPass(count > 0);
            }
        };
    }
}

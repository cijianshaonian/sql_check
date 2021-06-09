package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlKey;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlPrimaryKey;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description TableForeignKeyRule
 * @Date 2021/6/7 10:42
 * @Created by wangbing
 */
public class TableForeignKeyRule extends CheckRule{

    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE,SqlType.ALTER_TABLE);
    }

    @Override
    public String getMSg() {
        return "表不允许添加外键";
    }

    @Override
    public DruidSqlAstVisitor initVisitor() {
        return new DruidSqlAstVisitor(){
            public void endVisit(SQLCreateTableStatement x) {
                List<SQLTableElement> tableElementList = x.getTableElementList();
                long count = tableElementList.stream()
                        .filter(sqlTableElement -> sqlTableElement instanceof SQLForeignKeyConstraint)
                        .count();
                this.setPass(count == 0);
            }

            public void endVisit(SQLAlterTableAddConstraint x){
                setPass(false);
            }
        };
    }
}

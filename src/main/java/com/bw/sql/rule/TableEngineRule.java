package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableAddConstraint;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLForeignKeyConstraint;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @Description TableEngineRule
 * @Date 2021/6/7 10:58
 * @Created by wangbing
 */
public class TableEngineRule extends CheckRule{

    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE);
    }

    @Override
    public String getMSg() {
        return "存储引擎只允许使用InnoDB";
    }

    @Override
    public DruidSqlAstVisitor initVisitor() {
        return new DruidSqlAstVisitor(){
            public void endVisit(SQLCreateTableStatement x) {
                Map<String, SQLObject> tableOptions = x.getTableOptions();
                if(tableOptions != null){
                    SQLObject engine = tableOptions.get("ENGINE");
                    setPass("InnoDB".equals(engine.toString()));
                }

            }
        };
    }
}
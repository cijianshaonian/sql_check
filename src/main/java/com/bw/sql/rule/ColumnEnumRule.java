package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.statement.SQLAlterTableAddColumn;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description ColumnEnumRule
 * @Date 2021/6/9 18:07
 * @Created by wangbing
 */
public class ColumnEnumRule  extends CheckRule{

    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE,SqlType.ALTER_TABLE);
    }

    @Override
    public String getMSg() {
        return "不能使用enum类型（建议用tinyint/char代替）";
    }

    @Override
    public DruidSqlAstVisitor initVisitor() {
        return new DruidSqlAstVisitor(){
            public void endVisit(SQLCreateTableStatement x){
                List<SQLTableElement> tableElementList = x.getTableElementList();
                long count = tableElementList.stream().
                        filter(sqlTableElement -> sqlTableElement instanceof SQLColumnDefinition).
                        filter(sqlTableElement -> {
                            SQLColumnDefinition sqlColumnDefinition = (SQLColumnDefinition) sqlTableElement;
                            return "enum".equalsIgnoreCase(sqlColumnDefinition.getDataType().getName());
                        }).count();
                setPass(count == 0);
            }
            public void endVisit(SQLAlterTableAddColumn x){
                List<SQLColumnDefinition> columns = x.getColumns();
                long count = columns.stream().filter(sqlColumnDefinition -> "enum".equalsIgnoreCase(sqlColumnDefinition.getDataType().getName())).count();
                if(count > 0){
                    setPass(false);
                }
            }
        };
    }
}

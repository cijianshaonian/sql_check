package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.statement.*;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description ColumnCommentNotNullRule
 * @Date 2021/6/7 11:11
 * @Created by wangbing
 */
public class ColumnCommentNotNullRule  extends CheckRule{

    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE,SqlType.ALTER_TABLE);
    }

    @Override
    public String getMSg() {
        return "列必须有注释";
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
                            return sqlColumnDefinition.getComment() == null;
                        }).count();
                setPass(count == 0);
            }
            public void endVisit(SQLAlterTableAddColumn x){
                List<SQLColumnDefinition> columns = x.getColumns();
                long count = columns.stream().filter(sqlColumnDefinition -> sqlColumnDefinition.getComment() == null).count();
                if(count > 0){
                    setPass(false);
                }
            }
        };
    }
}
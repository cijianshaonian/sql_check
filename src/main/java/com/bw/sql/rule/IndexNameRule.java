package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlKey;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlPrimaryKey;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.SqlType;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description
 * @Date 2021/6/6 11:59 下午
 * @Created by wangbing
 */
public class IndexNameRule extends CheckRule{

    @Override
    public List<SqlType> scope() {
        return Lists.newArrayList(SqlType.CREATE_TABLE);
    }

    @Override
    public String getMSg() {
        return "索引名字必须以idx开头";
    }

    @Override
    protected void initVisitor() {
        druidSqlAstVisitor = new DruidSqlAstVisitor(){
            public void endVisit(SQLCreateTableStatement x) {
                List<SQLTableElement> tableElementList = x.getTableElementList();
                long count = tableElementList.stream()
                        .filter(sqlTableElement -> sqlTableElement instanceof MySqlKey && !(sqlTableElement instanceof MySqlPrimaryKey))
                        .filter(sqlTableElement -> {
                    MySqlKey key = (MySqlKey) sqlTableElement;
                    return !key.getName().getSimpleName().startsWith("idx");
                }).count();
                this.setPass(count == 0);
            }

            public void endVisit(SQLAlterTableAddIndex x){
                setPass(x.getName().getSimpleName().startsWith("idx"));
            }

            public void endVisit(SQLCreateIndexStatement x){
                setPass(x.getName().getSimpleName().startsWith("idx"));
            }
        };
    }
}

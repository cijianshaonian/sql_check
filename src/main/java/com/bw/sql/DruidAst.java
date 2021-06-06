package com.bw.sql;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;

/**
 * @Description
 * @Date 2021/6/6 2:29 上午
 * @Created by wangbing
 */
public class DruidAst{
    private SQLStatement sqlStatement;
    private String sql;

    public DruidAst(SQLStatement sqlStatement, String sql) {
        this.sqlStatement = sqlStatement;
        this.sql = sql;
    }

    public SqlType getSqlType() {
        if(sqlStatement instanceof SQLSelectStatement){
            return SqlType.SELECT;
        }else if(sqlStatement instanceof SQLUpdateStatement){
            return SqlType.UPDATE;
        }else if(sqlStatement instanceof SQLInsertStatement){
            return SqlType.INSERT;
        }else if(sqlStatement instanceof SQLDeleteStatement){
            return SqlType.DELETE;
        }else if(sqlStatement instanceof SQLCreateTableStatement){
            return SqlType.CREATE_TABLE;
        }
        return SqlType.OTHER;
    }

    public String getSql() {
        return this.sql;
    }

    public SQLStatement getSqlStatement() {
        return sqlStatement;
    }
}

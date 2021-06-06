package com.bw.sql;


import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;

/**
 * @Description SQL语法分析器
 * @Date 2021/6/6 2:07 上午
 * @Created by wangbing
 */
public class Analyzer {

    /**
     *
     * @param sql sql语句
     * @return sql语法树
     */
    public DruidAst analyze(String sql){
        DruidAst ast;
        try {
            sql = SQLUtils.formatMySql(sql);
            SQLStatement sqlStatement = SQLUtils.parseSingleMysqlStatement(sql);
            ast = new DruidAst(sqlStatement,sql);
        }catch (Exception e){
            throw new ParseException();
        }
        return ast;
    }
}

package com.bw.sql.rule;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.bw.sql.DruidAst;
import com.bw.sql.DruidSqlAstVisitor;
import com.bw.sql.Report;
import com.bw.sql.SqlType;
import java.util.List;

/**
 * @Description 具体的检查规则，每个规则器里有多个检查规则，如select类型的SQL语句会有多个检查规则
 * @Date 2021/6/6 2:23 上午
 * @Created by wangbing
 */
public abstract class CheckRule {

    /**
     * @param ast 抽象语法树
     * @return 规则检查报告
     */
    public Report match(DruidAst ast){
        DruidSqlAstVisitor druidSqlAstVisitor = initVisitor();
        SQLStatement sqlStatement = ast.getSqlStatement();
        sqlStatement.accept(druidSqlAstVisitor);
        return new Report(druidSqlAstVisitor.isPass(),getMSg(),ast.getSql());
    }

    /**
     * 规则作用域，SELECT、DELETE等
     * @return
     */
    public abstract List<SqlType> scope();

    public abstract String getMSg();

    public abstract DruidSqlAstVisitor initVisitor();
}

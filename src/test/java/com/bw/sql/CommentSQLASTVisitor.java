package com.bw.sql;

import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;

import java.util.List;

/**
 * @Description
 * @Date 2021/6/6 3:17 上午
 * @Created by wangbing
 */
public class CommentSQLASTVisitor extends SQLASTVisitorAdapter {

    public void endVisit(SQLAlterTableAddColumn x){
        List<SQLColumnDefinition> columns = x.getColumns();
        System.out.println("colums");
    }
    @Override
    public void endVisit(SQLAlterTableStatement x) {
        System.out.println("SQLAlterTableStatement");
    }

    @Override
    public void endVisit(SQLAlterTableAddConstraint x) {
        System.out.println("SQLAlterTableAddConstraint");
    }

    @Override
    public void endVisit(SQLCommentStatement x) {
        System.out.println("x");
    }

    @Override
    public boolean visit(SQLCommentStatement x) {
        System.out.println("y");
        return true;
    }

    @Override
    public void endVisit(SQLColumnPrimaryKey x) {
        System.out.println("key");
    }

    @Override
    public boolean visit(SQLColumnPrimaryKey x) {
        System.out.println("key 2");
        return true;
    }
}

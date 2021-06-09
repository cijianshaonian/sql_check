package com.bw.sql;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;

import java.util.List;
import java.util.function.Predicate;

/**
 * @Description TODO
 * @Date 2021/6/6 1:50 上午
 * @Created by wangbing
 */
public class Main {

    public static void main(String[] args) {

//        MySqlStatementParser parser = new MySqlStatementParser();
//        SQLStatement statement = parser.parseStatement();
        SQLStatement statement = SQLUtils.parseSingleMysqlStatement(getSql());
        MySqlCreateTableStatement mySqlCreateTableStatement = (MySqlCreateTableStatement) statement;

//        SQLExpr comment = mySqlCreateTableStatement.getComment();
//        List<SQLTableElement> tableElementList = mySqlCreateTableStatement.getTableElementList();
//        tableElementList.stream().filter(new Predicate<SQLTableElement>() {
//            @Override
//            public boolean test(SQLTableElement sqlTableElement) {
//                return true;
//            }
//        });

        CommentSQLASTVisitor visitor = new CommentSQLASTVisitor();
        statement.accept(visitor);
    }

    private static String getSql(){
        return  "CREATE TABLE `live` (\n" +
                "  `id` varchar(20) NOT NULL,\n" +
                "  `live_name` varchar(64) NOT NULL COMMENT '直播名称、主题',\n" +
                "  `status` int(4) NOT NULL COMMENT '状态、0：未开始，1：进行中，2：已结束',\n" +
                "  `show_status` tinyint(1) NOT NULL COMMENT '显示状态：0:草稿，1：上线',\n" +
                "  `cover` varchar(256) DEFAULT NULL COMMENT '封面图',\n" +
                "  `pre_start_time` bigint(20) DEFAULT NULL COMMENT '直播开始时间',\n" +
                "  `live_intro` varchar(256) DEFAULT NULL COMMENT '内容简介',\n" +
                "  `start_time` bigint(20) DEFAULT NULL COMMENT '直播实际开播时间',\n" +
                "  `end_time` bigint(20) DEFAULT NULL COMMENT '直播实际结束时间',\n" +
                "  `video_url` varchar(128) DEFAULT NULL COMMENT '视频回看地址',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  KEY `idx_status` (`status`) USING BTREE,\n" +
                "  KEY `idx_pre_start_time` (`pre_start_time`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='123';";
    }
}

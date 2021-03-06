package com.bw.sql;

import com.bw.sql.checker.AlterTableChecker;
import com.bw.sql.checker.Checker;
import com.bw.sql.checker.CreateTableChecker;
import com.bw.sql.rule.*;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Description tEST
 * @Date 2021/6/7 13:46
 * @Created by wangbing
 */
public class Test {

    static {
        ServiceLoader<Checker> checkers = ServiceLoader.load(Checker.class);
        Iterator<Checker> iteratorChecker = checkers.iterator();
        while (iteratorChecker.hasNext()) {
            Checker checker = iteratorChecker.next();
            CheckerHolder.registerChecker(checker);
        }
        ServiceLoader<CheckRule> services = ServiceLoader.load(CheckRule.class);
        Iterator<CheckRule> iteratorCheckRule = services.iterator();
        while (iteratorCheckRule.hasNext()) {
            CheckRule rule = iteratorCheckRule.next();
            List<SqlType> scopes = rule.scope();
            for (SqlType scope : scopes) {
                CheckerHolder.getCheckers().get(scope.toString()).registerRule(rule);
            }
        }
    }

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();

//        Checker createTableChecker = new CreateTableChecker();
//        CheckRule columnCommentNotNullRule = new ColumnCommentNotNullRule();
//        CheckRule indexNameRule = new IndexNameRule();
//        CheckRule tableCommentNotNullRule = new TableCommentNotNullRule();
//        CheckRule tableEngineRule = new TableEngineRule();
//        CheckRule tableForeignKeyRule = new TableForeignKeyRule();
//        CheckRule tablePrimaryKeyNotNullRule = new TablePrimaryKeyNotNullRule();
//        createTableChecker.registerRule(columnCommentNotNullRule);
//        createTableChecker.registerRule(indexNameRule);
//        createTableChecker.registerRule(tableCommentNotNullRule);
//        createTableChecker.registerRule(tableEngineRule);
//        createTableChecker.registerRule(tableForeignKeyRule);
//        createTableChecker.registerRule(tablePrimaryKeyNotNullRule);
//        CheckerHolder.registerChecker(createTableChecker);
//        Checker alterTableChecker = new AlterTableChecker();
//        alterTableChecker.registerRule(columnCommentNotNullRule);
//        alterTableChecker.registerRule(indexNameRule);
//        alterTableChecker.registerRule(tableForeignKeyRule);
//        CheckerHolder.registerChecker(alterTableChecker);

        Appender appender = new DefaultAppender();
        DruidAst ast = analyzer.analyze(getSql());
        Checker checker = CheckerHolder.getChecker(ast.getSqlType());
        List<Report> reports = checker.check(ast);
        //??????
        appender.print(reports);

    }

    private static String getSql(){
        return  "CREATE TABLE `live` (\n" +
                "  `id` varchar(20) NOT NULL,\n" +
                "  `live_name` varchar(64) NOT NULL COMMENT '?????????????????????',\n" +
                "  `status` int(4) NOT NULL COMMENT '?????????0???????????????1???????????????2????????????',\n" +
                "  `show_status` tinyint(1) NOT NULL COMMENT '???????????????0:?????????1?????????',\n" +
                "  `cover` varchar(256) DEFAULT NULL COMMENT '?????????',\n" +
                "  `pre_start_time` bigint(20) DEFAULT NULL COMMENT '??????????????????',\n" +
                "  `live_intro` varchar(256) DEFAULT NULL COMMENT '????????????',\n" +
                "  `start_time` bigint(20) DEFAULT NULL COMMENT '????????????????????????',\n" +
                "  `end_time` bigint(20) DEFAULT NULL COMMENT '????????????????????????',\n" +
                "  `video_url` varchar(128) DEFAULT NULL COMMENT '??????????????????',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  KEY `idx_status` (`status`) USING BTREE,\n" +
                "  KEY `idx_pre_start_time` (`pre_start_time`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='123';";
    }
}

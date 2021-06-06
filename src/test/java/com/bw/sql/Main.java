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
        MySqlStatementParser parser = new MySqlStatementParser("CREATE TABLE `qywx_content_msg` (\n" +
                "  `id` varchar(32) NOT NULL,\n" +
                "  `corp_id` varchar(32) DEFAULT NULL COMMENT '企业ID',\n" +
                "  `msg_id` varchar(100) DEFAULT NULL COMMENT '消息唯一标识',\n" +
                "  `publickey_ver` int(4) DEFAULT NULL COMMENT '加密此条消息使用的公钥版本号',\n" +
                "  `seq` bigint(20) DEFAULT NULL COMMENT '消息的seq值，标识消息的序号',\n" +
                "  `encrypt_random_key` varchar(1000) DEFAULT NULL COMMENT '使用publickey_ver指定版本的公钥进行非对称加密后base64加密的内容',\n" +
                "  `encrypt_chat_msg` text COMMENT '消息密文。需要业务方使用将encrypt_random_key解密得到的内容',\n" +
                "  `action` varchar(50) DEFAULT NULL COMMENT '消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型',\n" +
                "  `from_id` varchar(50) DEFAULT NULL COMMENT '消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid',\n" +
                "  `room_id` varchar(50) DEFAULT NULL COMMENT '群聊消息的群id。如果是单聊则为空',\n" +
                "  `msg_time` bigint(13) DEFAULT NULL COMMENT '消息发送时间戳',\n" +
                "  `msg_type` varchar(20) DEFAULT NULL COMMENT '消息类型',\n" +
                "  `origin_content` text COMMENT '原始消息内容',\n" +
                "  `translate_content` text COMMENT '解析后的消息内容',\n" +
                "  `to_list` text COMMENT '接收人列表',\n" +
                "  `is_del` int(2) DEFAULT '0' COMMENT '是否删除： 0否 1是',\n" +
                "  `create_at` bigint(13) DEFAULT '0' COMMENT '创建时间',\n" +
                "  `create_by` varchar(20) DEFAULT '' COMMENT '创建人ID',\n" +
                "  `create_name` varchar(50) DEFAULT '' COMMENT '创建人',\n" +
                "  `update_at` bigint(13) DEFAULT '0' COMMENT '修改时间',\n" +
                "  `update_by` varchar(20) DEFAULT '' COMMENT '修改人ID',\n" +
                "  `update_name` varchar(50) DEFAULT '' COMMENT '修改人',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  KEY `idx_corp_id` (`corp_id`) USING BTREE,\n" +
                "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n" +
                "  KEY `idx_seq` (`seq`) USING BTREE,\n" +
                "  KEY `idx_from_id` (`from_id`) USING BTREE,\n" +
                "  KEY `idx_room_id` (`room_id`) USING BTREE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业微信消息列表';");
        SQLStatement statement = parser.parseStatement();
        MySqlCreateTableStatement mySqlCreateTableStatement = (MySqlCreateTableStatement) statement;
        SQLExpr comment = mySqlCreateTableStatement.getComment();
        List<SQLTableElement> tableElementList = mySqlCreateTableStatement.getTableElementList();
        tableElementList.stream().filter(new Predicate<SQLTableElement>() {
            @Override
            public boolean test(SQLTableElement sqlTableElement) {
                return true;
            }
        });
        CommentSQLASTVisitor visitor = new CommentSQLASTVisitor();
        statement.accept(visitor);
    }
}

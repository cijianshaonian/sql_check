package com.bw.sql.checker;

import com.bw.sql.SqlType;

/**
 * @Description AlterTableChecker
 * @Date 2021/6/7 13:44
 * @Created by wangbing
 */
public class AlterTableChecker extends Checker{
    @Override
    public String getName() {
        return SqlType.ALTER_TABLE.toString();
    }
}

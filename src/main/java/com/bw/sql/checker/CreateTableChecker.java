package com.bw.sql.checker;

import com.bw.sql.SqlType;


/**
 * @Description
 * @Date 2021/6/6 3:06 上午
 * @Created by wangbing
 */
public class CreateTableChecker extends Checker{
    @Override
    public String getName() {
        return SqlType.CREATE_TABLE.toString();
    }
}

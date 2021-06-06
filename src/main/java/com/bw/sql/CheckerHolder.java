package com.bw.sql;

import com.bw.sql.checker.Checker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 用来注册Checker，所有的Checker都必须注册在CheckerHolder才能生效
 * @Date 2021/6/6 2:28 上午
 * @Created by wangbing
 */
public class CheckerHolder {

    private static Map<String, Checker> checkers = new ConcurrentHashMap<>();

    public static void registerChecker(Checker checker){
        checkers.putIfAbsent(checker.getName(),checker);
    }

    public static void unRegisterChecker(Checker checker){
        checkers.remove(checker.getName());
    }

    public static  Map<String,Checker> getCheckers(){
        return checkers;
    }
}

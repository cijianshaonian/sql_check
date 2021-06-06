package com.bw.sql.checker;

import com.bw.sql.DruidAst;
import com.bw.sql.rule.CheckRule;
import com.bw.sql.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 规则检查器
 * @Date 2021/6/6 2:21 上午
 * @Created by wangbing
 */
public abstract class Checker {
    /**
     * @return 规则检查器的名称
     */
    public abstract String getName();


    /**
     * 规则集
     */
    protected List<CheckRule> rules = new ArrayList<>();

    public void registerRule(CheckRule rule){
        this.rules.add(rule);
    }

    /**
     * @param ast 抽象语法树
     * @return 规则检查报告
     */
    public List<Report> check(DruidAst ast){
        List<Report> reports = new ArrayList<>();
        for(CheckRule rule : rules){
            Report report = rule.match(ast);
            if (report != null){
                reports.add(report);
            }
        }
        return reports;
    }
}

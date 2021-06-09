package com.bw.sql;

import java.util.List;

/**
 * @Description DefaultAppender
 * @Date 2021/6/7 14:02
 * @Created by wangbing
 */
public class DefaultAppender  implements Appender{
    @Override
    public void print(List<Report> result) {
        if (result == null || result.size() < 1){
            return;
        }
        System.out.println("========报告如下=========");

        for (Report report : result){
            //不通过才打印
            if (!report.isPass()){
                System.out.println(report);
                System.out.println();
            }
        }
    }
}

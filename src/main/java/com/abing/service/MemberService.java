package com.abing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    @Async //相当于这个方法重新开辟了单独线程进行执行
    //原理思路：使用aop技术在运行时创建一个单独的线程进行执行 不用@Async时则创建一个新线程执行
    public String addMemberAndEmail(){
        log.info("2");
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
        log.info("3");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.info("2");
//                try{
//                    Thread.sleep(5000);
//                }catch (Exception e){
//
//                }
//                log.info("3");
//            }
//        }).start();
        return "abing abing";
    }
}

package com.itheima.app.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HelloScheduled
 * @Description TODO
 * @Author 20609
 * @Date 2024/1/11 15:03
 * @Version 1.0
 */

@Slf4j
@Component
//@EnableAsync
@EnableScheduling
public class HelloScheduled {
    /**
     * 1、在Spring中表达式是6位组成，不允许第七位的年份
     * 2、在周几的的位置,1-7代表周一到周日
     * 3、定时任务不该阻塞。默认是阻塞的.
     *      1）、可以让业务以异步的方式，自己提交到线程池
     *              CompletableFuture.runAsync(() -> {
     *         },execute);
     *
     *      2）、支持定时任务线程池；设置 TaskSchedulingProperties spring线程池的默认配置是在 TaskSchedulingAutoConfiguration中配置的 默认的线程数为1
     *        spring.task.scheduling.pool.size: 5
     *        发现不起作用。
     *      3）、让定时任务异步执行
     *          异步任务
     *      解决：使用异步任务 + 定时任务来完成定时任务不阻塞的功能
     *
     */
    //@Async
    @Scheduled(cron = "*/5 * * ? * 4")
    public void hello() {
        log.info("hello...");
        try { TimeUnit.SECONDS.sleep(5);  //这种方式就相当于每隔10s执行一次了。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

package com.itheima.app.oom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OOMAnalyzer 该类只是为了测试频繁GC导致的内存泄露，进而导致OMM问题发生时如何排查
 * @Description TODO
 * @Author 20609
 * @Date 2024/1/3 18:52
 * @Version 1.0
 */
@RestController
public class OOMAnalyzer {
    List<Worker> workers = new ArrayList<Worker>();
    @GetMapping("/heap")
    public String heap() throws Exception{
        while(true){
            workers.add(new Worker());
        }
    }

}

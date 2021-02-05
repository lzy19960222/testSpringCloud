package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //获取一些配置的信息，得到对应的服务
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
       return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }

    @RequestMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    //注册进来的微服务，获取一些消息
    @RequestMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discover=>services"+services);

        //得到一个具体微服务信息通过具体的服务id
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance :instances){
            System.out.println(instance.getHost()+"\t"+
                               instance.getPort()+"\t"+
                               instance.getUri()+"\t"+
                               instance.getServiceId()
                    );
        }
        return this.client;
    }
}

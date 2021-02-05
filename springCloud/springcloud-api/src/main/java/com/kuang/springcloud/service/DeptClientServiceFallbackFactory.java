package com.kuang.springcloud.service;


import com.kuang.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//降级
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("id=>"+id+"没有对应的信息，客户端提供了降级的信息，这个服务已经被关闭")
                        .setDb_source("没有数据");
            }

            public List<Dept> queryAll() {
                return null;
            }

            public Boolean addDept(Dept dept) {
                return null;
            }
        };
    }
}

package com.kuang.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    //配置负载均衡实现RestTemplate
    //IRule
    // RoundRobinRule（轮询策略）
    // WeightedResponseTimeRule （权重配置）
    //RetryRule 重试的策略 会先按照轮询获取服务，如果服务获取失败，则会在指定的时间内重试
    //AvailabilityFilteringRule ：会先过滤掉，跳闸，访问故障的服务 （如果不配置，轮训到故障服务就会报错）
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }



}

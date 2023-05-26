package com.example.course.service;

import com.example.course.service.impl.CustomLoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SUBSCRIPTION-SERVICE")
@LoadBalancerClient(name = "SUBSCRIPTION-SERVICE",
        configuration = CustomLoadBalancerConfiguration.class)
public interface SubscriptionClient {

    @GetMapping("/subscription/port")
    String getPort();
}

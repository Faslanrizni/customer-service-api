package com.devstack.customerserviceapi.feigns;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="${order.service.url}",path = "/api/v1/orders",value = "order-feign-client")
public interface OrderFeignClient {
}

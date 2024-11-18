package com.enba.integrate.nacosdiscovery.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("discovery")
public class DiscoveryController {

  @NacosInjected private NamingService namingService;

  /**
   * 通过服务名称获取所有实例
   *
   * @param serviceName 服务名称
   * @return 返回服务的所有实例列表
   * @throws NacosException 可能抛出的Nacos异常
   */
  @RequestMapping(value = "/get", method = GET)
  @ResponseBody
  public List<Instance> get(@RequestParam String serviceName) throws NacosException {
    return namingService.getAllInstances(serviceName);
  }
}

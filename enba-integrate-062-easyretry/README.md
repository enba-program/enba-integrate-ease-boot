### Easy Retry(灵活，可靠和快速的分布式任务重试和分布式任务调度平台)
[官网文档](https://www.easyretry.com/)

[官方demo地址](https://gitee.com/opensnail/snail-job-demo)

---

####Retry重试模块
~~~
在当前广泛流行的分布式系统中，确保系统数据的一致性和正确性是一项重大挑战。为了解决分布式事务问题，涌现了许多理论和业务实践，其中BASE理论 (opens new window)是目前业界广泛接受的分布式一致性理论。

基于BASE理论，采用柔性事务并优先保障系统的可用性和数据的最终一致性已逐渐成为技术共识。

为了确保分布式服务的可用性和数据一致性，并防止由于网络抖动、连接超时等问题导致短时不可用的情况，根据"墨菲定律"，在核心流程中增加重试和数据核对校验的动作成为提高系统鲁棒性常用的技术方案。

在此背景下EasyRetry应运而生。EasyRetry是一款基于BASE思想实现的分布式服务重试组件，旨在通过重试机制确保数据的最终一致性。它提供了控制台任务观测、可配置的重试策略、重试后执行回调以及丰富地告警配置等功能。通过这些手段，可以对异常数据进行全面监测和回放，从而在确保系统高可用性的同时，大大提升数据的一致性。
~~~

####Job任务调度模块
~~~
Easy Retry 任务调度模块是一个分布式高性能任务调度框架，无需依赖外部中间件即可实现秒级任务间隔调度。支持多种执行模式，如集群模式、广播模式和分片模式，

同时提供多种阻塞策略，如丢弃、覆盖和并行，以提高任务执行效率。完善的监控和实时日志功能，帮助开发人员实时监控任务执行状态。
~~~
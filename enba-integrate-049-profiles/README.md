### SpringBoot(配置文件加载的优先级顺序)

#### 看图
![](profiles.png)

##### 总结
- 在同一路径下（比如都在classpath下）三者的优先级顺序是.properties> .yml> .yaml
- application.properties > application.yml > application.yaml
- application-{profile}.properties > application-{profile}.yml > application-{profile}.yaml
-  application-{profile}.*** > application.***
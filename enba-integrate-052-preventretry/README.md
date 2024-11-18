### PreventRetry(防重复提交)

#### 配置类
```yml
enba:
  request:
    prevent-retry:
      #      开关
      enabled: true
      #      缓存时间
      ttl: 1m
      #      请求头标识
      prevent-key: preventId
```
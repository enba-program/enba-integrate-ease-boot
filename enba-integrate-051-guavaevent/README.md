### guava(谷歌内部事件机制)

~~~
在Spring Boot中使用内部事件（ApplicationEvent）可以让你的应用程序组件之间进行解耦的通信。内部事件机制允许你在应用的不同部分发布事件，并由特定的监听器来处理这些事件。
~~~

~~~
ApplicationEvent和 MQ队列虽然实现的功能相似，但是MQ还是有其不可替代性的，最本质的区别就是MQ可以用于不同系统之间的消息发布，而SpringEvent这种模式只能在一个系统中，也就是要求必须是同一个Spring容器。
~~~

~~~
有两个重要的类，一个是事件，一个是监听。事件要继承ApplicationEvent类，监听要实现ApplicationListener接口
~~~
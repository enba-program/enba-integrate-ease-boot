### license-maven-plugin(版权信息)

~~~
在JAVA文件头加版权信息
~~~

#### 引入插件配置
```xml
<build>
    <plugins>
      <plugin>
        <groupId>com.mycila</groupId>
        <artifactId>license-maven-plugin</artifactId>
        <version>2.5</version>
        <configuration>
          <header>license/header.txt</header>
          <headerDefinitions>
            <headerDefinition>license/enba-header.xml</headerDefinition>
          </headerDefinitions>
          <includes>
            <include>src/main/java/**/*.java</include>
          </includes>
          <!--排除文件-->
          <excludes>
            <exclude>**/*.properties</exclude>
            <exclude>*.sh</exclude>
            <exclude>*.yml</exclude>
            <exclude>.editorconfig</exclude>
            <exclude>.gitignore</exclude>
            <exclude>**/*.md</exclude>
            <exclude>**/*.xml</exclude>
          </excludes>

        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>check</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

####该XML配置用于Maven项目，功能如下：
- 使用license-maven-plugin插件检查和管理源文件的许可头部。
- 设置许可证头部文件为license/header.txt，并定义特定格式使用license/enba-header.xml。
- 仅检查src/main/java目录下的Java文件。
- 排除多种类型的文件不进行检查，例如.properties、.sh、.yml等文件。
- 在构建过程中执行check目标，确保所有指定的文件包含正确的许可证头部信息。
### spring-boot-starter-validation校验框架

**Spring Boot Starter Validation是一个Spring Boot模块**

- **Hibernate Validator**：Hibernate Validator是JavaBean Validation（JSR
  380）规范的参考实现，提供了一组用于验证Java对象的约束和注解。
- **javax.validation API**：javax.validation API是JavaBean Validation的API，定义了可用于验证的约束和注解集。
- **Spring Validation**：Spring Validation是一个模块，用于将JavaBean Validation与Spring
  Framework集成，包括对方法参数和返回值的验证支持。

---

#### 注解全解

##### 空检查

- @Null 验证对象是否为null
- @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
- @NotEmpty 检查约束元素是否为NULL或者是EMPTY.
- @NotBlank 检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.

##### Booelan检查

- @AssertTrue 验证 Boolean 对象是否为 true
- @AssertFalse 验证 Boolean 对象是否为 false

##### 长度检查

- @Size(min=, max=)    验证对象（Array,Collection,Map,String）长度是否在给定的范围之内
- @Length(min=, max=)    验证字符串的长度

##### 日期检查

- @Past 验证 Date 和 Calendar 对象是否在当前时间之前
- @Future 验证 Date 和 Calendar 对象是否在当前时间之后
- @Pattern 验证 String 对象是否符合正则表达式的规则

##### 数值检查

- @Min 验证 Number 和 String 对象是否大等于指定的值
- @Max 验证 Number 和 String 对象是否小等于指定的值
- @DecimalMax 被标注的值必须不大于约束中指定的最大值
- @DecimalMin 被标注的值必须不小于约束中指定的最小值
- @Digits 验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。
- @Range 被注释的元素必须在合适的范围内

##### 自定义脚本检查

@ScriptAssert 自定义脚本检查

--- 

#### @validated和@valid不同点
- @valid 可以注解在成员属性(字段)上,但是@Validated只能用在类，方法和方法参数上面
- @valid 可以做嵌套校验
- 在controller层使用@valid不需要加@Validated
- @Validated可以用在其他被spring管理的类上，从而让方法参数上面的@Size等注解生效





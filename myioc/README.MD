## 实现步骤

1. 通过dom4j解析xml得到Document文档 
2. 遍历文档所有Bean标签 
3. 解析每一个Bean标签 封装一个BeanDefinition对象 
4. 解析每一个Bean标签下的所有Property标签 封装一个Property对象 
5. 将BeanDefinition和Property对象注册到容器

### （1）getBean实现步骤
1. 先从单例的map集合中获取 是否有指定beanName的对象
   有直接返回
   没有下一步
2. 从注册集合中获取bean的定义对象
   有下一步
   没有抛异常NoSuchBeanDefinition
3. 判断bean的scope作用域
   singleton单例
   createBean对象
   存入单例集合
   返回对象
   prototype多例
   createBean对象
   返回对象
4. createBean方法
   获取BeanDefinition中的className
   通过反射API得到Class对象
   通过反射API得到bean实例
   获取BeanDefinition中依赖的属性列表
   实现属性的依赖注入

### （2）扩展: 容器如何创建对象

IOC容器在准备创建对象时， 会判断是否有配置 factory-method方法

如果有配置 会调用factory-method所指向的方法构建对象.

如果没配置，会检查是否有配置构造参数

无构造参数: 调用默认构造器创建对象

有构造参数: 根据参数情况匹配对应的构造器

### （3）扩展: bean的生命周期

spring 容器中的bean的完整生命周期一共分为十一步完成。

1.bean对象的实例化
2.封装属性，也就是设置properties中的属性值
3.如果bean实现了BeanNameAware，则执行setBeanName方法,也就是bean中的id值
4.如果实现BeanFactoryAware或者ApplicationContextAware ，需要设置setBeanFactory或者上下文对象setApplicationContext
5.如果存在类实现BeanPostProcessor后处理bean，执行postProcessBeforeInitialization，可以在初始化之前执行一些方法
6.如果bean实现了InitializingBean，则执行afterPropertiesSet，执行属性设置之后的操作
7.调用执行指定的初始化方法
8.如果存在类实现BeanPostProcessor则执行postProcessAfterInitialization，执行初始化之后的操作
9.执行自身的业务方法
10.如果bean实现了DisposableBean，则执行spring的的销毁方法
11.调用执行自定义的销毁方法。
扩展: bean的循环依赖问题

A 依赖 B B 依赖 A 产生闭环，称为循环依赖

Spring 默认允许单例对象的属性注入 所产生的循环依赖
单例对象的循环依赖 Spring通过3级缓存来解决 比如一个类A中有一个属性是B类，B类中有一个属性是A类，这时看Spring是怎么解决他们的相互依赖的。Spring注入一个类的大体步骤分为两部分，一是先完成对类的构造工作，二是会对类的属性进行设置和填充。首先Spring构造A类，通过
AbstractAutowireCapableBeanFactory的doCreateBean方法中调用addSingletonFactory方法将A类曝光到singletonFactories中。

这时完成A的构造后，需要填充B属性，继续第二步，发现B还没有构造，于是开始B流程的构造过程，构造的时候发现需要填充A，从第三层缓存singletonFactories中找到A(此时的A还没有完全构造完成，但是可以拿到A的一个引用)，B拿到A的引用后，完成B自己的填充属性工作，完成初始化工作，把自己放到第一层缓存singletonObjects中。这时回到A的这边，在拿到B对象后，完成自己的填充属性工作。

![](https://p3-sign.toutiaoimg.com/pgc-image/2d5bb22090c047ac857eea08188667a0~noop.image?_iz=58558&from=article.pc_detail&x-expires=1667024343&x-signature=dLImBgMIpxcj7jGaQ2MO44r27JQ%3D)

- 如果是构造器依赖属性 会报循环依赖异常
- 如果对象都是多例对象 会报循环依赖异常
- 如果设置allowCircularReferences为false 会报循环依赖异常

```java
protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
   if (this.allowBeanDefinitionOverriding != null) {
      beanFactory.setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
   }
   if (this.allowCircularReferences != null) {
      beanFactory.setAllowCircularReferences(this.allowCircularReferences);
   }
}
```

### （4）扩展: bean的覆盖问题

默认情况: 同一个配置文件中出现id相同的bean会报错，不同的配置文件出现id相同的bean 后加载的bean会将先加载的bean覆盖掉，称为bean的覆盖，bean的覆盖不会报错，但可能影响我们的项目 ， 可以通过属性设置 不允许bean的覆盖
allowBeanDefinitionOverriding设置为false
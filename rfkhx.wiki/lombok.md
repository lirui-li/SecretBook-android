# Lombok

在前面的开发中使用Lombok的好处大家应该已经体会了，但[Lombok的滥用会带来一系列问题](https://mp.weixin.qq.com/s/ecAyadLrVChEEIluJpXO4Q)。我们对Lombok的使用做以下约定。

## 版本

### maven
```xml
<dependencies>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.18.12</version>
		<scope>provided</scope>
	</dependency>
</dependencies>
```

### Gradle

目前我们还没有接触过这个构建工具，但如果后面学安卓开发一定会用到。

先占坑，这里有[官方的介绍](https://projectlombok.org/setup/gradle)。

## 插件

## VSCode
VSCode天下第一！好吧，我不是来讨论这个的，但VSCode我们大概是会用到的。毕竟VSCode在前端开发非常有名，我们做桌面版如果用`Node.js`VSCode会比较方便。

如果只是前端用到就不需要说明了，但如果习惯在VSCode上开发的话还是需要插件的。

直接在VSCode的拓展中搜索Lombok，无需配置。

## Idea

占坑。

## Android Studio

占坑。

## 特性

Lombok支持的特性很多，但如果滥用的话代码会面目全非，反而不好读懂。我们只使用其中的一部分。

- [@Getter/@Setter](https://projectlombok.org/features/GetterSetter)
- [@ToString](https://projectlombok.org/features/ToString)
- [@EqualsAndHashCode](https://projectlombok.org/features/EqualsAndHashCode)
- [@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor](https://projectlombok.org/features/constructor)
- [@Data](https://projectlombok.org/features/Data)
- [@Builder](https://projectlombok.org/features/Builder)
- [@Slf4j](https://projectlombok.org/features/log)
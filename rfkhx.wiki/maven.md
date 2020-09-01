# maven
maven用来管理项目的依赖和生命周期，安装倒是相对简单：

## Windows安装maven
1. 解压
2. 设置环境变量
3. 到控制台`mvn -v`，看到版本信息即成功

## Ubuntu
```bash
sudo apt install maven
```

> 需要注意的是，如果你的Java是用sdkman安装的，那maven建议也用sdkman安装。

## 配置阿里源
settings.xml位置：
- 全局 `${M2_HOME}/conf/settings.xml`
- 用户 `${user.home}/.m2/settings.xml`

${M2_HOME}指maven的安装位置，可使用mvn -v查看。${user.home}指用户目录。

如我的这两个文件分别在`/usr/share/maven/conf/settings.xml`和`~/.m2/settings.xml`。

如果你的电脑上只有全局配置文件，复制一份到用户配置文件的位置即可。

修改该文件的<mirrors></mirrors>节点，加入阿里云的镜像：

```xml
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
```

建议是两个配置文件都改。
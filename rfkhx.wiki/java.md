# Java

> Java天下第一！不接受反驳！

为了减少出错，这里我们统一Java开发环境。毕竟现在java8还是主流，我们选择用java8进行开发。

你可以使用`java -version`和`javac -version`查看当前的java版本。

我们要求使用8u191以上的版本进行开发。

## 安装

### Ubuntu

```bash
sudo apt install openjdk-8-jdk
```

如果你安装了多个jdk，可以用下面的命令切换java版本

```bash
sudo update-alternatives --config java
```

执行后输入数字选择即可。

```bash
sudo update-alternatives --config javac
```

同上操作。

### deepin

deepin下的jdk版本过低，maven的测试功能会遇到莫名其妙的问题，建议不要使用deepin或者安装sdkman控制java版本。

> sdkman也有缺点，首先是只对当前用户起效，换用户或者sudo下就找不到命令；其次在桌面启动器中会找不到命令，因为没有激活环境；最后就是特别慢，没有梯子基本等一天吧。

### Windows

见群文件。

![image-20200225090118853](.pics/image-20200225090118853.png)

Windows下如果想改变java版本，只需要改变环境变量`PATH`和`JAVA_HOME`的值。

## java8中的新语言特性

虽然不觉得我们会用到多少，还是列举下吧：

- [Lambda表达式](https://www.runoob.com/java/java8-lambda-expressions.html)

- [方法引用](https://www.runoob.com/java/java8-method-references.html)
- [函数式接口](https://www.runoob.com/java/java8-functional-interfaces.html)
- [默认方法](https://www.runoob.com/java/java8-default-methods.html)
- [Stream](https://www.runoob.com/java/java8-streams.html)
- [Optional类](https://www.runoob.com/java/java8-optional-class.html)
- [ Nashorn, JavaScript 引擎](https://www.runoob.com/java/java8-nashorn-javascript.html)
- [新的日期时间 API](https://www.runoob.com/java/java8-datetime-api.html)
- [ Base64](https://www.runoob.com/java/java8-base64.html)
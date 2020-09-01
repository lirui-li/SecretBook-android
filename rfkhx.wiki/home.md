# RKX项目·密码管理工具

## 题目及项目愿景

### 项目名字、图标
项目定名“密书”，图标未确定，见#8。

### 为什么选择这个项目
选择这个题目我们小组经过了再三斟酌。最初的想法是实现一个考试系统，但让宇坤老师看，他认为我们这个想法比较普通，教务系统没有什么能做出新意和用上流行技术的方面，而且系统比较复杂，想全部实现工程量太大。

后来在讨论中曾打算考虑做一个资讯整合的平台，通过爬虫采集各个网站热搜信息，对结果进行文本聚类、情感分析等，将有关联的事件组织起来。但后来考虑事实上不同平台的热搜信息关联其实不是很密切，也只有有重大事件发生的时候关联才稍微紧密写些。理论上没问题，但估计实现出来效果不是很好。

现在很多网站都要注册，都要准备一个密码。密码一多，管理就容易混乱。假如设置不同的密码，全部记住是几乎不可能的，而保存在文件里又比较危险。如果设置相同的密码，只要有一家网站密码遭到泄漏，那么黑客通过[撞库](https://baike.baidu.com/item/%E6%92%9E%E5%BA%93)就能威胁到你其他网站的账号安全。我们打算做的密码管理工具，就是为了解决这个问题，兼顾安全性和便捷性，让用户更方便地管理各个网站的密码。

### 准备做成什么样
- 跨平台支持 我们打算实现安卓APP版本和(electron)跨平台桌面版本。如果时间有盈余，会考虑浏览器插件版本，与浏览器进行整合，使用更方便。
- 密码存储 存储不同网站的密码，并能通过网址或名称搜索，点击一键复制。
- 密码生成 帮助用户生成高强度密码
- 安全 通过验证主密码/指纹识别/人脸识别方可访问密码库
- 密码校验 帮助用户检查密码的安全性（是否在已知被泄漏的密码库中，是否与大量用户重复）

### 前景
随着平台的多样化、随着信息安全日益得到重视，密码管理工具肯定越来越有市场。

### 可行性
经过讨论，我们认为该项目以我们团队的能力是可以实现的。

## 文档

- [愿景](vision)
- [干系人分析](stakeholders)
- [项目实践中的质量活动和计划](plan)

## 阿里云code平台功能
阿里云的Code平台是用GitLab搭建的git仓库管理工具。用过github没？就是那个类似的平台，不过这个是中文的，而且访问更快一点，所以我们选择这个。

- [阿里云Code基本知识指南](https://code.aliyun.com/help/code-basics/README.md)
- [缺陷](issues)
- [Wikis](wikis)
- [Markdown](markdown)

## 工具和技术方案
- [docker](docker)
- [maven](maven)
- [lombok](lombok)
- [ProcessOn](https://www.processon.com/)
- [nvm](nvm)
- [java](java)
- [ssr](ssr)

## 我们的团队
[项目团队](team)
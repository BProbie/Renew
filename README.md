# ⭐原创开源工具

### 工具名称：Renew（更新工具）

### 工具版本：1.1.0（稳定版）

### 开发语言：Java（JDK-21.0.8）

### 开发时间：2025年10月01日 ~ 至今持续更新

### 开源地址（Github）：https://github.com/BProbie/Renew/

### 开源协议（MIT）：https://github.com/BProbie/Renew/raw/refs/heads/master/LICENSE/

### 下载地址：https://github.com/BProbie/Renew/releases/tag/1.1.0/

### 依赖工具：Maven

### 依赖技术：

- ##### JUnit（JUnit5）



# ⭐工具简介

### 基于Java环境，手动及嵌入两用，兼容鲁棒性网络环境，的更新工具



# ⭐快速开始

### 下载工具文件

```shell
https://github.com/BProbie/Renew/releases/download/1.1.0/Renew.jar
```



# ⭐使用教程

### 手动调用

##### ① 帮助文档

```shell
java -jar Renew.jar help
```

```shell
java -jar Renew.jar help
java -jar Renew.jar version
java -jar Renew.jar [FullFileUrl] [FullFilePath]
java -jar Renew.jar [FullFileUrl] [FullFilePath] [IsOpen(True|False)]
```

##### ② 工具版本

```shell
java -jar Renew.jar version
```

```shell
RENEW-v1.1.0
```

##### ③ 使用示例

```shell
java -jar Renew.jar https://github.com/BProbie/Renew/releases/download/1.1.0/Renew.jar C:\Users\probie\Desktop\Renew.jar true
```

```shell
Download Success
```

### 嵌入调用

##### ① 添加JitPack仓库

```xml
<repositories>

    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy> 
        </snapshots>
    </repository>
    
</repositories>
```

##### ② 添加工具依赖

```xml
<dependencies>
    
    <dependency>
        <groupId>com.github.BProbie</groupId>
        <artifactId>Renew</artifactId>
        <version>master-SNAPSHOT</version>
    </dependency>
    
</dependencies>
```

#####  ③ 使用示例

```java
// 实例化更新工具对象(建造者模式)
Renew renew = Renew
    
    // 必填 远程文件网址
    .builder("https://github.com/BProbie/Renew/releases/download/1.1.0/Renew.jar")
    
    // 选填 用于指定Java目录以使用特定的Java环境(默认为环境变量中定义的Java环境)
    .javaFilePath("java")
    
    // 选填 用于指定Renew.jar更新工具的本地路径(默认为当前目录下的Renew.jar) 
    .renewFilePath(ComputerSystem.getInstance().getHere() + File.separator + "Renew.jar") 
    
    // 选填 用于指定文件下载的本地路径(默认为当前目录加上远程文件网站的最后一小段字符串)
    .fullFilePath(ComputerSystem.getInstance().getHere() + File.separator + "Renew.jar") 
    
     // 选填 用于设置更新完成后是否自动启动更新完成后的程序(默认为启用)
    .isOpen(true)
    
    // 构建实例化更新工具对象
    .build();

// 执行更新
renew.renew();
```



# ⭐更多功能

### 将版本号转成数值用于大小比较

```java
System.out.println(Renew.getInstance().turnVersionToNumber(Renew.getInstance().VERSION));
```

```shell
1001000
```

##### 仅适用于规范版本号，例va.b.c（即段数不大于3，且每小段的值不大于等于1k）



### 版本号大小比较方法

```java
System.out.println(Renew.getInstance().compareVersionWithSmallerVersion(Renew.getInstance().VERSION, "v1.0.0"));
```

```shell
true
```

```java
System.out.println(Renew.getInstance().compareVersionWithBiggerVersion(Renew.getInstance().VERSION, "v1.0.0"));
```

```shell
false
```

##### 适用于几乎所有格式的版本号



# ⭐项目结构

```markdown
Renew
├── .github
├── .gitignore
├── .idea # 已在仓库中删减
├── .mvn # 已在仓库中删除
├── LICENSE
├── out # 已在仓库中删除
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── probie
│   │   │           └── renew
│   │   │               ├── Main.java
│   │   │               ├── renew
│   │   │               │   ├── api
│   │   │               │   │   └── IRenew.java
│   │   │               │   └── Renew.java
│   │   │               └── system
│   │   │                   ├── api
│   │   │                   │   ├── IComputerSystem.java
│   │   │                   │   ├── IFileSystem.java
│   │   │                   │   └── INetworkSystem.java
│   │   │                   ├── ComputerSystem.java
│   │   │                   ├── FileSystem.java
│   │   │                   └── NetworkSystem.java
│   │   └── resources
│   │       └── META-INF
│   │           └── MANIFEST.MF
│   └── test
│       └── java
│           └── com
│               └── probie
│                   └── renew
│                       └── MainTest.java
└── target # 已在仓库中删除
```



# ⭐技术细节

### ① 实现了对网络传输过程中出现的异常问题的监控和自动修复，目前主要实现了在网络加速环境中对于SSL证书信任问题的默认信任。



# ⭐作者介绍

### 作者：probie

### 贡献：\[probie, probie, probie]



# ⭐疑问交流联系

### 如有疑问请通过提交Issue阐述，作者能看到且会经常查看！



# ❤❤❤

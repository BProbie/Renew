# ⭐原创开源工具

### 工具名称: Renew（更新工具）

### 工具版本: v1.0.0.0（稳定版）

### 开发语言: Java（JDK-21.0.8）

### 开发时间: 2025年10月01日 ~ 至今持续更新

### 开源地址: https://github.com/BProbie/Renew/

### 开源协议：https://github.com/BProbie/Renew/raw/refs/heads/master/LICENSE/

### 下载地址：https://github.com/BProbie/Renew/releases/tag/v1.0.0.0/

### 依赖工具：Maven

### 依赖技术：

- ##### JUnit（JUnit5）



# ⭐快速开始

### 下载工具文件

```shell
https://github.com/BProbie/Renew/releases/download/v1.0.0.0/Renew.jar
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
RENEW-v1.0.0.0
```

##### ③ 使用示例

```shell
java -jar Renew.jar https://github.com/BProbie/Renew/releases/download/v1.0.0.0/Renew.jar C:\Users\probie\Desktop\Renew.jar true
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
// 实例化更新工具对象
Renew renew = Renew.getInstance();

// 必填: Renew更新工具的本地文件路径
renew.setRenewFilePath(System.getProperty("user.dir") + File.separator + "Renew.jar");

// 必填: 需要更新的远程文件网址
renew.setFullFileUri("https://github.com/BProbie/Renew/releases/download/v1.0.0.0/Renew.jar");

// 必填: 需要更新到的本地文件路径
renew.setFullFilePath("C:\\Users\\probie\\Desktop\\Renew.jar");

// 可选: 更新完成后是否自动打开文件
renew.setIsOpen(true);

// 可选: 用于使用指定Java目录下的Java环境
renew.setJavaFilePath("java");

// 执行更新
renew.renew();
```



# ⭐项目结构

```markdown
Renew
├── .github/
├── .idea/ # 已在仓库中删减
├── .mvn/ # 已在仓库中删除
├── out/ # 已在仓库中删除
├── target/ # 已在仓库中删除
├── src/
│  ├── main/
│  │  ├── java/
│  │  │  └── com/
│  │  │     └── probie/
│  │  │        └── renew/
│  │  │           ├── Main.java
│  │  │           ├── Renew/
│  │  │           │  ├── Renew.java
│  │  │           │  └── Interface/
│  │  │           │     └── IRenew.java
│  │  │           └── System/
│  │  │              ├── ComputerSystem.java
│  │  │              ├── FileSystem.java
│  │  │              ├── NetworkSystem.java
│  │  │              └── Interface/
│  │  │                 ├── IComputerSystem.java
│  │  │                 ├── IFileSystem.java
│  │  │                 └── INetworkSystem.java
│  │  └── resources/
│  │     └── META-INF/
│  │        └── MANIFEST.MF
│  └── test/
│     └── java/
│        └── com/
│           └── probie/
│              └── renew/
│                 └── MainTest.java
├── .gitignore
├── LICENSE
├── pom.xml
└── README.md
```



# ⭐作者介绍

### 作者：probie

### 贡献：\[probie, probie, probie]



# ⭐疑问交流联系

### 如有疑问请通过提交Issue阐述，作者能看到且会经常查看！



# ❤❤❤

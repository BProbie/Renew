# Renew

Renew是一个Java项目，提供系统相关的功能接口和实现。

## Maven依赖配置

要在您的项目中使用Renew，请添加以下Maven依赖：

### 1. 添加JitPack仓库

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### 2. 添加依赖

```xml
<dependency>
    <groupId>com.github.BProbie</groupId>
    <artifactId>Renew</artifactId>
    <version>least</version>
</dependency>
```

## 项目结构

```
src/
└── main/
    └── java/
        └── com/
            └── probie/
                └── renew/
                    ├── Main.java
                    ├── Renew/
                    │   ├── Interface/
                    │   │   └── IRenew.java
                    │   └── Renew.java
                    └── System/
                        ├── Interface/
                        │   ├── IComputerSystem.java
                        │   ├── IFileSystem.java
                        │   └── INetworkSystem.java
                        ├── ComputerSystem.java
                        ├── FileSystem.java
                        └── NetworkSystem.java
```

## 功能模块

- **Renew**: 核心功能模块
- **System**: 系统相关功能，包括计算机系统、文件系统和网络系统

## 如何使用

1. 克隆项目到本地
2. 构建项目：`mvn clean install`
3. 在您的项目中添加Maven依赖
4. 导入所需的类并使用相应的接口和实现

## 构建要求

- Java 8+
- Maven 3.6+

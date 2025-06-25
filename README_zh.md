# 简介

Ginkgo通用模块

# 模块

## ginkgo-common-logging

### 简介

Ginkgo日志模块

### 系统变量

| 变量名                  | 描述          | 默认值         |
|----------------------|-------------|-------------|
| LOG_FILE_PATH        | 日志文件保存路径    | /var/log    |
| LOG_FILE_BASE_NAME   | 日志文件基础名称    | application |
| MAX_LOG_FILE_SIZE    | 单个文件的最大容量   | 100MB       |
| MAX_BACKUP_LOG_FILES | 最多保留的日志文件个数 | 30          |
| LOG_LEVEL            | 日志级别        | INFO        |

### JVM参数

| JVM参数名              | 描述                           | 默认值   |
|---------------------|------------------------------|-------|
| -Dlog2Console=false | Disable console log printing | true  |
| -Dlog2File=true     | Enable file log printing     | false |

## ginkgo-common-id-generator

### 简介

Ginkgo ID生成模块
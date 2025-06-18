# Description

Ginkgo universal module

# Modules

## ginkgo-common-logging

### Description

Ginkgo logging module

### System Variables

| Variable             | Description                           | Default Value |
|----------------------|---------------------------------------|---------------|
| LOG_FILE_PATH        | Path for saving log files             | /var/log      |
| LOG_FILE_BASE_NAME   | Basic name of log file                | application   |
| MAX_LOG_FILE_SIZE    | Maximum capacity of a single log file | 100MB         |
| MAX_BACKUP_LOG_FILES | Maximum number of log files           | 30            |
| LOG_LEVEL            | Log level                             | INFO          |

### JVM Arguments

| JVM Argument        | Description                  | Default Value |
|---------------------|------------------------------|---------------|
| -Dlog2Console=false | Disable console log printing | true          |
| -Dlog2File=true     | Enable file log printing     | false         |
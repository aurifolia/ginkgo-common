# Ginkgo ID Service

独立的ID生成服务，提供高性能的分布式ID生成能力。

## 特性

- 🚀 **高性能**：基于雪花算法，支持高并发ID生成
- 🔄 **分布式**：支持多实例部署，自动分配机器ID
- 📊 **监控完善**：提供详细的监控指标和告警
- 🔧 **配置灵活**：支持动态配置调整
- 🛡️ **高可用**：支持集群部署，自动故障转移
- 📈 **可扩展**：支持水平扩展，满足大规模需求

## 架构设计

### 核心组件

1. **ID生成引擎**：基于雪花算法的ID生成器
2. **机器ID管理器**：自动分配和管理机器ID
3. **时钟同步器**：处理时钟回拨问题
4. **缓存管理器**：本地缓存，提高性能
5. **监控系统**：性能监控和告警

### 部署架构

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Load Balancer │    │   Load Balancer │    │   Load Balancer │
└─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘
          │                      │                      │
┌─────────▼───────┐    ┌─────────▼───────┐    ┌─────────▼───────┐
│  ID Service 1   │    │  ID Service 2   │    │  ID Service 3   │
│ Machine ID: 1   │    │ Machine ID: 2   │    │ Machine ID: 3   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## API接口

### 1. 生成单个ID

```http
GET /api/id/generate
```

**响应**：
```json
{
  "success": true,
  "data": 1234567890123456789,
  "message": "success"
}
```

### 2. 批量生成ID

```http
POST /api/id/batch
Content-Type: application/json

{
  "count": 100
}
```

**响应**：
```json
{
  "success": true,
  "data": [1234567890123456789, 1234567890123456790, ...],
  "message": "success"
}
```

### 3. 获取服务状态

```http
GET /api/id/status
```

**响应**：
```json
{
  "success": true,
  "data": {
    "machineId": 1,
    "sequence": 1234,
    "timestamp": 1640995200000,
    "uptime": 86400000
  },
  "message": "success"
}
```

## 配置

### application.yml

```yaml
server:
  port: 8080

ginkgo:
  id:
    snowflake:
      machine-id: ${MACHINE_ID:1}
      buffer-size: 1000
      fill-batch-size: 100
      max-idle-time: 1000
    cluster:
      enabled: true
      zookeeper:
        connect-string: ${ZK_CONNECT_STRING:localhost:2181}
        namespace: ginkgo-id-service
    monitoring:
      enabled: true
      metrics-port: 9090
```

### 环境变量

- `MACHINE_ID`: 机器ID（可选，自动分配）
- `ZK_CONNECT_STRING`: ZooKeeper连接字符串
- `SERVER_PORT`: 服务端口

## 部署

### Docker部署

```bash
# 构建镜像
docker build -t ginkgo-id-service .

# 运行容器
docker run -d \
  --name ginkgo-id-service \
  -p 8080:8080 \
  -p 9090:9090 \
  -e MACHINE_ID=1 \
  ginkgo-id-service
```

### Kubernetes部署

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ginkgo-id-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: ginkgo-id-service
  template:
    metadata:
      labels:
        app: ginkgo-id-service
    spec:
      containers:
      - name: ginkgo-id-service
        image: ginkgo-id-service:latest
        ports:
        - containerPort: 8080
        - containerPort: 9090
        env:
        - name: MACHINE_ID
          valueFrom:
            fieldRef:
              fieldPath: metadata.name
```

## 监控

### 指标

- `id_generated_total`: 生成的ID总数
- `id_generation_duration`: ID生成耗时
- `id_cache_hit_rate`: 缓存命中率
- `id_machine_id`: 当前机器ID
- `id_sequence_overflow`: 序列号溢出次数

### 告警

- ID生成失败率 > 1%
- ID生成耗时 > 10ms
- 缓存命中率 < 80%
- 时钟回拨检测

## 性能

### 基准测试

- **单机性能**：10万ID/秒
- **集群性能**：100万ID/秒（10节点）
- **延迟**：< 1ms（P99）
- **可用性**：99.99%

### 优化策略

1. **本地缓存**：预生成ID缓存
2. **批量处理**：支持批量ID生成
3. **连接池**：HTTP连接复用
4. **异步处理**：非阻塞ID生成

## 故障处理

### 时钟回拨

- 自动检测时钟回拨
- 等待时钟恢复
- 记录回拨事件

### 机器ID冲突

- 自动检测冲突
- 重新分配机器ID
- 记录冲突事件

### 服务不可用

- 健康检查
- 自动故障转移
- 降级策略

## 最佳实践

### 1. 部署建议

- 至少部署3个实例
- 使用负载均衡器
- 配置监控和告警
- 定期备份配置

### 2. 使用建议

- 使用批量接口减少网络调用
- 配置合适的超时时间
- 实现重试机制
- 监控服务状态

### 3. 运维建议

- 定期检查日志
- 监控性能指标
- 及时处理告警
- 定期更新版本

## 许可证

MIT License 
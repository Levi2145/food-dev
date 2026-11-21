# Food Dev 模拟电商项目

这是一个保留慕课网 2019 课程模块边界的练习项目：`food-dev-api` 提供商城接口，`food-dev-service` 负责业务，`food-dev-mapper` 负责 MyBatis 映射，`food-dev-payment` 是独立的支付模拟服务，`food-dev-front` 是 Vite 前端。

## 快速开始

1. 后端使用 JDK 8+、Maven 3.8+。在项目根目录执行 `mvn clean package -DskipTests`。
2. 正式运行默认使用 MySQL 8 数据库 `food`，地址 `localhost:3306`、用户 `root`、密码 `root`，端口 8088。先在 Navicat/MySQL 客户端执行根目录的 `food.sql`，再启动 `com.L.Application` 或运行打包后的 jar。
3. 前端进入 `food-dev-front` 执行 `npm install && npm run dev`，访问 Vite 输出地址。
4. 支付模拟服务启动 `food-dev-payment` 后，调用 `POST /payment/create?orderId=xxx&amount=0.01` 获取二维码字符串，再调用 `POST /payment/mock-success/xxx` 模拟支付成功。

前端页面现在是可交互的商城 SPA：`#/home` 首页轮播/分类/推荐，`#/category` 商品分页与搜索，`#/item/:id` 商品详情与评价，`#/cart` 购物车，`#/checkout` 地址和下单，`#/orders` 订单中心，`#/profile` 个人中心，`#/login` 和 `#/register` 登录注册。页面通过 `fetch` 调用 `/api/home`、`/api/items`、`/api/cart`、`/api/addresses`、`/api/orders`、`/api/comments` 等接口。

## 数据库

正式运行使用 MySQL：配置位于 `food-dev-api/src/main/resources/application.yml`。数据库名为 `food`，完整建表和演示数据脚本为根目录 [food.sql](food.sql)。

测试时可切换到 H2 内存库，不会修改 MySQL：

```powershell
java -jar food-dev-api/target/food-dev-api-1.0-SNAPSHOT.jar --spring.profiles.active=test
```

测试 profile 会自动执行 `food-dev-api/src/main/resources/schema.sql`，并随机分配 HTTP 端口。

## 说明

课程源码中有少量未完成的课堂草稿（空实体、旧 Swagger 注解和未实现的业务方法）。本次补全了依赖、H2 初始化、MyBatis-Plus 基础配置、可运行的前端展示和一分钱支付模拟链路；真实微信/支付宝商户支付仍需申请商户号、证书和回调公网地址后再替换 `PaymentController`。

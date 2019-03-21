一、环境依赖
1、jdk8
2、maven3.3+
3、redis4.0.8+
4、mysql5.6+
二、使用前重要说明：
1、本项目使用redis存储session，但是没用spring-session-redis（写法在代码中也有参考），而是采用自己设置session到redis
2、nginx用于处理跨域和负载均衡，本项目已经处理了跨域，因此可以不用nginx，此处提及的目的是为今后负载均衡做准备
http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  60; #http连接超时时间为60秒
    server {
	#监听localhost的9999端口下的所有请求，并将其转发至http://localhost:8888/
        listen       9999;
        server_name  localhost;
	charset utf-8;
	location / {
		proxy_pass       http://localhost:8888/;
                proxy_redirect   off;
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	}
    }
}
3、后端几乎所有的增删改查删除操作都是物理操作（忽略数据库表中的del_flag字段），如果想做逻辑操作（使用数据库表中的del_flag字段）需要重写相关SQL语句（目前虽然保留了逻辑删除字段，但是形同虚设）
4、本项目已不在提供前端支持，仅仅是保留了下历史前端项目，本项目将全程采用Postman验证接口
三、前后端近期开发计划：
1、各功能细节优化
2、接口测试管理
3、文件上传下载管理
4、HTTP2.0
5、websoket完善并优化
6、多数据源通过类似注解@DS在service或dao层完成
7、优化后端验证码的代码
8、后端将采用JAVA10及函数式编程（WebFlux/RxJava2）改造（此技术较新同时改动也较大）
9、将JAVA算法改用C语言实现，JAVA本地调用C语言的实现方法
10、整合logback
11、优化根据JSON格式自动生成实体类的代码

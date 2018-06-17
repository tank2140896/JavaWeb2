一、环境依赖
1、jdk8
2、maven3.3+
3、redis最新版本
4、mysql5.6+
二、使用前重要说明：
1、本项目前端样式借鉴：https://github.com/RogerDeng/SB-Admin-BS4-Angular-5
2、本项目使用redis存储session，但是没用spring-session-redis（写法在代码中也有参考），而是采用自己设置session到redis
3、nginx用于处理跨域和负载均衡，本项目已经处理了跨域，因此可以不用nginx，此处提及的目的是为今后负载均衡做准备
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
4、前端并未将单条数据的详情、新增、修改合为一个页面显示，不是说不能，只是个人喜欢将其分开来而已
5、后端几乎所有的增删改查删除操作都是物理操作（忽略数据库表中的del_flag字段），如果想做逻辑操作（使用数据库表中的del_flag字段）需要重写相关SQL语句（目前虽然保留了逻辑删除字段，但是形同虚设）
6、前端需要有一定的TypeScripe知识基础，参考：https://www.tslang.cn/index.html
7、个人临时租用的服务器（47.96.157.130）装了redis（密码：123456）和mysql（root/root）
8、前端Angular和RxJS知识点，项目部署后参考：http://localhost:4200/#/demo
三、前端后期改进：
1、更换日期插件（优先）或深入研究现在的日期插件
2、更换弹出框插件（优先）或深入研究现在的弹出框插件
3、优化或改进表格在“数据获取中”、“无数据”、“有数据”这三种状态下的界面表现形式及其相关代码，且
4、前端校验
5、前端国际化
6、加入消息提示框
7、优化角色模块分配样式
8、优化登录界面验证码样式
四、前后端近期开发计划：
1、各功能细节优化
2、接口测试管理
3、文件上传下载管理
4、HTTP2.0
5、websoket完善并优化
6、优化后端验证码的代码
7、后端将采用JAVA10及函数式编程（WebFlux/RxJava）改造部分代码



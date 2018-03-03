使用前重要说明：
1、本项目前端样式借鉴：https://rawgit.com/start-angular/SB-Admin-BS4-Angular-5/master/dist/components
2、本项目全程采用redis，因此必须保证redis的高可用性
3、session（共享）用的是redis，但是没用自动挡的spring-session-redis（request.getSession()）（自动挡写法在代码中也有参考），而是采用手动档自己设置session到redis（redisTemplate）
4、nginx用于处理跨域和负载均衡，本项目已经处理了跨域，因此可以不用nginx，此处提及的目的是为今后负载均衡做准备
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
5、前端并未将单条数据的详情、新增、修改合为一个页面显示，不是说不能，只是个人喜欢将其分开来而已
6、后端几乎所有的增删改查删除操作都是物理操作（忽略数据库表中的del_flag字段），如果想做逻辑操作（使用数据库表中的del_flag字段）需要重写相关SQL语句（目前虽然保留了逻辑删除字段，但是形同虚设）
7、前端将用到TypeScripe，参考：https://www.tslang.cn/index.html
前端后期改进：
1、更换日期插件（优先）或深入研究现在的日期插件
2、更换弹出框插件（优先）或深入研究现在的弹出框插件
3、优化或改进表格在“数据获取中”、“无数据”、“有数据”这三种状态下的界面表现形式及其相关代码，且考虑用NgSwitch改造
<div class="container" [ngSwitch]="myVar">
	<div *ngSwitchCase="'A'">Var is A</div>
	<div *ngSwitchCase="'B'">Var is B</div>
	<div *ngSwitchDefault>Var is something else</div>
</div>
4、前端校验
5、前端国际化
6、加入消息提示框
7、优化角色模块分配样式
8、优化登录界面验证码样式
前后端近期开发计划：
1、表单重复提交处理
2、后端将采用函数式编程改造部分代码
3、用户管理、角色管理、模块管理细节优化
4、更新至SpringBoot2.0+
5、WebSocket接入
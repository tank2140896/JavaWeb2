一、运行和打包                               
cnpm install     
cnpm start      
cnpm run build                    
注：如果要用npm命令，请注意修改angular.json中引用的jquery、bootstrap等相关路径                
二、npm相关配置                        
1、下载最新版的node.js（https://nodejs.org/zh-cn/），配置环境变量                     
2、命令行输入：npm config get prefix获得路径，如：C:\Users\admin\AppData\Roaming                               
3、进入根据步骤2获得的路径，删除npm和npm-cache文件夹，然后npm cache clean --force                                   
4、下载最新版本的npm：npm -g install npm（国内快点的话可以使用【npm install -g cnpm --registry=https://registry.npm.taobao.org】以后命令就由npm变为cnpm）                    
5、下载最新版的typescript：npm -g install typescript（查看typescript的版本：tsc -v）                         
6、下载最新版的Angular CLI（可跳过）：npm -g install @angular/cli，查看版本：ng --version                        
7、下载最新版的gulp（可跳过）：npm -g install gulp                           
8、下载最新版的yarn（可跳过）：npm -g install yarn                           
9、下载最新版的bower（可跳过）：npm -g install bower                                
10、下载最新版的WebStorm（可跳过）                                
11、测试（可跳过）：新建文件test.ts写一些typescript代码，然后运行tsc test.ts将其编译成test.js，然后在html中正常引入使用                                        
注：①关于npm各种包和依赖及其版本可以查看：https://www.npmjs.com/；②-g是全局安装                
12、angular入门。参考官方网站的示例：https://angular.io                
13、其它                     
A、npm命令行cmd代理设置                           
set http_proxy=http://10.131.46.21:80/                 
set https_proxy=http://10.131.46.21:80/                           
B、设置完代理重新执行npm install继续下载包时报"Cannot read property 'match' of undefined"                             
npm cache clear --force                     
删除package-lock.json                              

package com.javaweb.config.mongodb;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.util.core.MathUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
SQL术语/概念    MongoDB术语/概念    解释/说明
database	 database	                数据库
table	     collection	                数据库表/集合
row	         document	                数据记录行/文档
column	     field	                           数据字段/域
index	     index	                           索引
table joins	  无	                                     表连接,MongoDB不支持
primary key	 primary key	     主键,MongoDB自动将_id字段设置为主键
命令操作：
cd C:\Program Files\MongoDB\Server\4.2\bin
mongo
show dbs
db local
#使用数据库
use my
#给test表插入一条数据
db.test.insert({"my":"my"})
#显示所有表
show collections
#清空test表的所有数据
db.test.remove({})
#删除test表
db.test.drop
*/
@RestController
public class MongoDbController {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	@Autowired 
	private GridFSBucket gridFSBucket;
	
    @GetMapping("/mongoTest")
    public BaseResponseResult mongoTest() {
    	if(mongoTemplate==null||gridFsTemplate==null){
    		return new BaseResponseResult(500,"fail",null);
    	}else{
    		return new BaseResponseResult(200,"success",mongoTemplate.toString()+"~"+gridFsTemplate.toString()+"~"+gridFSBucket.toString());
    	}
    }
    
    @GetMapping("/mongoSelect")
    public BaseResponseResult mongoSelect() {
    	List<UserForMongoDb> list = mongoTemplate.findAll(UserForMongoDb.class);
    	for(int i=0;i<list.size();i++){
    		System.out.println(list.get(i).getMongoDbId()+"~"+list.get(i).getId());
    	}
		return new BaseResponseResult(200,"success",list.size());
    }
    
    @GetMapping("/mongoAdd")
    public BaseResponseResult mongoAdd() {
    	UserForMongoDb userForMongoDb = new UserForMongoDb();
    	userForMongoDb.setId(UUID.randomUUID().toString());
    	userForMongoDb.setUsername("username_"+MathUtil.getRandomNumForLCRC(100));
    	userForMongoDb.setPassword("password_"+MathUtil.getRandomNumForLCRC(100));
    	userForMongoDb.setAge(MathUtil.getRandomNumForLCRC(100));
	    userForMongoDb.setCurrentAddress("address_"+MathUtil.getRandomNumForLCRC(100));	
	    UserForMongoDb returnUserForMongoDb = mongoTemplate.save(userForMongoDb);
		return new BaseResponseResult(200,"success",returnUserForMongoDb.getMongoDbId());
    }
    
    @GetMapping("/mongoUpdate")
    public BaseResponseResult mongoUpdate() {
    	Query query = new Query(Criteria.where("id").is("79504fa8-02af-4e00-8a2c-6a2a9dafe1f9"));
    	Update update = new Update();
    	update.set("username","我被修改了");
	    UpdateResult updateResult = mongoTemplate.updateFirst(query,update,UserForMongoDb.class);
		return new BaseResponseResult(200,"success",updateResult);
    }
    
    @GetMapping("/mongoDelete")
    public BaseResponseResult mongoDelete() {
    	UserForMongoDb userForMongoDb = new UserForMongoDb();
    	userForMongoDb.setMongoDbId("5eccb549fd6eb44c1ad0d0d2");
    	DeleteResult deleteResult = mongoTemplate.remove(userForMongoDb);
		return new BaseResponseResult(200,"success",deleteResult);
    }
	
}

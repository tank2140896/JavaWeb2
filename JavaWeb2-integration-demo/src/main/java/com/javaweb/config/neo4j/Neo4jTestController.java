package com.javaweb.config.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
删除所有命令：
MATCH (n)
OPTIONAL MATCH (n)-[r]-()
DELETE n,r
*/
@RestController
public class Neo4jTestController {
	
	@Autowired
	private PersonNodeRepository pnr;

	@Autowired
	private PersonRelationNodeRepository prnr;
	
	@GetMapping("/test")
	public String test(){
		try {
	        PersonNodeEntity pne1 = new PersonNodeEntity();
	        pne1.setId("a");
	        pne1.setName("张三");
	        PersonNodeEntity pne2 = new PersonNodeEntity();
	        pne2.setId("b");
	        pne2.setName("李四");
	        pnr.save(pne1);
	        pnr.save(pne2);
	        PersonRelationNodeEntity prne = new PersonRelationNodeEntity();
	        prne.setId("c");
	        prne.setPne1(pne1);    
	        prne.setPne2(pne2);
	        prne.setRelationName("兄弟");
	        prnr.save(prne);
	    }catch (Exception e) {
	        //do nothing
	    }
		return "success";
	}

}

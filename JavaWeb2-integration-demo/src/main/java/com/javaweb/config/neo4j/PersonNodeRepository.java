package com.javaweb.config.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonNodeRepository extends Neo4jRepository<PersonNodeEntity,String> {

}

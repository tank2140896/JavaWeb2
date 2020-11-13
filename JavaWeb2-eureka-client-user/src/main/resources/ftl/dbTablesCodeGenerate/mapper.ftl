<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.javaweb.web.dao.ds1.${className}Dao">

	<!--
	select <#list propertyList as pl>${pl.tableColumn} as ${pl.javaPropertyName}<#if (pl?size>=pl_index)>,</#if></#list> from ${tableName}
	<#noparse>${currentPage}*${pageSize}</#noparse>
	<#noparse>(${currentPage}-1)*${pageSize}</#noparse>
	-->

</mapper>

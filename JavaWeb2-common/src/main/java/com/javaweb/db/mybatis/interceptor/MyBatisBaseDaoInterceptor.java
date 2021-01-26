package com.javaweb.db.mybatis.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.constant.CommonConstant;
import com.javaweb.db.help.BoundSqlSource;
import com.javaweb.db.help.SqlBuildInfo;
import com.javaweb.db.help.SqlHandle;
import com.javaweb.db.mybatis.api.handle.HandleDelete;
import com.javaweb.db.mybatis.api.handle.HandleInsert;
import com.javaweb.db.mybatis.api.handle.HandleSelectAll;
import com.javaweb.db.mybatis.api.handle.HandleSelectAllCount;
import com.javaweb.db.mybatis.api.handle.HandleSelectByPk;
import com.javaweb.db.mybatis.api.handle.HandleSelectList;
import com.javaweb.db.mybatis.api.handle.HandleSelectListCount;
import com.javaweb.db.mybatis.api.handle.HandleUpdate;
import com.javaweb.db.query.QueryWapper;
import com.javaweb.enums.DbTypeEnum;
import com.javaweb.util.core.DateUtil;

/**
 * Executor           作用是执行SQL语句（所有的sql），并且对事务、缓存等提供统一接口（在这一层上做拦截的权限会更大）
 * StatementHandler   作用是对 statement 进行预处理，并且提供统一的原子的增、删、改、查接口（如果要在SQL执行前进行拦截的话，拦截这里就可以了）
 * ResultSetHandler   作用是对返回结果ResultSet进行处理
 * ParameterHandler   作用是对参数进行赋值
 */
@Component
@Intercepts({
	@Signature(type=Executor.class,method="update",args={MappedStatement.class,Object.class}),
	@Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
})
public class MyBatisBaseDaoInterceptor implements Interceptor {
	
	private static final Map<String,SqlHandle> map = new HashMap<>();
	static{
		map.put("insert",new HandleInsert());
		map.put("update",new HandleUpdate());
		map.put("delete",new HandleDelete());
		map.put("selectAll",new HandleSelectAll());
		map.put("selectAllCount",new HandleSelectAllCount());
		map.put("selectByPk",new HandleSelectByPk());
		map.put("selectList",new HandleSelectList());
		map.put("selectListCount",new HandleSelectListCount());
	}
	
	public Object intercept(Invocation invocation) throws Throwable {
		//这里无论是update方法还是query方法，只对前两个参数进行处理
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		String id = mappedStatement.getId();
		Class<?> c = getGenericParadigm(id);//获得泛型类型
		String split[] = id.split("\\.");
		String lastString = split[split.length-1];
		SqlHandle sqlHandle = map.get(lastString);
		if(sqlHandle!=null){
			SqlBuildInfo sqlBuildInfo = getSqlBuildInfo(c,parameter);//获取改造SQL所需要的数据
			DataSource dataSource = mappedStatement.getConfiguration().getEnvironment().getDataSource();
			Connection connection = dataSource.getConnection();
			String driverName = connection.getMetaData().getDriverName();
			if(driverName.toLowerCase().contains("mysql")){
				sqlBuildInfo.setDbTypeEnum(DbTypeEnum.MYSQL);
			}else if(driverName.toLowerCase().contains("oracle")){
				sqlBuildInfo.setDbTypeEnum(DbTypeEnum.ORACLE);
			}
			String sql = sqlHandle.handle(sqlBuildInfo);//改造SQL
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),sql,boundSql.getParameterMappings(),boundSql.getParameterObject());  
			Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(),mappedStatement.getId(),new BoundSqlSource(newBoundSql),mappedStatement.getSqlCommandType());
			builder = getBuilder(builder, mappedStatement);
			MappedStatement newMappedStatement = builder.build();
			invocation.getArgs()[0] = newMappedStatement;
			connection.close();
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target,this);
	}

	public void setProperties(Properties properties) {

	}
	
	private Class<?> getGenericParadigm(String id) throws Exception {
		Class<?> c = Class.forName(id.substring(0,id.lastIndexOf(".")));//获得子类，目前只处理单层继承
		Type type[] = c.getGenericInterfaces(); 
		for(int i=0;i<type.length;i++){
			ParameterizedType parameterizedType = (ParameterizedType)type[i];//type[i] instanceof ParameterizedType
			Type newType[] = parameterizedType.getActualTypeArguments();
			for(int j=0;j<newType.length;j++){
				c = Class.forName(newType[j].getTypeName());//获得泛型类型，目前只处理单个泛型参数
			}
		}
		return c;
	}
	
	private Builder getBuilder(Builder builder,MappedStatement mappedStatement){
		builder.resource(mappedStatement.getResource());
		builder.fetchSize(mappedStatement.getFetchSize());
		builder.statementType(mappedStatement.getStatementType());
		builder.keyGenerator(mappedStatement.getKeyGenerator());
		// builder.keyProperty(mappedStatement.getKeyProperties());
		builder.timeout(mappedStatement.getTimeout());
		builder.parameterMap(mappedStatement.getParameterMap());
		builder.resultMaps(mappedStatement.getResultMaps());
		builder.cache(mappedStatement.getCache());
		return builder;
	}
	
	private SqlBuildInfo getSqlBuildInfo(Class<?> c,Object parameter) throws Exception {
		List<String> entityList = new ArrayList<>();//获取实体类属性名称的集合
		List<Object> entityValueList = new ArrayList<>();//获取实体类属性值的集合
		List<String> columnList = new ArrayList<>();//获取数据库字段名称的集合
		List<Boolean> canUpdateSetEmptyList = new ArrayList<>();
		boolean useEntityValueList = true;
		if((parameter==null)||(parameter instanceof String)||(parameter instanceof Number)||(parameter instanceof Map)||(parameter instanceof QueryWapper)){
			useEntityValueList = false;
		}
		Table tabel = c.getAnnotation(Table.class);
		String tableName = tabel.name();//获取持久化对象所对应的表名
		String pk = CommonConstant.EMPTY_VALUE;
		boolean pkGenerate = false;
		while(c!=null){
			Field[] field = c.getDeclaredFields();
			for(int i=0;i<field.length;i++){
				Field eachField = field[i];
				eachField.setAccessible(true);
				Column column = eachField.getDeclaredAnnotation(Column.class);
				if(column==null){
					continue;
				}
				if(column.pk()){
					pk = column.name();//获取主键，暂时只支持单主键
				}
				if(column.keyGenerate()){
					pkGenerate = true;//获取主键是否自增，暂时只支持单主键
				}
				String fieldName = eachField.getName();
				entityList.add(fieldName);
				columnList.add(column.name());//数据库字段名称
				if(column.canUpdateSetEmpty()){
					canUpdateSetEmptyList.add(true);
				}else{
					canUpdateSetEmptyList.add(false);
				}
				if(useEntityValueList){
					fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());
					Method method = c.getMethod("get"+fieldName);
					Object value = method.invoke(parameter);
					//目前只考虑到String、Integer、Long和Date类型
					if(value instanceof Date){
						entityValueList.add(DateUtil.dateToLocalDateTime((Date)value).format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATETIME_PATTERN)));
					}else{
						entityValueList.add(value);
					}
				}
			}
			c = c.getSuperclass();
		}
		SqlBuildInfo sqlBuildInfo = new SqlBuildInfo();
		sqlBuildInfo.setTableName(tableName);
		sqlBuildInfo.setEntityList(entityList);
		sqlBuildInfo.setEntityValueList(entityValueList);
		sqlBuildInfo.setParameterValue(parameter);
		sqlBuildInfo.setColumnList(columnList);
		sqlBuildInfo.setPk(pk);
		sqlBuildInfo.setPkGenerate(pkGenerate);
		sqlBuildInfo.setCanUpdateSetEmptyList(canUpdateSetEmptyList);
		return sqlBuildInfo;
	}
	
}

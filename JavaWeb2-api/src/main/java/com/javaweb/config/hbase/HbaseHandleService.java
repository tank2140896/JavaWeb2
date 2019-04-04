package com.javaweb.config.hbase;

/**
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseHandleService {
	
	private Connection connection;
	
	public HbaseHandleService(Configuration configuration) {
		try {
			this.connection = ConnectionFactory.createConnection(configuration);
		} catch (IOException e) {
			//do nothing
		}
	}
	
	//创建表
	public boolean creatTable(String tableName,List<String> columnFamily) {
		Admin admin = null;
	    try {
	    	admin = connection.getAdmin();
	    	if(admin.tableExists(TableName.valueOf(tableName))) {
	    		return true;
	    	}
	        List<ColumnFamilyDescriptor> familyDescriptors = new ArrayList<>(columnFamily.size());
	        columnFamily.forEach(cf->{
	        	familyDescriptors.add(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build());
	        });
	        TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName)).setColumnFamilies(familyDescriptors).build();
        	admin.createTable(tableDescriptor);
        	return true;
	    }catch(IOException e){
	    	return false;
        }finally {
        	close(admin,null);
        }
	}
	
    //预分区创建表
    public boolean createTableBySplitKeys(String tableName,List<String> columnFamily,byte[][] splitKeys) {
        Admin admin = null;
        try{
            admin = connection.getAdmin();
            if(admin.tableExists(TableName.valueOf(tableName))){
                return true;
            }else{
                List<ColumnFamilyDescriptor> familyDescriptors = new ArrayList<>(columnFamily.size());
                columnFamily.forEach(cf -> {
                    familyDescriptors.add(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build());
                });
                TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName)).setColumnFamilies(familyDescriptors).build();
                admin.createTable(tableDescriptor,splitKeys);
                return true;
            }
        }catch(IOException e){
            return false;
        }finally{
        	close(admin,null);
        }
    }
    
    //获取表名
    public Table getTable(String tableName) {
    	try {
    		return connection.getTable(TableName.valueOf(tableName));
    	}catch (Exception e) {
    		return null;
		}
    }
    
    //查询库中所有表的表名
    public List<String> getAllTableNames(){
        List<String> result = new ArrayList<>();
        Admin admin = null;
        try{
            admin = connection.getAdmin();
            TableName[] tableNames = admin.listTableNames();
            for(TableName tableName:tableNames){
                result.add(tableName.getNameAsString());
            }
        }catch(IOException e){
            //do nothing
        }finally{
            close(admin,null);
        }
        return result;
    }
    
	//删除表
	public void deleteTable(String tableName){
		Admin admin = null;
		try {
			admin = connection.getAdmin();
			if(admin.tableExists(TableName.valueOf(tableName))){
			   admin.disableTable(TableName.valueOf(tableName));
			   admin.deleteTable(TableName.valueOf(tableName));
			}
		}catch(IOException e){
            //do nothing
        }finally{
            close(admin,null);
        }
	}
	
	//删除单行数据
	public void deleteData(String tablename,String row) {
		Table table = getTable(tablename);
		if (table != null) {
			try{
				Delete delete = new Delete(row.getBytes());
				table.delete(delete);
			}catch(IOException e){
	            //do nothing
	        }finally{
	            close(null,table);
	        }
		}
	}
	
	//删除多行数据
	public void deleteDatas(String tablename,String[] rows) {
		Table table = getTable(tablename);
		if (table != null) {
			try{
				List<Delete> list = new ArrayList<>();
				for(String row:rows){
					Delete delete = new Delete(row.getBytes());
					list.add(delete);
				}
				if(list.size()>0){
					table.delete(list);
				}
			}catch(IOException e){
	            //do nothing
	        }finally{
	            close(null,table);
	        }
		}
	}
	
	//获取单条数据
	public Result getRow(String tablename,byte[] row) {
		Table table = getTable(tablename);
		Result result = null;
		if(table!=null){
			try{
				Get get = new Get(row);
				result = table.get(get);
			}catch(IOException e){
				//do nothing
			}finally{
				close(null,table);
			}
		}
		return result;
	}
	
	//获取多条数据
	public Result[] getRows(String tablename,List<byte[]> rows) {
		Table table = getTable(tablename);
		List<Get> gets = new ArrayList<>();
		Result[] results = null;
		try {
			if(table!=null){
				for(byte[] row:rows){
					if(row!=null){
						gets.add(new Get(row));
					}
				}
			}
			if(gets.size()>0){
				results = table.get(gets);
			}
		}catch(IOException e){
			//do nothing
		}finally{
			close(null,table);
		}
		return results;
	}
	
	//插入数据
	public void insertData(String tableName,List<Put> puts) {
		Table table = getTable(tableName);
		try {
			table.put(puts);
		}catch(IOException e){
			//do nothing
		}finally{
			close(null,table);
		}
	}
    
    //关闭连接
    public void close(Admin admin,Table table) {
    	if(admin!=null) {
    		try{
				admin.close();
			}catch(IOException e){
				//do nothing
			}
    	}
    	if(table!=null) {
    		try{
    			table.close();
			}catch(IOException e){
				//do nothing
			}
    	}
    }
	
}
*/
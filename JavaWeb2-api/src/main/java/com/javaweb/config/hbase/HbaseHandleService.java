package com.javaweb.config.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseHandleService {
	
	private Connection connection;
	
	public HbaseHandleService(Configuration configuration) {
		/** 使用请解注
		try {
			this.connection = ConnectionFactory.createConnection(configuration);
		} catch (IOException e) {
			//do nothing
		}
		*/
	}
	
	public boolean creatTable(String tableName,List<String> columnFamily) {
		Admin admin = null;
	    try {
	    	admin = connection.getAdmin();
	    	if(admin.tableExists(TableName.valueOf(tableName))) {
	    		return true;
	    	}
	        List<ColumnFamilyDescriptor> familyDescriptors = new ArrayList<>(columnFamily.size());
	        columnFamily.forEach(cf -> {
	        	familyDescriptors.add(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build());
	        });
	        TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName)).setColumnFamilies(familyDescriptors).build();
        	admin.createTable(tableDescriptor);
        	return true;
	    }catch(IOException e){
	    	return false;
        }finally {
            if(admin!=null) {
            	try {
					admin.close();
				} catch (IOException e) {
					//do nothing
				}
            }
        }
	}
	
}

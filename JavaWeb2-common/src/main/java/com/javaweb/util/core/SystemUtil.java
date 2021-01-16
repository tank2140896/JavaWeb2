package com.javaweb.util.core;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class SystemUtil {
    
	//判断是不是linux系统
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
    
    //获取mac地址
    public static List<String> getMacList() throws Exception {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tmpMacList=new ArrayList<>();
        while(en.hasMoreElements()){
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for(InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if(network==null){continue;}
                byte[] mac = network.getHardwareAddress();
                if(mac==null){continue;}
                sb.delete( 0, sb.length() );
                for (int i = 0; i < mac.length; i++) {
                	sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                tmpMacList.add(sb.toString());
            }        
        }
        if(tmpMacList.size()<=0){
        	return tmpMacList;
        }
        List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
        return unique;
    }
    
}

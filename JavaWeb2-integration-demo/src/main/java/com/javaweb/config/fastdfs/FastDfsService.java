package com.javaweb.config.fastdfs;

import java.security.MessageDigest;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

//使用参考：https://blog.51cto.com/14439672/2434896
//带token参考：https://www.cnblogs.com/zeussbook/p/10764086.html
@Component
public class FastDfsService {
    
    //private static final Logger LOGGER = LoggerFactory.getLogger(FastDfsUtil.class);
    
    @Autowired
    private FastFileStorageClient storageClient ;
    
    /**
	@Autowired
	private DefaultTrackerClient defaultTrackerClient;
	List<GroupState> groupStates = defaultTrackerClient.listGroups();
	List<StorageState> storageStates = defaultTrackerClient.listStorages(groupName);
    */
    
    /**
     * 上传文件
     * @param file 文件对象
     * @param mataData 设置文件信息（可为null）<br/>形如：Set<MetaData> mataData = new HashSet<>();<br/>mataData.add(new MetaData("author","fastdfs"));
     * @return 文件全路径
     * @throws Exception 异常
     */
    public String upload(MultipartFile file,Set<MetaData> mataData) throws Exception{
    	//缩略图：uploadImageAndCrtThumbImage
    	StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(),FilenameUtils.getExtension(file.getOriginalFilename()),mataData);
        return storePath.getFullPath();
    }
    
    /**
     * 删除文件
     * @param fileUrl 文件路径
     * @throws Exception 异常
     */
    public void deleteFile(String fileUrl) throws Exception {
    	StorePath storePath = StorePath.parseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(),storePath.getPath());
    }
    
    //参考：https://blog.csdn.net/SIMBA1949/article/details/101761771
    public FileInfo queryFileInfo(String groupName,String remoteFileName) throws Exception {
    	return storageClient.queryFileInfo(groupName, remoteFileName);
    }
	
    //
    /**
     * 计算token值（不能加上group1！！！getToken("M00/00/00/CoMALF_RsLeAP-fgAACwsyaLgL0777.jpg",(int)(System.currentTimeMillis()/1000),"FastDFS1234567890")）
     * @param remoteFilePath 目标文件路径
     * @param ts 时间
     * @param secretKey 密钥
     * @return token
     * @throws Exception 异常
     */
    public String getToken(String remoteFilePath, int ts,String secretKey) throws Exception {
    	byte[] bsFilePath = remoteFilePath.getBytes("UTF-8");
        byte[] bsKey = secretKey.getBytes("UTF-8");
        byte[] bsTimestamp = (new Long(ts)).toString().getBytes("UTF-8");
        byte[] buff = new byte[bsFilePath.length + bsKey.length + bsTimestamp.length];
        System.arraycopy(bsFilePath, 0, buff, 0, bsFilePath.length);
        System.arraycopy(bsKey, 0, buff, bsFilePath.length, bsKey.length);
        System.arraycopy(bsTimestamp, 0, buff, bsFilePath.length + bsKey.length, bsTimestamp.length);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hash = messageDigest.digest(buff);
        return Hex.encodeHexString(hash);
    }
    
}

package com.javaweb.config.fastdfs;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

//使用参考：https://blog.51cto.com/14439672/2434896
//带token参考：https://www.cnblogs.com/zeussbook/p/10764086.html
@Component
public class FastDfsService {
    
	//private static final Logger LOGGER = LoggerFactory.getLogger(FastDfsUtil.class);
    
    @Resource
    private FastFileStorageClient storageClient ;
    
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
    
}

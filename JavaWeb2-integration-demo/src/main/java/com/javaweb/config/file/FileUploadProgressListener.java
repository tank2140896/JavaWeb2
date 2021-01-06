package com.javaweb.config.file;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

@Component
public class FileUploadProgressListener implements ProgressListener {

	private HttpSession session;
	
    public void setSession(HttpSession session){
        this.session = session;
        session.setAttribute("upload_percent",0);
    }
    
    public void update(long bytesRead/*已读取文件的比特数*/,long contentLength/*文件总比特数*/,int items/*正读的第几个文件*/) {
    	int percent = (int)(bytesRead*100.0/contentLength);
        session.setAttribute("upload_percent",percent);
    }

}
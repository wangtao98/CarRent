package com.iwiller.sys.task;

import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.utils.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@EnableScheduling
public class RecycleTempFileTask {
    /**
     * 每天12点删除图片
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void recycleTempFile(){
        File file = new File(AppFileUtils.PATH);
        deleteFile(file);
    }

    /**
     * 删除图片
     * @param file
     */
    private void deleteFile(File file) {
        if(null!=file){
            File[] listFiles = file.listFiles();
            if(null!=listFiles&&listFiles.length>0){
                for (File f :
                        listFiles) {
                    if (f.isFile()) {
                        if (f.getName().endsWith(SysConstants.FILE_UPLOAD_TEMP)){
                            f.delete();
                        }
                    }else{
                        //如果是文件夹，在递归删除
                        deleteFile(f);
                    }
                }
            }

        }
    }

}

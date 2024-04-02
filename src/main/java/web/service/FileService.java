package web.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    HttpServletResponse response;

    String uploadPath = "C:\\Users\\504\\Desktop\\webSpringReact\\build\\resources\\main\\static\\uploadimg\\";

    // 1. 업로드
    public String fileUpload(MultipartFile multipartFile){
        // 파일 이름 조합하기 : 새로운 식별이름과 실제 파일이름
        String uuid = UUID.randomUUID().toString();
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");

        File file = new File(uploadPath+filename);

        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            System.out.println("e = " + e);
            return null;
        }
        return filename;
    }
}

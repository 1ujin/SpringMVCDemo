package com.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class Html5FileUploadController {
    @RequestMapping(value = "/upload")
    public String inputProduct() {
        return "html5FileUpload";
    }

    @RequestMapping(value = "/file_upload")
    public void saveFile(HttpServletRequest request, @ModelAttribute UploadedFile uploadedFile) throws IOException {
        MultipartFile multipartFile = uploadedFile.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            File file = new File(request.getServletContext().getRealPath("/"), fileName);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

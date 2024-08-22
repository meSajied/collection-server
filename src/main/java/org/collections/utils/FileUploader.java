package org.collections.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploader {
  public String uploadFile(MultipartFile file, String fileName) {
    if (file.isEmpty()) {
      return "Please select a file to upload";
    }

    String UPLOAD_DIR = getUploadDir();
    File directory = new File(UPLOAD_DIR);

    if (!directory.exists()) {
      directory.mkdirs();
    }

    try {
      File saveFile = new File(UPLOAD_DIR + fileName + ".png");
      file.transferTo(saveFile);

      return "File uploaded successfully: " + fileName;
    } catch (IOException e) {
      return "Could not upload file: " + fileName;
    }
  }

  private String getUploadDir() {
    return new File(System.getProperty("user.dir"), "src/main/resources/static/uploads/").getAbsolutePath();
  }
}

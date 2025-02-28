package com.AnnualProject.February.service;


import com.AnnualProject.February.Exception.FileStorageLocationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {
   public final Path fileStorageLocation= Paths.get("/uploads").toAbsolutePath().normalize();

   public FileStorageService(){
       try{
           Files.createDirectories(this.fileStorageLocation);
       }
       catch (Exception ex){
           throw new FileStorageLocationException("directory unable to create",ex);
       }
   }


    public String storeFile(MultipartFile  file){
        String filename= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try{
            if(filename.contains("..")){
                throw new FileStorageLocationException("filename is invalid" + filename);
            }
            Path targetLocation=this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception ex){
            throw new FileStorageLocationException("file not stored",ex);
        }

    }



    public void deleteFile(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Files.deleteIfExists(filePath);
        } catch (Exception ex) {
            throw new FileStorageLocationException("Failed to delete file " + filename, ex);
        }
    }

}

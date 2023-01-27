package com.example.quanlysieuthi.service.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ReadImageServiceImpl implements ReadImageService {

    private final Path rootDir = Paths.get("src/main/resources/static/image");
    @Override
    public byte[] readFile(String fileName){
        if(!Files.exists(rootDir)){
            throw new RuntimeException("Không thể đọc file : " + fileName);
        }

        try {
            Path file = rootDir.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("Không thể đọc file : " + fileName);
            }
        } catch (Exception exception){
            throw new RuntimeException("Không thể đọc file : " + fileName);
        }
    }
}

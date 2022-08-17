package ru.devteam.resume.core.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import ru.devteam.resume.core.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private Path path;

    public void init(String ipath) {
        this.path = Paths.get(ipath);
        try {
            if (!Files.exists(path)) { Files.createDirectories(path); }
        } catch (IOException e) {
            throw new ResourceNotFoundException("Невозможно инициализировать папку хранилища!");
        }
    }

    public void clear() {
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    public Resource load(String filename) {
        Path file = path.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new ResourceNotFoundException("Файл недоступен!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error:"+e.getMessage());
        }
    }

}

package com.example.finalEclips.eclips.helper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.common.dto.FileDto;
import com.example.finalEclips.eclips.config.FileConfiguration;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileHelper {
    private final FileConfiguration fileConfiguration;

    public List<FileDto> uploadFiles(final List<MultipartFile> files) {
        List<FileDto> fileDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            fileDtos.add(uploadFile(file));
        }
        return fileDtos;
    }

    public FileDto uploadFile(final MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        String originFilename = file.getOriginalFilename();
        String storedFilename = makeStoredFilename(originFilename);
        String uploadPath = makeDir(fileConfiguration.getDirPath()) + File.separator + storedFilename;
        File uploadFile = new File(uploadPath);
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileDto.builder().originFilename(originFilename).storedFilename(storedFilename)
                .contentType(file.getContentType()).size(file.getSize()).build();
    }

    private String makeStoredFilename(String originFilename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(originFilename);
        return uuid + "." + extension;
    }

    public String makeDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

    public Resource getFileResource(String filename) throws MalformedURLException {
        Path filePath = Paths.get(String.format("%s/%s", fileConfiguration.getDirPath(), filename)).toAbsolutePath()
                .normalize();
        return new UrlResource(filePath.toUri());
    }

}

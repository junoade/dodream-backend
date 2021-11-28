package com.metabus.dodream.service;

import com.metabus.dodream.domain.file.File;
import com.metabus.dodream.dto.FileDto;
import com.metabus.dodream.repository.FileRepository;
import com.metabus.dodream.utils.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    /**
     * 저장하는 File의 경로를 NFT 스타일로 저장하기
     *
     *
     * @return status
     * <p>
     * 1: success
     * 0: fail
     */
    public static String fileToBase64(String path) throws IOException {
        java.io.File f = new java.io.File(path);
        if (f.isFile()) {
            byte[] bt = new byte[(int) f.length()];
            try (FileInputStream fis = new FileInputStream(f)) {
                fis.read(bt);
                return new String(Base64.encodeBase64(bt));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    @Transactional
    public int saveNFTStyle(FileDto fileDto) throws NoSuchAlgorithmException {
        int status = 0;
        try {
            log.info(fileDto.getFilePath());
            fileDto.setFileId(Hashing.hashingSHA256(fileDto.getFilePath()));
            log.info(fileDto.getFileId());
            fileRepository.save(fileDto.toEntity());
            status = 1;
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return status;
        }

    }

    @Transactional
    public String[] getAllFilesLink() {
        List<File> files = fileRepository.getAllFiles();
        log.info(String.valueOf(files.size()));
        String[] pathlist = new String[files.size()];
        int i = 0;
        for (File f : files) {
            pathlist[i] = f.getFilePath() + "\\" + f.getOriginFileName();
            log.info(pathlist[i]);
            i++;
        }

        return pathlist;
    }

}

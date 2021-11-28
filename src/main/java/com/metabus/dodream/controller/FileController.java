package com.metabus.dodream.controller;

import com.google.gson.JsonObject;
import com.metabus.dodream.config.path.PathDeterminant;
import com.metabus.dodream.dto.FileDto;
import com.metabus.dodream.repository.MemberRepository;
import com.metabus.dodream.service.FileService;
import com.metabus.dodream.service.MemberService;
import com.metabus.dodream.service.WalletService;
import com.metabus.dodream.utils.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dodream")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @Autowired
    MemberService memberService;

    @Autowired
    WalletService walletService;

    @Autowired
    FileService fileService;

    PathDeterminant pathDeterminant = new PathDeterminant();
    private final String DIRECTORY = pathDeterminant.getOS_TYPE();

    @PostMapping("/upload")
    public ResponseEntity<String> uploadNormal(@RequestParam("file") MultipartFile file, @RequestParam("wallet") String wallet,
                                               @RequestParam("name") String name, @RequestParam("id") String id) throws IOException {

        log.info("id" + id);
        if (memberService.isValidMember(id)) {
            String fileName = file.getOriginalFilename();
            File saveFile = new File(DIRECTORY + fileName);
            if (!saveFile.exists()) {
                saveFile.getParentFile().mkdirs();
            }
            JsonObject param = new JsonObject();
            HttpHeaders response_header = new HttpHeaders();
            response_header.set("content-type", "application/json");
            if (!file.getOriginalFilename().isEmpty()) {
                file.transferTo(saveFile);
                param.addProperty("result", true);
                param.addProperty("msg", "File uploaded successfully.");
                param.addProperty("fileName", fileName);
                param.addProperty("wallet", wallet);
                param.addProperty("name", name);
                param.addProperty("id", id);


            } else {
                param.addProperty("msg", "Please select a valid mediaFile..");
            }
            return new ResponseEntity<>(param.toString(), response_header, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/NftUpload")
    public ResponseEntity<String> uploadNFT(@RequestParam("file") MultipartFile file, @RequestParam("wallet") String wallet, @RequestParam("id") String id) throws IOException, NoSuchAlgorithmException {
        /* 응답폼 준비 */
        JsonObject param = new JsonObject();
        HttpHeaders response_header = new HttpHeaders();
        response_header.set("content-type", "application/json;charset=utf-8");


        if (walletService.isValidWallet(id, wallet)) {
            String fileName = file.getOriginalFilename();
            File saveFile = new File(DIRECTORY + fileName);
            if (!saveFile.exists()) {
                saveFile.getParentFile().mkdirs();
            }

            /* 로직 처리*/
            if (!file.getOriginalFilename().isEmpty()) {
                file.transferTo(saveFile);
                FileDto fileDto = new FileDto();
                fileDto.setFileId(saveFile.toString());
                fileDto.setWallet(wallet);
                fileDto.setOriginFileName(fileName);
                fileDto.setFilePath(saveFile.getParent());
                fileDto.setPrice(Hashing.randomPrice());
                fileDto.setOnPublic(true);

                fileService.saveNFTStyle(fileDto);

                param.addProperty("result", true);
                param.addProperty("msg", "File uploaded successfully.");
                param.addProperty("fileName", fileName);
                param.addProperty("wallet", wallet);
                param.addProperty("fileDto", fileDto.toString());
            } else {
                param.addProperty("msg", "Please select a valid mediaFile..");
            }
            return new ResponseEntity<>(param.toString(), response_header, HttpStatus.OK);
        } else {
            param.addProperty("result", false);
            param.addProperty("msg", "File upload fail");
            return new ResponseEntity<>(param.toString(), response_header, HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @GetMapping("/display")
    public ResponseEntity<Resource> display(@RequestParam(value = "filename") String filename) throws IOException {
        /*String path = "C:\\TestQR\\qrImg\\";*/
        Resource resource = new FileSystemResource(DIRECTORY + filename);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath = Paths.get(DIRECTORY + filename);
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resource, header, HttpStatus.OK);

    }


    @GetMapping("/displayAll")
    public ResponseEntity<String> displayAll() throws IOException {
        String[] filePaths = fileService.getAllFilesLink();
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");

        JsonObject list = new JsonObject();
        JsonObject param = new JsonObject();

        for (int i = 0; i < filePaths.length; i++) {
            param.addProperty(String.valueOf(i), FileService.fileToBase64(filePaths[i]));
        }
        return new ResponseEntity<>(param.toString(), header, HttpStatus.OK);

    }


}

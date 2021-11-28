package com.metabus.dodream.controller;

import com.google.gson.JsonObject;
import com.metabus.dodream.config.path.PathDeterminant;
import com.metabus.dodream.repository.MemberRepository;
import com.metabus.dodream.service.MemberService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dodream")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @Autowired
    MemberService memberService;


    PathDeterminant pathDeterminant = new PathDeterminant();
    private final String DIRECTORY = pathDeterminant.getOS_TYPE();

    @PostMapping("/upload")
    public ResponseEntity<String> test(@RequestParam("file") MultipartFile file, @RequestParam("wallet") String wallet,
                                       @RequestParam("name") String name, @RequestParam("id") String id) throws IOException {

        log.info("id"+id);
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
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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


}

package com.metabus.dodream.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/dodream")
@RequiredArgsConstructor
@Slf4j
public class NFT_Controller {

    /*@PostMapping("/auth")
    public ResponseEntity<String> proceedAuth() {

    }*/

    @PostMapping("/convertToNFT")
    public ResponseEntity<String> convertToNFT(@RequestParam("file") MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject result = new JsonObject();

        result.addProperty("nft-token-value", originalFileName);
        log.info("요청 처리");
        return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK);
    }


}

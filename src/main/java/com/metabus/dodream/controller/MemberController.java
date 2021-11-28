package com.metabus.dodream.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.metabus.dodream.domain.account.Member;
import com.metabus.dodream.dto.account.NH_DATA_Dto;
import com.metabus.dodream.service.HttpRequestService;
import com.metabus.dodream.service.MemberService;
import com.metabus.dodream.service.NH_Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    MemberService memberService;

    @Autowired
    NH_Service nh_service;

    static final String BASE_URL = "https://developers.nonghyup.com";

   /* @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> param) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject result = new JsonObject();
        log.info("클라이언트로 부터 AUTH QR 인증 요청 받음");
        log.info(param.get("id"));
        log.info(param.get("pwd"));

        result.addProperty("id", param.get("id"));
        result.addProperty("pwd", param.get("pwd"));
        JsonObject dataForHeader = new JsonObject();
        dataForHeader.addProperty("ApiNm", "InquireDepositorAccountNumber");
        dataForHeader.addProperty("Tsymd","20211128");
        dataForHeader.addProperty("Trtm", "112428");
        dataForHeader.addProperty("Iscd", "001237");
        dataForHeader.addProperty("FintechApsno", "001");
        dataForHeader.addProperty("ApiSvcCd", "DrawingTransferA");
        dataForHeader.addProperty("IsTuno","000012312346");
        dataForHeader.addProperty("AccessToken","dc29640ad73b5918d9910839f305b445d2efd163947def10be487b0ec1aa946e");
        JsonObject request = new JsonObject();
        request.add("Header", dataForHeader);
        request.addProperty("Bncd", "011");
        request.addProperty("Acno", "3020000005420");

        String temp = "/InquireDepositorAccountNumber.nh";

        String response = httpRequestService.postRequest(BASE_URL+temp, gson.toJson(request));


        return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
    }*/

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> param) throws IOException {
        log.info("클라이언트로 부터 AUTH 인증 요청 받음");
        log.info(param.get("id"));
        log.info("통과 "+ param.get("pwd"));
        Map<String, String> map = memberService.isValidMember(param.get("id"), param.get("pwd"));
        if(map.get("result").equals("true")){
            NH_DATA_Dto dto = nh_service.getData(param.get("id"));
            JsonObject response = new JsonObject();
            response.addProperty("result", true);
            response.addProperty("accesstoken", dto.getAccessToken());
            response.addProperty("name", map.get("username"));

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }else{
            log.info("못찾음");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* 계좌 정보 조회 */
    @GetMapping("/detail")
    public ResponseEntity<String> accountDetail(@RequestHeader Map<String, String> headers) throws IOException {
        log.info(headers.get("custom-accesstoken").toString());
        String accessToken = headers.get("custom-accesstoken");
        log.info(accessToken);
        try {
            NH_DATA_Dto dto = nh_service.getDataWithAccessToken(accessToken);
            String result = nh_service.getMyAccount(dto);
            log.info(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            log.info("NOT FOUND");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* 농협 계좌 송금 */
    @PostMapping("/transfernh")
    public ResponseEntity<String> transferNh(@RequestBody Map<String, String> param) throws IOException {
        log.info("클라이언트로 부터 송금 요청 받음");
        log.info(param.get("id"));
        log.info(param.get("pwd"));
        log.info(param.get("acno"));
        log.info(param.get("tram"));
        log.info("통과 "+ param.get("pwd"));

        Map<String, String> map = memberService.isValidMember(param.get("id"), param.get("pwd"));
        if(map.get("result").equals("true")){
            NH_DATA_Dto dto = nh_service.getData(param.get("id"));
            log.info(dto.toString());
            String result = nh_service.doAccountTransferNh(dto, param.get("acno"), param.get("tram"));
            log.info(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            log.info("못찾음");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

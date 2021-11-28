package com.metabus.dodream.controller;

import com.metabus.dodream.dto.account.NH_DATA_Dto;
import com.metabus.dodream.service.MemberService;
import com.metabus.dodream.service.NH_Service;
import com.metabus.dodream.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dodream")
@RequiredArgsConstructor
@Slf4j
public class WalletController {
    @Autowired
    WalletService walletService;

    @Autowired
    NH_Service nh_service;

    @Autowired
    MemberService memberService;

    @PostMapping("/createWallet")
    public ResponseEntity<String> createMyWallet(@RequestHeader Map<String, String> headers, @RequestBody Map<String, String> params) throws NoSuchAlgorithmException, JSONException {
        String accessToken = headers.get("accesstoken");
        HttpHeaders response_header = new HttpHeaders();
        response_header.set("content-type", "application/json;charset=utf-8");
        String id = params.get("id");
        NH_DATA_Dto dto = nh_service.getDataWithAccessToken(accessToken);
        /**
         * TODO MEMBER 엔티티랑 잘 id 연결이 안돼..
         */
        if (memberService.isValidMember(id)) {
            String wallet = walletService.createWallet(id);
            log.info("controller wallet" + wallet);
            JSONObject response = new JSONObject();
            response.put("result", true);
            response.put("wallet", wallet);
            return new ResponseEntity<>(response.toString(), response_header, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

package com.metabus.dodream.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.metabus.dodream.domain.account.NH_DATA;
import com.metabus.dodream.dto.account.NH_DATA_Dto;
import com.metabus.dodream.repository.NH_Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class NH_Service {

    @Autowired
    NH_Repository nh_repository;

    @Autowired
    HttpRequestService httpRequestService;

    final static String FintechApsno = "001"; // 테스트 과정에선 고정
    final static String BASE_URL = "https://developers.nonghyup.com";

    @Transactional
    public NH_DATA_Dto getData(String id) {
        Optional<NH_DATA> nh_data = nh_repository.findById(id);
        return nh_data.map(nhData -> NH_DATA_Dto.builder()
                .Iscd(nhData.getIscd())
                .accessToken(nhData.getAccessToken())
                .fintechAcno(nhData.getFintechAcno())
                .bncd(nhData.getBncd())
                .acno(nhData.getAcno())
                .finAcno(nhData.getFinAcno())
                .build()).orElse(null);
    }

    /* NH REST API에 요청 */
    public String getAccountData(NH_DATA_Dto dto) throws IOException {
        JsonObject dataForHeader = new JsonObject();
        String api = "/InquireDepositorAccountNumber.nh";

        dataForHeader.addProperty("ApiNm", "InquireDepositorAccountNumber"); // api 요청명
        dataForHeader.addProperty("Tsymd", getTodayDate()); // 오늘 날짜 yyyyMMdd 형태
        dataForHeader.addProperty("Trtm", "112428"); // 전송 시각, 고정되어있네..?
        dataForHeader.addProperty("Iscd", dto.getIscd()); // 기관 코드
        dataForHeader.addProperty("FintechApsno", FintechApsno);
        dataForHeader.addProperty("ApiSvcCd", "DrawingTransferA"); // API 서비스 코드
        dataForHeader.addProperty("IsTuno", randInt(12)); // 임의 숫자 뽑아야함
        dataForHeader.addProperty("AccessToken", dto.getAccessToken()); // accessToken
        JsonObject request = new JsonObject();
        request.add("Header", dataForHeader);
        request.addProperty("Bncd", dto.getBncd());
        request.addProperty("Acno", dto.getAcno());

        return httpRequestService.postRequest(BASE_URL+api, request);
    }

    public static String getTodayDate() {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        return today.format(dtf);
    }

    public static String randInt(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append((int) (Math.random() * 10));
        }
        return result.toString();
    }

    /* NH 계좌 조회 */
    /*public String getMyAccount(NH_DATA_Dto dto){

    }*/
}

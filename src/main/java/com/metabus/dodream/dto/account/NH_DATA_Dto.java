package com.metabus.dodream.dto.account;

import com.metabus.dodream.domain.account.Member;
import com.metabus.dodream.domain.account.NH_DATA;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class NH_DATA_Dto {
    private Member id;
    private String Iscd;    // 기관코드(공통 -기관약정 시 발급된 ‘핀테크 기관코드’를 말함 “마이페이지 > 서비스 관리 > 기관코드”
    private String accessToken; // 인증키(공통 “마이페이지 > 서비스 관리 > 인증키”)
    private String fintechAcno; // 핀테크 앱 일련번호(공통 -핀테크서비스 약정 시 발급된 ‘핀테크 앱 일련번호’ -테스트 과정에서는 ‘001’로 고정되며 핀테크 서비스 약정시 발급된 ‘핀테크 앱 일련번호’로 교체해야 함)
    private String bncd;    // 은행코드(입(송)금시 필요 농협은행:011, 상호금융:012)
    private String acno;    // 계좌번호(입(송)금시 필요 "마이페이지 > 서비스 관리 > 계좌번호")
    private String finAcno; // 핀-어카운트(잔액 조회시 필요)

    public NH_DATA toEntity(){
        return NH_DATA.builder()
                .member(id)
                .Iscd(Iscd)
                .accessToken(accessToken)
                .fintechAcno(fintechAcno)
                .bncd(bncd)
                .acno(acno)
                .finAcno(finAcno)
                .build();
    }

    @Builder
    public NH_DATA_Dto(Member id, String Iscd, String accessToken, String fintechAcno, String bncd, String acno, String finAcno){
        this.id = id;
        this.Iscd = Iscd;
        this.accessToken = accessToken;
        this.fintechAcno = fintechAcno;
        this.bncd = bncd;
        this.acno = acno;
        this.finAcno = finAcno;
    }
}

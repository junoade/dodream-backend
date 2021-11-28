package com.metabus.dodream.domain.account;

import lombok.*;
import org.springframework.data.util.Lazy;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"member", "id"})
@Table(name="NH_DATA")
public class NH_DATA {
    @Id
    private String id;

    /* MEMBER 테이블과 맵핑 */
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id", referencedColumnName = "id")
    private Member member;

    @Column(nullable = false)
    private String Iscd;    // 기관코드(공통 -기관약정 시 발급된 ‘핀테크 기관코드’를 말함 “마이페이지 > 서비스 관리 > 기관코드”

    @Column(nullable = false)
    private String accessToken; // 인증키(공통 “마이페이지 > 서비스 관리 > 인증키”)

    @Column(nullable = false)
    private String fintechAcno; // 핀테크 앱 일련번호(공통 -핀테크서비스 약정 시 발급된 ‘핀테크 앱 일련번호’ -테스트 과정에서는 ‘001’로 고정되며 핀테크 서비스 약정시 발급된 ‘핀테크 앱 일련번호’로 교체해야 함)

    @Column(nullable = false)
    private String bncd;    // 은행코드(입(송)금시 필요 농협은행:011, 상호금융:012)

    @Column(nullable = false)
    private String acno;    // 계좌번호(입(송)금시 필요 "마이페이지 > 서비스 관리 > 계좌번호")

    @Column(nullable = false)
    private String finAcno; // 핀-어카운트(잔액 조회시 필요)
}

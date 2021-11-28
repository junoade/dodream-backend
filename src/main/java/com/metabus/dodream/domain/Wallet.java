package com.metabus.dodream.domain;

import com.metabus.dodream.domain.account.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"member", "id"})
@Table(name="WALLET")
public class Wallet {

    @Id
    private String wallet;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id", referencedColumnName = "id",insertable = false, updatable = false)
    private Member member;

    private String id;

    public Wallet(String hashed_wallet, String id) {
        this.wallet=hashed_wallet;
        this.id=id;
    }
}

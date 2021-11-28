package com.metabus.dodream.dto;

import com.metabus.dodream.domain.Wallet;
import com.metabus.dodream.domain.account.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class WalletDto {
    private String wallet;
    private String id;

    public Wallet toEntity(){
        return Wallet.builder()
                .wallet(wallet)
                .id(id)
                .build();
    }

    @Builder
    public WalletDto(String wallet, String id){
        this.wallet=wallet;
        this.id = id;

    }
}

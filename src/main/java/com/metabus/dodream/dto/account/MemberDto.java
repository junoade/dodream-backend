package com.metabus.dodream.dto.account;

import com.metabus.dodream.domain.account.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@ToString
@Data
@NoArgsConstructor
public class MemberDto {
    private String id;
    private String pwd;
    private String name;

    public Member toEntity(){
       return Member.builder()
               .id(id)
               .pwd(pwd)
               .name(name)
               .build();
    }

    @Builder
    public MemberDto(String id, String pwd, String name){
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }
}

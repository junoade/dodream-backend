package com.metabus.dodream.domain.account;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="MEMBER")
public class Member {

    @Id
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String name;


}

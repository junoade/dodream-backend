package com.metabus.dodream.domain.file;

import com.metabus.dodream.domain.BaseEntity;
import com.metabus.dodream.domain.Wallet;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"wal", "wallet"}) // 성능을 위해 fetch Lazy 타입으로 종종한다. 이때 사용
@Table(name = "FILE")
@IdClass(FileId.class)
public class File extends BaseEntity {

    @Id
    private String fileId; // NFT 토큰값으로 부여

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="walletId", referencedColumnName = "wallet", insertable = false, updatable = false)
    private Wallet wal;

    @Column(name = "walletId")
    private String wallet;

    private String filePath;
    private String originFileName;
    private Long price;
    private Boolean onPublic;


}

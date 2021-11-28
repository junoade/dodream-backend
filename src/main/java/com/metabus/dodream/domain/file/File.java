/*
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
public class File extends BaseEntity {

    @Id
    private String fileId;


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wallet", referencedColumnName = "wallet", insertable = false, updatable = false)
    private Wallet wal;

    @Column(name = "wallet")
    private String wallet;

}
*/

package com.metabus.dodream.repository;

import com.metabus.dodream.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    @Modifying
    @Query(value="UPDATE WALLET W SET W.id = :buyerId, WHERE W.wallet = :wallet",nativeQuery = true)
    @Transactional
    void updateWalletForBuyer(@Param("buyerId") String buyerId, @Param("wallet") String wallet);


    @Query(value="SELECT W FROM Wallet W WHERE W.id = :id AND W.wallet=:wallet")
    Optional<Wallet> getMyWallet(@Param("id") String id, @Param("wallet") String wallet);

}

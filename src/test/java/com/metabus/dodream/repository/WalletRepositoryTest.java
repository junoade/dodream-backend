package com.metabus.dodream.repository;

import com.metabus.dodream.domain.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.SecondaryTable;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class WalletRepositoryTest {

    @Autowired
    WalletRepository walletRepository;


    @Test
    @Transactional
    public void testInsert(){
        String id= "dongeun11";
        String test= "12";
        Wallet wallet = new Wallet(test,id);
        walletRepository.save(wallet);
        TestTransaction.flagForCommit();
    }

    @Test
    @Transactional
    public void testFind(){
        String id= "jayong11";

        Optional<Wallet> wallet = walletRepository.findById(id);
        Assertions.assertThat(wallet.isPresent());
    }

}

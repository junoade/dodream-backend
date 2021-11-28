package com.metabus.dodream.service;

import com.metabus.dodream.domain.Wallet;
import com.metabus.dodream.dto.WalletDto;
import com.metabus.dodream.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static com.metabus.dodream.utils.Hashing.getRandomString;

@Service
@Slf4j
@AllArgsConstructor
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    private final static int LENGTH = 10;

    @Transactional
    public String createWallet(String id) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        String before = getRandomString(LENGTH);
        log.info("before"+before);
        /*sha256.update(before.getBytes());
        String hashed_wallet = new String(sha256.digest());
        log.info(hashed_wallet);*/
        Wallet wallet = new Wallet(before, id);
        walletRepository.save(wallet);

        return before;
    }

    @Transactional
    public boolean isValidWallet(String id, String wallet){
        Optional<Wallet> result = walletRepository.getMyWallet(id, wallet);
        return result.isPresent();
    }

}

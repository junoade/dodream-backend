package com.metabus.dodream.util;

import com.metabus.dodream.utils.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@Slf4j
@SpringBootTest
public class HashingTest {

    @Test
    public void testHashing() throws NoSuchAlgorithmException {
        String message= "C:\\TestMETA\\20211129\\t2.PNG";
        log.info((Hashing.hashingSHA256(message)));
    }

}

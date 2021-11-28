package com.metabus.dodream.repository;

import com.metabus.dodream.domain.account.NH_DATA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class NH_DATA_RepositoryTest {

    @Autowired
    NH_Repository nh_repository;

    @Test
    @Transactional
    public void testFind(){

        Optional<NH_DATA> data = nh_repository.findById("jayong11");

        Assertions.assertTrue(data.isPresent());
    }
}

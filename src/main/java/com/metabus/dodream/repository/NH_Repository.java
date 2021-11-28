package com.metabus.dodream.repository;

import com.metabus.dodream.domain.account.NH_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NH_Repository extends JpaRepository<NH_DATA,String> {

}

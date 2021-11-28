package com.metabus.dodream.repository;

import com.metabus.dodream.domain.account.NH_DATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NH_Repository extends JpaRepository<NH_DATA,String> {

    @Query(value="SELECT N FROM NH_DATA N WHERE N.accessToken = :accessToken")
    Optional<NH_DATA> getAccessToken(@Param("accessToken") String accessToken);

    /*@Query(value="SELECT N FROM NH_DATA N WHERE N.id = :id")
    Optional<NH_DATA> getAccessTokenByValidId(@Param("id") String id);*/

}

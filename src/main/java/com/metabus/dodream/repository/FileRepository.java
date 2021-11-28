package com.metabus.dodream.repository;

import com.metabus.dodream.domain.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

    @Query(value="SELECT F FROM File F WHERE F.filePath IS NOT NULL")
    List<File> getAllFiles();
}

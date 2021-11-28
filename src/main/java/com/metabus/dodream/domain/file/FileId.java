package com.metabus.dodream.domain.file;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class FileId implements Serializable {

    protected String fileId;

    protected String wallet;
}

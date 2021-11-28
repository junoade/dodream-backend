package com.metabus.dodream.dto;

import com.metabus.dodream.domain.Wallet;
import com.metabus.dodream.domain.file.File;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class FileDto {
    private String fileId;
    private String wallet;
    private String filePath;
    private String originFileName;
    private Long price;
    private Boolean onPublic;

    public File toEntity(){
        return File.builder()
                .fileId(fileId)
                .wallet(wallet)
                .filePath(filePath)
                .originFileName(originFileName)
                .price(price)
                .onPublic(onPublic)
                .build();
    }

    @Builder
    public FileDto(String fileId, String wallet, String filePath, String originFileName, Long price, Boolean onPublic){
        this.fileId=fileId;
        this.wallet=wallet;
        this.filePath=filePath;
        this.originFileName=originFileName;
        this.price=price;
        this.onPublic=onPublic;
    }
}

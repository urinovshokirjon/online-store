package uz.urinov.app.Attach.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDto {

    private String id;
    private String originName;
    private Long size;
    private String extension;
    private LocalDateTime createdData;
    private String url;
    private AttachDto compressedAttach;

}

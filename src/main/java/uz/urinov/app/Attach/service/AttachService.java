package uz.urinov.app.Attach.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.urinov.app.Attach.dto.AttachDto;
import uz.urinov.app.Attach.util.ApiResponse;


public interface AttachService {

    ApiResponse<AttachDto> saveToSystem(MultipartFile file);

    ResponseEntity<Resource> open(String fileName);

    Resource download(String fileName);

    PageImpl<AttachDto> getAll(int page, int size);

     boolean delete(String id);
}

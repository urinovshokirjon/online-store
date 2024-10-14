package uz.urinov.app.Attach.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.urinov.app.Attach.dto.AttachDto;
import uz.urinov.app.Attach.service.AttachService;
import uz.urinov.app.Attach.util.ApiResponse;

import static uz.urinov.app.util.ApiUrls.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(ATTACH_URL)
public class AttachController {

    private final AttachService attachService;

    // filelarni yuklash
    @PostMapping(ATTACH_UPLOAD_URL)
    public ResponseEntity<ApiResponse<AttachDto>> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(attachService.saveToSystem(file));
    }

    // File open
    @GetMapping(ATTACH_OPEN_URL)
    public ResponseEntity<Resource> open(@PathVariable("fileName") String fileName)  {
        return attachService.open(fileName);
    }

    // File download
    @GetMapping(ATTACH_DOWNLOAD_URL)
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        Resource file = attachService.download(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    // File Get All
    @GetMapping(ATTACH_ALL_URL)
    public ResponseEntity<PageImpl<AttachDto>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                      @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok(attachService.getAll(page - 1, size));
    }

    // File delete
    @DeleteMapping(ATTACH_ID_URL)
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(attachService.delete(id));
    }


}

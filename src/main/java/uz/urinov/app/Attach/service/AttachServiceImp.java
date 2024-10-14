package uz.urinov.app.Attach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.urinov.app.Attach.dto.AttachDto;
import uz.urinov.app.Attach.entity.AttachEntity;
import uz.urinov.app.Attach.repository.AttachRepository;
import uz.urinov.app.Attach.util.ApiResponse;
import uz.urinov.base.exp.AppBadException;


import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AttachServiceImp implements AttachService {

    private final ResourceLoader resourceLoader;
    private final AttachRepository attachRepository;

    @Value("${attach.upload.folder}")
    private  String folderName;
    @Value("${attach.url}")
    private String attachUrl;

    private static final Map<String, Object> imageExtensionMap = new HashMap<>();

    static {
        imageExtensionMap.put("jpg", new Object());
        imageExtensionMap.put("png", new Object());
        imageExtensionMap.put("jpeg", new Object());
    }


    // filelarni yuklash
    @Override
    public ApiResponse<AttachDto> saveToSystem(MultipartFile file) {
        try {
            String pathFolder = getYmDString(); // 2024/09/27
            String key = UUID.randomUUID().toString(); // dasdasd-dasdasda-asdasda-asdasd
            String extension = getExtension(file.getOriginalFilename()); // .jpg, .png, .mp4

            File folder = new File(folderName+pathFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // save to system
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folderName+ pathFolder + "/" + key + "." + extension);
            Files.write(path, bytes);

            // save to db
            AttachEntity entity = new AttachEntity();
            entity.setId(key + "." + extension);
            entity.setPath(pathFolder);
            entity.setSize(file.getSize());
            entity.setOrigenName(file.getOriginalFilename());
            entity.setExtension(extension);
            attachRepository.save(entity);

            // Compress Image
            AttachDto compressedAttach=null;
            if (imageExtensionMap.containsKey(extension.toLowerCase())) {
                compressedAttach = uploadWithCompression(file);
                attachRepository.updateCompressedAttach(entity.getId(), compressedAttach.getId());
            }
            AttachDto dto = toDTO(entity);
            dto.setCompressedAttach(compressedAttach);

            return new ApiResponse<>(201,false, dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fileni open
    @Override
    public ResponseEntity<Resource> open(String id) {

        AttachEntity entity = getEntity(id);
        if (entity == null) {
            Resource resource = resourceLoader.getResource("classpath:images/default-image.jpg");
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpeg")).body(resource);
        }

//        Path filePath = Paths.get(getPath(entity)).normalize();
        Path filePath = Paths.get(folderName + entity.getPath() + "/" + id);
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + id);
            }
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // Fallback content type
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // File download
    @Override
    public Resource download(String id) {
        try {
            AttachEntity entity = getEntity(id);
            Path file = Paths.get(folderName + entity.getPath() + "/" + id);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
//                throw new RuntimeException("Could not read the file!");
                // Default images
               return resourceLoader.getResource("classpath:images/default-image.jpg");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read the file!");
        }
    }

    // File Get All
    @Override
    public PageImpl<AttachDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<AttachEntity> entityPages = attachRepository.findAll(pageable);
        return new PageImpl<>(entityPages.stream().map(this::toDTO).toList(), pageable, entityPages.getTotalElements());
    }

    // File delete
    @Override
    public boolean delete(String id) {
        AttachEntity entity = getEntity(id);
        File file = new File(folderName + entity.getPath() + "/" + id);
        if (file.exists()) {
           file.delete();  // delete from system
        }
        attachRepository.delete(entity); // delete from db
        return true;
    }

    // File compressed
    public AttachDto uploadWithCompression(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppBadException("File not found");
        }

        try {
            String pathFolder = getYmDString(); // 2024/09/27
            String key = UUID.randomUUID().toString(); // dasdasd-dasdasda-asdasda-asdasd
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename())); // .jpg, .png, .mp4

            // create folder if not exists
            File folder = new File(folderName + "/" + pathFolder);
            if (!folder.exists()) {
                boolean t = folder.mkdirs();
            }

            // save to system
            BufferedImage image = ImageIO.read(file.getInputStream());

            Path path = Paths.get(folderName  + pathFolder + "/" + key + "." + extension);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(extension);
            ImageWriter writer = writers.next();

            ImageOutputStream outputStream = ImageIO.createImageOutputStream(path.toFile());
            writer.setOutput(outputStream);

            ImageWriteParam params = writer.getDefaultWriteParam();
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            params.setCompressionQuality(0.20f);

            writer.write(null, new IIOImage(image, null, null), params);

            outputStream.close();
            writer.dispose();

            // save to db
            AttachEntity entity = new AttachEntity();
            entity.setId(key + "." + extension);
            entity.setPath(pathFolder);
            entity.setSize(file.getSize());
            entity.setOrigenName(file.getOriginalFilename());
            entity.setExtension(extension);
            attachRepository.save(entity);

            return toDTO(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        // Papkani yaratadi 2024/09/27
    private String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);  // 2024
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1; // 8+1=9
        int day = Calendar.getInstance().get(Calendar.DATE); // 27
        return year + "/" + month + "/" + day;
    }

      // Fileni tipini aniqlaydi .png, .mp3
    private String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

        // File Url ni aniqlaydi
    public String openURL(String id) {
        return attachUrl + id;
    }

    private AttachDto toDTO(AttachEntity entity) {
        AttachDto attachDTO = new AttachDto();
        attachDTO.setId(entity.getId());
        attachDTO.setOriginName(entity.getOrigenName());
        attachDTO.setSize(entity.getSize());
        attachDTO.setExtension(entity.getExtension());
        if (entity.getCreatedDate() != null) {
            attachDTO.setCreatedData(entity.getCreatedDate().toLocalDateTime());
        }
        attachDTO.setUrl(openURL(entity.getId()));
        return attachDTO;
    }

    public AttachEntity getEntity(String id) {
        return attachRepository.findById(id).orElseThrow(()->new AppBadException("File not found"));
//        return attachRepository.findById(id).orElse(null);
    }



}

package uz.urinov.app.Attach.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.urinov.app.Attach.entity.AttachEntity;


public interface AttachRepository extends JpaRepository<AttachEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE AttachEntity a SET a.compressedAttachId=?2 WHERE a.id=?1")
    void updateCompressedAttach(String attachId, String compressedAttachId);
}

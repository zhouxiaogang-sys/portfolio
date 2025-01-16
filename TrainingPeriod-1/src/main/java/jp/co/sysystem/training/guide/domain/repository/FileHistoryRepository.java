package jp.co.sysystem.training.guide.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sysystem.training.guide.domain.table.FileHistory;

@Repository
public interface FileHistoryRepository extends JpaRepository<FileHistory, Long> {
  List<FileHistory> findByFileIdOrderByCreateTimeDesc(String fileId);

  Optional<FileHistory> findByFileIdAndVersion(String fileId, String version);

  @Query("SELECT MAX(f.version) FROM FileHistory f WHERE f.fileId = :fileId AND f.version LIKE CONCAT(:versionPrefix, '%')")
  Optional<String> findMaxVersionByFileIdAndPrefix(
          @Param("fileId") String fileId,
          @Param("versionPrefix") String versionPrefix);

  Optional<FileHistory> findTopByFileIdOrderByCreateTimeDesc(String fileId);
}

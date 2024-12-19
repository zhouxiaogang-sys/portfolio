package jp.co.sysystem.training.guide.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sysystem.training.guide.domain.table.FileHistory;

@Repository
public interface FileHistoryRepository extends JpaRepository<FileHistory, Long> {
  List<FileHistory> findByFileIdOrderByCreateTimeDesc(String fileId);

  Optional<FileHistory> findByFileIdAndVersion(String fileId, String version);

  Optional<FileHistory> findTopByFileIdOrderByCreateTimeDesc(String fileId);
}

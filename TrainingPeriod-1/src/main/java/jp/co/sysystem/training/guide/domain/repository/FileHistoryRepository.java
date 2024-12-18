package jp.co.sysystem.training.guide.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.sysystem.training.guide.domain.table.FileHistory;


public interface FileHistoryRepository extends JpaRepository<FileHistory, Long>{
  List<FileHistory> findByFilePathOrderByCreateTimeDesc(String filePath);
  Optional<FileHistory> findByFilePathAndVersion(String filePath, String version);
  Optional<FileHistory> findTopByFilePathOrderByCreateTimeDesc(String filePath);

}

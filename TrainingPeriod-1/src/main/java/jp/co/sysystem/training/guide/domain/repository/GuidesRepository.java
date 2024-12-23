package jp.co.sysystem.training.guide.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sysystem.training.guide.domain.table.MarkdownFile;

@Repository
public interface GuidesRepository extends JpaRepository<MarkdownFile, String> {
  List<MarkdownFile> findAllByOrderBySortOrderAsc();
  
  @Modifying
  @Query("UPDATE MarkdownFile f SET f.updateTime = :updateTime WHERE f.fileId = :fileId")
  int updateUpdateTimeByFileId(@Param("fileId") String fileId, @Param("updateTime") LocalDateTime updateTime);

}

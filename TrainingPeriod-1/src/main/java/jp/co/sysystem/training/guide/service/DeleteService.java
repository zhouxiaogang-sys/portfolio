package jp.co.sysystem.training.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import jp.co.sysystem.training.guide.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeleteService {
  @Autowired
  private GuidesRepository grep;
  
  @Transactional
  public void logicDelete(String fileId) {
    try {
      // ファイルの存在チェック
      MarkdownFile guideBook = grep.findByFileId(fileId);
      if (guideBook == null) {
        throw new NotFoundException(fileId + " のファイルが見つかりません");
      }

      // プロパティの更新
     guideBook.setIsDeleted(true);

      // 保存
      grep.save(guideBook);

    } catch (Exception e) {
      // エラーログを記録
      log.error("ガイドブックの削除中にエラーが発生しました。fileId: " + fileId, e);
      throw e;
    }
  }

}

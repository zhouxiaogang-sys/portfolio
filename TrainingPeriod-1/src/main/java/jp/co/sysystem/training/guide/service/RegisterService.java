package jp.co.sysystem.training.guide.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sysystem.training.guide.domain.dto.GuideBookDTO;
import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisterService {

  @Autowired
  private GuidesRepository grep;
  
  @Autowired
  private ModelMapper modelMapper;

  /**
   * 新規ガイドブックを登録する
   * 
   * @param fileId ファイルID
   * @param fileName ファイル名
   * @param task タスク
   * @param sortOrder ソート順
   * @param author 作成者
   * @throws Exception パラメータが不正な場合にスロー
   */
  public void registerNewGuideBooks(String fileId, String fileName, String task,
          int sortOrder, String author) {
    try {     
      GuideBookDTO dto = GuideBookDTO.createNew(fileId, fileName, task, sortOrder, author);      
      
      MarkdownFile guideBook = modelMapper.map(dto, MarkdownFile.class);
      grep.save(guideBook);

    } catch (Exception e) {
      log.error("ガイドブックの登録中にエラーが発生しました。fileId: " + fileId, e);
      throw e;
    }
  }

}

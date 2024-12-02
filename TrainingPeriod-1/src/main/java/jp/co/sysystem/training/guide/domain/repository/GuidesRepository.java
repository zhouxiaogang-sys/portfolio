package jp.co.sysystem.training.guide.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.sysystem.training.guide.domain.table.MarkdownFile;

@Repository
public interface GuidesRepository extends CrudRepository<MarkdownFile, Integer> {

}

package jp.co.sysystem.training.guide.service;

import jp.co.sysystem.training.guide.domain.repository.GuidesRepository;
import jp.co.sysystem.training.guide.domain.table.MarkdownFile;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    GuidesRepository grep;

    public List<MarkdownFile> showIndex() {

        return StreamSupport.stream(grep.findAll().spliterator(), false)
                        .sorted(Comparator.comparing(MarkdownFile::getSortOrder))
                        .collect(Collectors.toList());
    }

}
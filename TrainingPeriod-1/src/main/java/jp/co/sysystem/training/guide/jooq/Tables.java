/*
 * This file is generated by jOOQ.
 */
package jp.co.sysystem.training.guide.jooq;


import jp.co.sysystem.training.guide.jooq.tables.FileHistory;
import jp.co.sysystem.training.guide.jooq.tables.MarkdownFile;
import jp.co.sysystem.training.guide.jooq.tables.MarkdownFileHistory;
import jp.co.sysystem.training.guide.jooq.tables.User;


/**
 * Convenience access to all tables in trainingguidebook.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * 編集記録表
     */
    public static final FileHistory FILE_HISTORY = FileHistory.FILE_HISTORY;

    /**
     * マークダウンファイル管理テーブル
     */
    public static final MarkdownFile MARKDOWN_FILE = MarkdownFile.MARKDOWN_FILE;

    /**
     * マークダウンファイル変更履歴テーブル
     */
    public static final MarkdownFileHistory MARKDOWN_FILE_HISTORY = MarkdownFileHistory.MARKDOWN_FILE_HISTORY;

    /**
     * The table <code>trainingguidebook.user</code>.
     */
    public static final User USER = User.USER;
}
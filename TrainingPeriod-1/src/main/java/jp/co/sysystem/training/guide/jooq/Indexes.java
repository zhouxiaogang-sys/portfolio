/*
 * This file is generated by jOOQ.
 */
package jp.co.sysystem.training.guide.jooq;


import jp.co.sysystem.training.guide.jooq.tables.FileHistory;
import jp.co.sysystem.training.guide.jooq.tables.MarkdownFile;
import jp.co.sysystem.training.guide.jooq.tables.MarkdownFileHistory;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in trainingguidebook.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index MARKDOWN_FILE_IDX_AUTHOR = Internal.createIndex(DSL.name("idx_author"), MarkdownFile.MARKDOWN_FILE, new OrderField[] { MarkdownFile.MARKDOWN_FILE.AUTHOR }, false);
    public static final Index FILE_HISTORY_IDX_CREATE_TIME = Internal.createIndex(DSL.name("idx_create_time"), FileHistory.FILE_HISTORY, new OrderField[] { FileHistory.FILE_HISTORY.CREATE_TIME }, false);
    public static final Index MARKDOWN_FILE_HISTORY_IDX_FILE_NO = Internal.createIndex(DSL.name("idx_file_no"), MarkdownFileHistory.MARKDOWN_FILE_HISTORY, new OrderField[] { MarkdownFileHistory.MARKDOWN_FILE_HISTORY.FILE_NO }, false);
    public static final Index FILE_HISTORY_IDX_FILE_PATH = Internal.createIndex(DSL.name("idx_file_path"), FileHistory.FILE_HISTORY, new OrderField[] { FileHistory.FILE_HISTORY.FILE_PATH }, false);
    public static final Index MARKDOWN_FILE_HISTORY_IDX_MODIFY_TIME = Internal.createIndex(DSL.name("idx_modify_time"), MarkdownFileHistory.MARKDOWN_FILE_HISTORY, new OrderField[] { MarkdownFileHistory.MARKDOWN_FILE_HISTORY.MODIFY_TIME }, false);
    public static final Index MARKDOWN_FILE_IDX_UPDATE_TIME = Internal.createIndex(DSL.name("idx_update_time"), MarkdownFile.MARKDOWN_FILE, new OrderField[] { MarkdownFile.MARKDOWN_FILE.UPDATE_TIME }, false);
    public static final Index MARKDOWN_FILE_IDX_UPLOAD_TIME = Internal.createIndex(DSL.name("idx_upload_time"), MarkdownFile.MARKDOWN_FILE, new OrderField[] { MarkdownFile.MARKDOWN_FILE.UPLOAD_TIME }, false);
    public static final Index FILE_HISTORY_IDX_VERSION = Internal.createIndex(DSL.name("idx_version"), FileHistory.FILE_HISTORY, new OrderField[] { FileHistory.FILE_HISTORY.VERSION }, false);
}

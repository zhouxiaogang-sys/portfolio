/*
 * This file is generated by jOOQ.
 */
package jp.co.sysystem.training.guide.jooq.tables.records;


import java.time.LocalDateTime;

import jp.co.sysystem.training.guide.jooq.tables.FileHistory;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 編集記録表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FileHistoryRecord extends UpdatableRecordImpl<FileHistoryRecord> implements Record7<Long, String, String, String, String, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>trainingguidebook.file_history.id</code>. PK ID
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.id</code>. PK ID
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>trainingguidebook.file_history.file_path</code>. ファイルパス
     */
    public void setFilePath(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.file_path</code>. ファイルパス
     */
    public String getFilePath() {
        return (String) get(1);
    }

    /**
     * Setter for <code>trainingguidebook.file_history.content</code>. ファイルコンテンツ
     */
    public void setContent(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.content</code>. ファイルコンテンツ
     */
    public String getContent() {
        return (String) get(2);
    }

    /**
     * Setter for <code>trainingguidebook.file_history.commit_message</code>.
     * コミット情報
     */
    public void setCommitMessage(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.commit_message</code>.
     * コミット情報
     */
    public String getCommitMessage() {
        return (String) get(3);
    }

    /**
     * Setter for <code>trainingguidebook.file_history.author</code>. 作者
     */
    public void setAuthor(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.author</code>. 作者
     */
    public String getAuthor() {
        return (String) get(4);
    }

    /**
     * Setter for <code>trainingguidebook.file_history.create_time</code>. 作成日付
     */
    public void setCreateTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.create_time</code>. 作成日付
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>trainingguidebook.file_history.version</code>. ヴァージョン
     */
    public void setVersion(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>trainingguidebook.file_history.version</code>. ヴァージョン
     */
    public String getVersion() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, String, String, LocalDateTime, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, String, String, String, String, LocalDateTime, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return FileHistory.FILE_HISTORY.ID;
    }

    @Override
    public Field<String> field2() {
        return FileHistory.FILE_HISTORY.FILE_PATH;
    }

    @Override
    public Field<String> field3() {
        return FileHistory.FILE_HISTORY.CONTENT;
    }

    @Override
    public Field<String> field4() {
        return FileHistory.FILE_HISTORY.COMMIT_MESSAGE;
    }

    @Override
    public Field<String> field5() {
        return FileHistory.FILE_HISTORY.AUTHOR;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return FileHistory.FILE_HISTORY.CREATE_TIME;
    }

    @Override
    public Field<String> field7() {
        return FileHistory.FILE_HISTORY.VERSION;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFilePath();
    }

    @Override
    public String component3() {
        return getContent();
    }

    @Override
    public String component4() {
        return getCommitMessage();
    }

    @Override
    public String component5() {
        return getAuthor();
    }

    @Override
    public LocalDateTime component6() {
        return getCreateTime();
    }

    @Override
    public String component7() {
        return getVersion();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFilePath();
    }

    @Override
    public String value3() {
        return getContent();
    }

    @Override
    public String value4() {
        return getCommitMessage();
    }

    @Override
    public String value5() {
        return getAuthor();
    }

    @Override
    public LocalDateTime value6() {
        return getCreateTime();
    }

    @Override
    public String value7() {
        return getVersion();
    }

    @Override
    public FileHistoryRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public FileHistoryRecord value2(String value) {
        setFilePath(value);
        return this;
    }

    @Override
    public FileHistoryRecord value3(String value) {
        setContent(value);
        return this;
    }

    @Override
    public FileHistoryRecord value4(String value) {
        setCommitMessage(value);
        return this;
    }

    @Override
    public FileHistoryRecord value5(String value) {
        setAuthor(value);
        return this;
    }

    @Override
    public FileHistoryRecord value6(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    @Override
    public FileHistoryRecord value7(String value) {
        setVersion(value);
        return this;
    }

    @Override
    public FileHistoryRecord values(Long value1, String value2, String value3, String value4, String value5, LocalDateTime value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FileHistoryRecord
     */
    public FileHistoryRecord() {
        super(FileHistory.FILE_HISTORY);
    }

    /**
     * Create a detached, initialised FileHistoryRecord
     */
    public FileHistoryRecord(Long id, String filePath, String content, String commitMessage, String author, LocalDateTime createTime, String version) {
        super(FileHistory.FILE_HISTORY);

        setId(id);
        setFilePath(filePath);
        setContent(content);
        setCommitMessage(commitMessage);
        setAuthor(author);
        setCreateTime(createTime);
        setVersion(version);
        resetChangedOnNotNull();
    }
}

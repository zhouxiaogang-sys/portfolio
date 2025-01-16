package jp.co.sysystem.training.guide.exception;

/**
 * リソースが見つからない場合にスローされる例外
 */
public class NotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;  // 追加
    private final int errorCode;  // finalを追加
    
    /**
     * デフォルトのエラーコード(404)で例外を生成する
     * 
     * @param message エラーメッセージ
     */
    public NotFoundException(String message) {
        super(message);
        this.errorCode = 404;
    }
    
    /**
     * 指定されたエラーコードで例外を生成する
     * 
     * @param message エラーメッセージ
     * @param errorCode エラーコード
     */
    public NotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * エラーコードを取得する
     * 
     * @return エラーコード
     */
    public int getErrorCode() {
        return errorCode;
    }
}
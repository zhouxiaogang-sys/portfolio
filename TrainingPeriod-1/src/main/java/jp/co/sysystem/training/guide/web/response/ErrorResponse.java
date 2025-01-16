package jp.co.sysystem.training.guide.web.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int cause;
    private LocalDateTime time;
    
    /**
     * エラーメッセージのみを指定してインスタンスを生成する
     * 
     * @param message エラーメッセージ
     */
    public ErrorResponse(String message) {
        this.message = message;
    }
    
    /**
     * 全てのフィールドを指定してインスタンスを生成する
     * 
     * @param cause エラー原因コード
     * @param message エラーメッセージ
     * @param time エラー発生時刻
     */
    public ErrorResponse(int cause, String message, LocalDateTime time) {
      this.message = message;
      this.cause = cause;
      this.time = time;
    }
}
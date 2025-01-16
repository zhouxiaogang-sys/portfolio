package jp.co.sysystem.training.guide.exception;

public class UnauthorizedException extends RuntimeException {
  private int errorCode;
  
  public UnauthorizedException(String message) {
      super(message);
      this.errorCode = 401;
  }
  
  public UnauthorizedException(String message, int errorCode) {
      super(message);
      this.errorCode = errorCode;
  }
  
  public int getErrorCode() {
      return errorCode;
  }
}
package jp.co.sysystem.training.guide.exception;

public class OptimisticLockException extends RuntimeException {
  private int errorCode;
  
  public OptimisticLockException(String message) {
      super(message);
      this.errorCode = 409;
  }
  
  public OptimisticLockException(String message, int errorCode) {
      super(message);
      this.errorCode = errorCode;
  }
  
  public int getErrorCode() {
      return errorCode;
  }
}
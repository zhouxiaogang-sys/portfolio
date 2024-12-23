package jp.co.sysystem.training.guide.web.request;

public class CompareRequest {
  private String oldVersion;
  private String newVersion;

  // getters and setters
  public String getOldVersion() {
    return oldVersion;
  }

  public void setOldVersion(String oldVersion) {
    this.oldVersion = oldVersion;
  }

  public String getNewVersion() {
    return newVersion;
  }

  public void setNewVersion(String newVersion) {
    this.newVersion = newVersion;
  }
}
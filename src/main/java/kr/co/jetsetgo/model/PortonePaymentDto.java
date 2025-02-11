package kr.co.jetsetgo.model;;


public class PortonePaymentDto {

  private long payId;
  private String resCode;
  private String recepContent;
  private java.sql.Timestamp startDate;
  private java.sql.Timestamp endDate;


  public long getPayId() {
    return payId;
  }

  public void setPayId(long payId) {
    this.payId = payId;
  }


  public String getResCode() {
    return resCode;
  }

  public void setResCode(String resCode) {
    this.resCode = resCode;
  }


  public String getRecepContent() {
    return recepContent;
  }

  public void setRecepContent(String recepContent) {
    this.recepContent = recepContent;
  }


  public java.sql.Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Timestamp startDate) {
    this.startDate = startDate;
  }


  public java.sql.Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Timestamp endDate) {
    this.endDate = endDate;
  }

}

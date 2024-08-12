package kr.co.jetsetgo.model;


public class TbSupport {

  private long supportId;
  private long writerId;
  private String writerName;
  private long num;
  private String title;
  private String detail;
  private java.sql.Timestamp createdDate;
  private String answer;
  private long publicStatus;
  private String category;


  public long getSupportId() {
    return supportId;
  }

  public void setSupportId(long supportId) {
    this.supportId = supportId;
  }


  public long getWriterId() {
    return writerId;
  }

  public void setWriterId(long writerId) {
    this.writerId = writerId;
  }


  public String getWriterName() {
    return writerName;
  }

  public void setWriterName(String writerName) {
    this.writerName = writerName;
  }


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public java.sql.Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(java.sql.Timestamp createdDate) {
    this.createdDate = createdDate;
  }


  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }


  public long getPublicStatus() {
    return publicStatus;
  }

  public void setPublicStatus(long publicStatus) {
    this.publicStatus = publicStatus;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}

package kr.co.jetsetgo.model;


public class TbSupport {

  private long support_Id;
  private long writer_Id;
  private String writer_Name;
  private long num;
  private String title;
  private String detail;
  private java.sql.Timestamp created_Date;
  private String answer;
  private long publicStatus;
  private String category;


  public long getSupport_Id() {
    return support_Id;
  }

  public void setSupport_Id(long support_Id) {
    this.support_Id = support_Id;
  }


  public long getWriter_Id() {
    return writer_Id;
  }

  public void setWriter_Id(long writer_Id) {
    this.writer_Id = writer_Id;
  }


  public String getWriter_Name() {
    return writer_Name;
  }

  public void setWriter_Name(String writer_Name) {
    this.writer_Name = writer_Name;
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


  public java.sql.Timestamp getCreated_Date() {
    return created_Date;
  }

  public void setCreated_Date(java.sql.Timestamp created_Date) {
    this.created_Date = created_Date;
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

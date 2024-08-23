package kr.co.jetsetgo.model;


public class TbMembersDto {

  private long membernum;
  private String name;
  private String username;
  private String password;
  private java.sql.Date birthdate;
  private String phonenumber;
  private long agreeterms;


  public long getMembernum() {
    return membernum;
  }

  public void setMembernum(long membernum) {
    this.membernum = membernum;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public java.sql.Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(java.sql.Date birthdate) {
    this.birthdate = birthdate;
  }


  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }


  public long getAgreeterms() {
    return agreeterms;
  }

  public void setAgreeterms(long agreeterms) {
    this.agreeterms = agreeterms;
  }

}

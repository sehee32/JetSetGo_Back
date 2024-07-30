package kr.co.jetsetgo.model;

public class SupportDto {

    private int supportNum;    // 문의번호
    private int authorNum;     // 작성자 번호
    private int num;               // 번호
    private String title;         // 제목
    private String detail;        // 내용
    private String author;        // 작성자
    private String createdDate;     // 작성일
    private String answer;        // 답변
    private boolean isPublic;     // 공개여부
    private String category;      // 카테고리

    // 기본 생성자
    public SupportDto() {
    }

    // 모든 필드를 초기화하는 생성자
    public SupportDto(int supportNum, int authorNum, int num, String title, String detail, String author, String createdDate, String answer, boolean isPublic, String category) {
        this.supportNum = supportNum;
        this.authorNum = authorNum;
        this.num = num;
        this.title = title;
        this.detail = detail;
        this.author = author;
        this.createdDate = createdDate;
        this.answer = answer;
        this.isPublic = isPublic;
        this.category = category;
    }

    // Getter와 Setter 메서드
    public int getSupportNum() {
        return supportNum;
    }
    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getAuthorNum() {
        return authorNum;
    }
    public void setAuthorNum(int authorNum) {
        this.authorNum = authorNum;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public boolean getIsPublic() {
        return isPublic;
    }
    public void setISPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {this.category = category; }
}

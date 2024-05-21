package kr.co.jetsetgo.model;

public class SupportDTO {

    private int supportNum;    // 문의번호
    private int num;               // 번호
    private String title;         // 제목
    private String detail;        // 내용
    private String author;        // 작성자
    private String createdDate;     // 작성일
    private String answer;        // 답변
    private boolean isPublic;     // 공개여부

    // 기본 생성자
    public SupportDTO() {
    }

    // 모든 필드를 초기화하는 생성자
    public SupportDTO(int supportNum, int num, String title, String detail, String author, String createdDate, String answer, boolean isPublic) {
        this.supportNum = supportNum;
        this.num = num;
        this.title = title;
        this.detail = detail;
        this.author = author;
        this.createdDate = createdDate;
        this.answer = answer;
        this.isPublic = isPublic;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getdDetail() {
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "SupportDTO{" +
                "supportNum=" + supportNum +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", author='" + author + '\'' +
                ", createdDate=" + createdDate +
                ", answer='" + answer + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }

}

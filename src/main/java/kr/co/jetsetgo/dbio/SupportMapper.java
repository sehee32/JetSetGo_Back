package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.TbSupport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupportMapper {

    List<TbSupport> findAll(); //1:1문의 전체 검색)
    List<TbSupport> findBycategory(String category); //1:1문의 검색(카테고리)
    List<TbSupport> findBySearchQuery(String search); //1:1문의 검색(검색어)

    TbSupport findById(String id); //SUPPORT_ID로 문의 검색

    void addSupport(TbSupport support); // 문의하기 추가

    void editSupport(TbSupport support); // 문의하기 수정

    void removeSupport(String id); // 문의하기 삭제

    String findPasswordByWriterId(long writerId); // id로 작성자 비밀번호 검색

    long findIdByWriterName(String writerName); // 사용자 아이디로 id 검색








//
//    int count(@Param("option1Name") String option1Name, @Param("option1") String option1,
//              @Param("option2Name") String option2Name, @Param("option2") String option2);
//
//    ArrayList<TbSupport> stayUser(@Param("begin") int begin, @Param("end") int end,
//                                   @Param("option1Name") String option1Name, @Param("option1") String option1,
//                                   @Param("option2Name") String option2Name, @Param("option2") String option2);

//    int reservationcount();
//
//    void reservationProc(ReservationDTO reservation);
//
//    // 리스트 가져오기 전 업데이트
//    void listUp();
//
//    // 예약확정 가져오기
//    ArrayList<ReservationDTO> resConfirmed(String id);
//
//    // 이용완료 가져오기
//    ArrayList<ReservationDTO> resCompleted(String id);
//
//    // 예약취소 가져오기
//    ArrayList<ReservationDTO> resCancellation(String id);
//
//    // 예약 삭제
//    void delete(int no);
//
//    // 예약 상세 가져오기
//    ReservationDTO resDetail(int no);
//
//    // 예약 취소하기
//    void resUpdate(int no);
}

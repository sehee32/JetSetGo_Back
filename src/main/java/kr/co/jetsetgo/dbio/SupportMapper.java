package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.SupportDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SupportMapper {

    SupportDto findById(String id);

//        SupportDto loginProc(String id);
//
//    SupportDto serchNickname(String nickname);
//
//    void registerProc(SupportDto member);
//
//    SupportDto getMobile(String id);
//
//    void passwdReset(SupportDto member);
//
//    void updateNickname(SupportDto member);
//
//    void updateName(SupportDto member);
//
//    void updateMobile(SupportDto member);
//
//    void delete(SupportDto member);
//
//    int count(@Param("option1Name") String option1Name, @Param("option1") String option1,
//              @Param("option2Name") String option2Name, @Param("option2") String option2);
//
//    ArrayList<SupportDto> stayUser(@Param("begin") int begin, @Param("end") int end,
//                                   @Param("option1Name") String option1Name, @Param("option1") String option1,
//                                   @Param("option2Name") String option2Name, @Param("option2") String option2);
}

package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.TbMembersDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    TbMembersDto findUserInfoByUserName(String userName); //사용자 로그인 ID로 검색

    void editUserInfo(String id, String contact); // 사용자 정보 수정
}

package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.TbMembersDto;

import java.util.Map;

public interface MyPageService {
    public TbMembersDto selectMembers(String username); //사용자 로그인 ID 값으로 사용자 정보 가져오기
    public boolean updateUserInfo(Map<String, String> userInfoMap); //사용자 정보 변경
    public boolean updateUserPassword(Map<String, String> userInfoMap); //사용자 정보 변경
}

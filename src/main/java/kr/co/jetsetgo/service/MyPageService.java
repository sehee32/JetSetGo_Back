package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.TbMembersDto;

public interface MyPageService {
    public TbMembersDto selectMembers(String username); //사용자 로그인 ID 값으로 사용자 정보 가져오기
}

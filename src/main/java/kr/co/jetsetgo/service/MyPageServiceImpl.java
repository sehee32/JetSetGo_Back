package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.MyPageMapper;
import kr.co.jetsetgo.model.TbMembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    private MyPageMapper myPageMapper;

    //사용자 로그인 ID 값으로 사용자 정보 가져오기
    public TbMembersDto selectMembers(String username) {
        
        TbMembersDto result = myPageMapper.findUserInfoByUserName(username);

        return result;
    }

    //사용자 정보 변경
    public boolean updateUserInfo(Map<String, String> userInfoMap) {
        String id = userInfoMap.get("userId");
        String contact = userInfoMap.get("contact");
        boolean isUpdated = myPageMapper.editUserInfo(id, contact);
        if (isUpdated) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }


        return isUpdated;
    }

    //사용자 비밀번호 변경
    public boolean updateUserPassword(Map<String, String> userInfoMap) {
        String id = userInfoMap.get("userId");
        String password = userInfoMap.get("password");
        boolean isUpdated = myPageMapper.editUserPassword(id, password);

        if (isUpdated) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }


        return isUpdated;
    }

}

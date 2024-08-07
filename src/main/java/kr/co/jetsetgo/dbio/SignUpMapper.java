package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.SignUpDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SignUpMapper {
    void insertMember(SignUpDto signUpDto);

    SignUpDto findByUsername(String username);
}

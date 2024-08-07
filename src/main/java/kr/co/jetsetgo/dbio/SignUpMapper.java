package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.SignUpDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SignUpMapper {
    @Insert("INSERT INTO MEMBERS (NAME, USERNAME, PASSWORD, BIRTHDATE, PHONENUMBER, AGREETERMS) VALUES (#{name}, #{username}, #{password}, #{birthdate}, #{phoneNumber}, #{agreeTerms})")
    void insertMember(SignUpDto signUpDto);

    @Select("SELECT * FROM MEMBERS WHERE USERNAME = #{username}")
    SignUpDto findByUsername(String username);
}

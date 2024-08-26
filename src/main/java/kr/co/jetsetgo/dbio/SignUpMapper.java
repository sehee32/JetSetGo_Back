package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.SignUpDto;
import kr.co.jetsetgo.model.TbMembersDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SignUpMapper {
    void insertMember(TbMembersDto tbMembersDto);

    TbMembersDto findByUsername(String username);
}

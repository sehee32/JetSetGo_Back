package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.TbSupport;

import java.util.List;
import java.util.Map;

public interface SupportService {
    public List<TbSupport> selectSupports(Map<String, String> SearchMap); //1:1문의 검색
    public TbSupport selectSupport(Map<String, String> supportIdMap); //문의하기 상세 진입
    public boolean selectSupportPassword(Map<String, String> supportIdMap); //문의하기 상세 진입시 비밀번호 확인
    public boolean insertSupport(TbSupport support); //문의하기 추가
    public boolean updateSupport(TbSupport support); //문의하기 추가
    public boolean deleteSupport(Map<String, String> supportIdMap); //문의하기 삭제
}

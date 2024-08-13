package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.SupportDto;
import kr.co.jetsetgo.model.TbSupport;

import java.util.List;
import java.util.Map;

public interface SupportService {
    public List<TbSupport> selectSupports(Map<String, String> SearchMap); //1:1문의 검색
    public TbSupport selectSupport(Map<String, String> supportIdMap); //문의하기 상세 진입
}

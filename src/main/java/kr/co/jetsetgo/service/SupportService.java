package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.SupportDto;
import kr.co.jetsetgo.model.TbSupport;

import java.util.List;

public interface SupportService {
    public List<SupportDto> selectSupports(String data);
    public TbSupport selectSupport(String id);
}

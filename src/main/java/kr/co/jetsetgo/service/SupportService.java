package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.SupportDto;

import java.util.List;

public interface SupportService {
    public List<SupportDto> selectSupports(String data);
}

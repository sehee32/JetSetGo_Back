package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.SupportDTO;

import java.util.List;

public interface SupportService {
    public List<SupportDTO> selectSupports(String data);
}

package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.TbSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.jetsetgo.service.SupportService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class SupportContoller {

    @Autowired
    private SupportService SupportService;

    /**
     * Sample Controller
     * 호출 예시 : http://localhost:8080/api/Hello?data=호출테스트
     * 결과 : 호출테스트 Hello Word
     *
     * @param data
     * @return
     */

    //1:1문의 검색
    @PostMapping(value = "/supportSearch", produces = "application/json; charset=utf-8")
    public List<TbSupport> supportSearch(@RequestBody(required = false) Map<String, String> SearchMap) {
        System.out.println("1:1문의 검색: " + SearchMap);
        List<TbSupport> result = SupportService.selectSupports(SearchMap);
        System.out.println(result);
        return result;
    }

    //문의하기 상세 진입
    @PostMapping(value = "/enterSupport", produces = "application/json; charset=utf-8")
    public TbSupport enterSupport(@RequestBody(required = false) Map<String, String> supportIdMap) {
        TbSupport result = SupportService.selectSupport(supportIdMap);
        return result;
    }

    //문의하기 상세 진입시 비밀번호 확인
    @PostMapping(value = "/supportCheckPassword", produces = "application/json; charset=utf-8")
    public boolean supportCheckPassword(@RequestBody(required = false) Map<String, String> supportIdMap) {
        boolean result = SupportService.selectSupportPassword(supportIdMap);
        return result;
    }

    //문의하기 추가
    @PostMapping(value = "/supportAdd", produces = "application/json; charset=utf-8")
    public boolean supportAdd(@RequestBody(required = false) TbSupport support) {
        System.out.println("문의하기: " + support);
        boolean result = SupportService.insertSupport(support);
        return result;
    }

    //문의하기 수정(수정, 답변)
    @PostMapping(value = "/supportEdit", produces = "application/json; charset=utf-8")
    public boolean supportEdit(@RequestBody(required = false) TbSupport support) {
        System.out.println("문의수정: " + support);
        boolean result = SupportService.updateSupport(support);
        return result;
    }

    //문의하기 삭제
    @PostMapping(value = "/supportRemove", produces = "application/json; charset=utf-8")
    public boolean supportRemove(@RequestBody(required = false) Map<String, String> supportIdMap) {
        boolean result = SupportService.deleteSupport(supportIdMap);
        return result;
    }

}
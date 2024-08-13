package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.SupportDto;
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
    public List<SupportDto> supportSearch(@RequestBody(required = false) String data) {
        System.out.println("1:1문의 검색: " + data);
        // data에 해당하는 값 받아옴
//        List<SupportDTO> result = SupportService.selectSupports(data);
        System.out.println(SupportService.selectSupports(data));
        return SupportService.selectSupports(data);
    }

    //문의하기 추가
    @PostMapping(value = "/supportAdd", produces = "application/json; charset=utf-8")
    public boolean supportAdd(@RequestBody(required = false) String data) {
        System.out.println("문의하기: " + data);
        // data에 해당하는 값 받아옴
        return true;
    }

    //문의하기 상세 진입
    @PostMapping(value = "/enterSupport", produces = "application/json; charset=utf-8")
    public TbSupport enterSupport(@RequestBody(required = false) Map<String, String> supportIdMap) {
        String supportId = supportIdMap.get("supportId");
        TbSupport result = SupportService.selectSupport(supportId);
//        if (result != null) {
//            System.out.println(result.getSupportId());
//        } else {
//            System.out.println("컨트롤러 null인디");
//        }

        return result;
    }

}
package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.SupportDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class SupportContoller {

//    @Autowired
//    private SupportService SupportService;

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
    public List<SupportDTO> supportSearch(@RequestBody(required = false) String data) {
        System.out.println("1:1문의 검색: " + data);
        // data에 해당하는 값 받아옴
        List<SupportDTO> searchResults = new ArrayList<>();
        searchResults.add(new SupportDTO(1, 101, "확인용:"+data, "내용", "작성자1", "2024-05-21", "답변1", true));
        searchResults.add(new SupportDTO(3, 103, "제목3", "내용", "작성자3","2024-05-24", "답변2", true));
        searchResults.add(new SupportDTO(2, 102, "제목2", "내용", "작성자2","2024-05-23", "답변2", true));
        searchResults.add(new SupportDTO(4, 104, "제목4", "내용", "작성자4","2024-05-25", "답변2", true));
        searchResults.add(new SupportDTO(5, 105, "제목5", "내용", "작성자5","2024-05-26", "답변2", true));
        searchResults.add(new SupportDTO(6, 106, "제목6", "내용", "작성자6","2024-05-27", "답변2", true));
        searchResults.add(new SupportDTO(7, 107, "제목7", "내용", "작성자7","2024-05-28", "답변2", true));
        searchResults.add(new SupportDTO(8, 108, "제목8", "내용", "작성자8","2024-05-29", "답변2", true));
        searchResults.add(new SupportDTO(9, 109, "제목9", "내용", "작성자9","2024-05-30", "답변2", true));
        searchResults.add(new SupportDTO(10, 110, "제목10", "내용", "작성자10","2024-06-01", "답변2", true));
        searchResults.add(new SupportDTO(11, 111, "제목11", "내용", "작성자11","2024-06-01", "답변2", true));

        System.out.println(searchResults);
        return searchResults;
    }

    //문의하기 추가
    @PostMapping(value = "/supportAdd", produces = "application/json; charset=utf-8")
    public boolean supportAdd(@RequestBody(required = false) String data) {
        System.out.println("문의하기: " + data);
        // data에 해당하는 값 받아옴
        return true;
    }

}
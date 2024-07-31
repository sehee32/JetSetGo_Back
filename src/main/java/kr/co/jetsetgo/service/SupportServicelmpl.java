package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.SupportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import org.json.JSONObject;
//import java.sql.*;

@Service
public class SupportServicelmpl implements SupportService{

    @Autowired
    private SupportMapper supportMapper;

    public List<SupportDto> selectSupports(String data) {

        List<SupportDto> searchResults = new ArrayList<>();
        searchResults.add(new SupportDto(1, 1,101, "확인용:"+data, "내용", "작성자1", "2024-05-21", "테스트용 답변입니다.", true,"etc"));
        searchResults.add(new SupportDto(3, 1, 103, "제목3", "내용", "작성자3","2024-05-24", "", true,"etc"));
        searchResults.add(new SupportDto(2, 1, 102, "제목2", "내용", "작성자2","2024-05-23", "", false,"etc"));
        searchResults.add(new SupportDto(4, 1, 104, "제목4", "내용", "작성자4","2024-05-25", "", true,"etc"));
        searchResults.add(new SupportDto(5, 1, 105, "제목5", "내용", "작성자5","2024-05-26", "테스트용 답변입니다.", false,"etc"));
        searchResults.add(new SupportDto(6, 1, 106, "제목6", "내용", "작성자6","2024-05-27", "테스트용 답변입니다.", true,"etc"));
        searchResults.add(new SupportDto(7, 1, 107, "제목7", "내용", "작성자7","2024-05-28", "", false,"etc"));
        searchResults.add(new SupportDto(8, 1, 108, "제목8", "내용", "작성자8","2024-05-29", "", true,"etc"));
        searchResults.add(new SupportDto(9, 1, 109, "제목9", "내용", "작성자9","2024-05-30", "", true,"etc"));
        searchResults.add(new SupportDto(10, 1, 110, "제목10", "내용", "작성자10","2024-06-01", "", false,"etc"));
        searchResults.add(new SupportDto(11, 1, 111, "제목11", "내용", "작성자11","2024-06-01", "", true,"etc"));

        return searchResults;



    }

    public SupportDto selectSupport(String id){
        SupportDto result = supportMapper.findById(id);

        return result;
    }

}

package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.SupportMapper;
import kr.co.jetsetgo.model.TbSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//import org.json.JSONObject;
//import java.sql.*;

@Service
public class SupportServiceImpl implements SupportService{

    @Autowired
    private SupportMapper supportMapper;

    //1:1문의 검색
    public List<TbSupport> selectSupports(Map<String, String> SearchMap) {
        String search = SearchMap.get("search");
        String category = SearchMap.get("category");
        System.out.println("카테고리 : " + category);
        System.out.println("검색어 : " + search);

        List<TbSupport> results = null;

        if (search == ""){
            if(category.equals("total")){
                results = supportMapper.findAll();
            }else{
                results = supportMapper.findBycategory(category);
            }
        } else {
                results = supportMapper.findBySearchQuery(search);
        }
//        results.add(new TbSupport(1, 1,101, "확인용:"+data, "내용", "작성자1", "2024-05-21", "테스트용 답변입니다.", true,"etc"));
//        results.add(new TbSupport(3, 1, 103, "제목3", "내용", "작성자3","2024-05-24", "", true,"etc"));
//        results.add(new TbSupport(2, 1, 102, "제목2", "내용", "작성자2","2024-05-23", "", false,"etc"));
//        results.add(new TbSupport(4, 1, 104, "제목4", "내용", "작성자4","2024-05-25", "", true,"etc"));
//        results.add(new TbSupport(5, 1, 105, "제목5", "내용", "작성자5","2024-05-26", "테스트용 답변입니다.", true,"etc"));
//        results.add(new TbSupport(6, 1, 106, "제목6", "내용", "작성자6","2024-05-27", "테스트용 답변입니다.", true,"etc"));
//        results.add(new TbSupport(7, 1, 107, "제목7", "내용", "작성자7","2024-05-28", "", false,"etc"));
//        results.add(new TbSupport(8, 1, 108, "제목8", "내용", "작성자8","2024-05-29", "", true,"etc"));
//        results.add(new TbSupport(9, 1, 109, "제목9", "내용", "작성자9","2024-05-30", "", true,"etc"));
//        results.add(new TbSupport(10, 1, 110, "제목10", "내용", "작성자10","2024-06-01", "", false,"etc"));
//        results.add(new TbSupport(11, 1, 111, "제목11", "내용", "작성자11","2024-06-01", "", true,"etc"));

        return results;
    }

    //문의하기 상세 진입
    public TbSupport selectSupport(Map<String, String> supportIdMap){
        String supportId = supportIdMap.get("supportId");
        TbSupport result = supportMapper.findById(supportId);
//        System.out.println(supportId);
        System.out.println(result.getPublic_Status());
        return result;
    }

    //문의하기 상세 진입시 비밀번호 확인
    public boolean selectSupportPassword(Map<String, String> supportIdMap){
        String supportId = supportIdMap.get("supportId");
        String password = supportIdMap.get("password");

        System.out.println(supportId);
        System.out.println(password);

        // 비밀번호 암호화 할 것인지 문의 필요, 로그인한 사용자만 확인 가능으로 할 것인지도 문의 필요(우선 비번만 맞으면 진입 가능으로 구현)
        TbSupport support = supportMapper.findById(supportId);
        String writerPassword = supportMapper.findPasswordByWriterId(support.getWriter_Id()); //id로 작성자 비밀번호 검색
//        String writerPassword ="test"; //임시 비밀번호 입력

        if (writerPassword.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    //문의하기 추가
    public boolean insertSupport(TbSupport support){
        // 아이디 변경 가능한지 문의 필요
        long writerId = supportMapper.findIdByWriterName(support.getWriter_Name()); // 사용자 아이디로 id 검색
        support.setWriter_Id(writerId);

        System.out.println(support.getWriter_Id());
        System.out.println(support.getWriter_Name());
        System.out.println(support.getTitle());
        System.out.println(support.getDetail());
        System.out.println(support.getPublic_Status());
        System.out.println(support.getCategory());

        supportMapper.addSupport(support);

        return true;
    }

    //문의하기 수정
    public boolean updateSupport(TbSupport support){

        System.out.println(support.getSupport_Id());
        System.out.println(support.getTitle());
        System.out.println(support.getDetail());
        System.out.println(support.getPublic_Status());
        System.out.println(support.getCategory());
        System.out.println(support.getAnswer());

        supportMapper.editSupport(support);

        return true;
    }

    //문의하기 삭제
    public boolean deleteSupport(Map<String, String> supportIdMap){
        String supportId = supportIdMap.get("supportId");

        System.out.println(supportId);

        supportMapper.removeSupport(supportId);

        return true;
    }

}

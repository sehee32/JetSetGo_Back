package kr.co.jetsetgo.service;

import kr.co.jetsetgo.util.PortoneApiUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    /**
     * 본인인증
     * @param data
     * @return
     */
    public String SelectVerifiService(Map<?, ?> data) {

        System.out.println(data.toString());

//        PortoneApiUtil.getPortOneApi(data);



        return "success";
    }

    /**
     * 결제
     * @param data
     * @return
     */
    public String InsertPaymentsService(Map<?, ?> data) {

        System.out.println(data.toString());

//        PortoneApiUtil.getPortOneApi(data);



        return "success";
    }

    /**
     * 결제 요청 데이터 생성
     * 포트원 결제 금액 사전 등록 API 호출
     */
    public static Map<String, String> createOrderParam(Map<String, String> data) throws Exception {

        // 사전 등록 API
        Map<String, String> param = new HashMap<>();
        param.put("merchant_uid", data.get("merchant_uid"));
        param.put("amount", data.get("amount"));

        data.put("storeId", "imp12777257");
        data.put("currency", data.get("payment_Method"));

        PortoneApiUtil.getPortOneApi("/payments/prepare", "POST", param);

        return data;
    }
}

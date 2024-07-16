package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.RequestDto;
import kr.co.jetsetgo.util.PortoneApiUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
     */
    public Map<String, String> createOrderParam(RequestDto<?> requestDto) throws Exception {

        Map<String, String> res = (Map<String, String>) requestDto;

//        MembDto membDto = tbMembMapper.selectMembUser(requestDto.getSessionId());
//
//        res.put("prodSeq", EncryptUtil.sessionDecrypt(res.get("prodSeqEnc")));
//        res.put("prodOptnSeq", EncryptUtil.sessionDecrypt(res.get("prodOptnSeqEnc")));
//
//        res.put("amount",tbProdMapper.selectProdAmt(requestDto));
//        res.put("buyer_name", membDto.getMembNm());
//        res.put("buyer_email", EncryptUtil.decrypt(membDto.getMembEmail()));
//        res.put("buyer_tel", EncryptUtil.decrypt(membDto.getMembThpn()));

            res.put("storeId", "imp57355341");
            res.put("confirm_url","https://ed86-210-90-73-138.ngrok-free.app/api/purchase/PURCHASE03R");


        // TB_PAY 결제내역 등록
//        PayDto payDto = new PayDto();
//        payDto.setPayAmt(res.get("amount"));
//        payDto.setMembSeq(requestDto.getSessionId());
//        payDto.setCurr(res.get("currency"));
//        payDto.setRegDt(date);
//        payDto.setOriAmt(tbProdMapper.selectProdOriAmt(requestDto));
//
//        tbPayMapper.insertPay(payDto);
//
//        res.put("merchant_uid", date.substring(2,8)+"-"+String.format("%06d", commonMapper.getLastSeq()));
//        res.put("paySeqEnc", EncryptUtil.encrypt(String.valueOf(commonMapper.getLastSeq())));
//
//        // TB_PAY_ITEM 결제내역 등록
//        PayItemDto payItemDto = new PayItemDto();
//        payItemDto.setPaySeq(String.valueOf(commonMapper.getLastSeq()));
//        payItemDto.setProdSeq(res.get("prodSeq"));
//        payItemDto.setProdOptnSeq(res.get("prodOptnSeq"));
//        payItemDto.setPayItemAmt(res.get("amount"));
//        payItemDto.setPayItemCnt(String.valueOf(res.get("prodCnt")));
//        payItemDto.setRegMembSeq(requestDto.getSessionId());
//        payItemDto.setRegDt(date);
//
//        tbPayItemMapper.insertPayItem(payItemDto);

        // 사전 등록 API
        Map<String, String> param = new HashMap<>();
        param.put("merchant_uid", res.get("merchant_uid"));
        param.put("amount", res.get("amount"));
        PortoneApiUtil.getPortOneApi("/payments/prepare", "POST", param);

        return res;
    }
}

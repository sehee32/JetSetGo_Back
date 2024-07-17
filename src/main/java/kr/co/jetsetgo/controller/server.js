const express = require('express');
const bodyParser = require('body-parser');
const fetch = require('node-fetch');

const app = express();
const PORT = process.env.PORT || 8080;
const PORTONE_API_SECRET = process.env.PORTONE_API_SECRET;

// Body parser middleware 설정
app.use(bodyParser.json());

// POST /identity-verifications 요청 처리
app.post('/identity-verifications', async (req, res) => {
    // request의 body에서 identityVerificationId 추출
    const { identityVerificationId } = req.body;
    try {
        // 포트원 본인인증 내역 단건조회 API 호출
        const verificationResponse = await fetch(`https://api.portone.io/identity-verifications/${encodeURIComponent(identityVerificationId)}`, {
            method: 'GET',
            headers: {
                Authorization: `PortOne ${PORTONE_API_SECRET}`,
                'Content-Type': 'application/json'
            }
        });

        if (!verificationResponse.ok) {
            throw new Error(`verificationResponse: ${await verificationResponse.json()}`);
        }

        const identityVerification = await verificationResponse.json();

        if (identityVerification.status !== 'VERIFIED') {
            // 인증 실패 처리
            res.status(400).json({ error: '본인 인증이 실패했습니다.' });
        } else {
            // 인증 성공 처리
            res.status(200).json({ message: '본인 인증이 완료되었습니다.' });
        }
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: '서버 오류가 발생했습니다.' });
    }
});


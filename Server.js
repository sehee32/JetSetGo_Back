const dotenv = require('dotenv');
dotenv.config();

const express = require('express');
const bodyParser = require('body-parser');
const mariadb = require('mariadb');
const bcrypt = require('bcrypt');
const cors = require('cors');
const axios = require('axios');

const app = express();
const PORT = process.env.PORT || 8080;
const imp_key = process.env.imp_key;
const imp_secret = process.env.imp_secret;

// MariaDB 연결 설정
const pool = mariadb.createPool({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    port: 3306,
    connectionLimit: 10
});

app.use(bodyParser.json());
app.use(cors());

// 회원가입 API
app.post('/api/signup', async (req, res) => {
    const { name, username, password, birthdate, phoneNumber, agreeTerms } = req.body;

    try {
        const hashedPassword = await bcrypt.hash(password, 10);
        const conn = await pool.getConnection();
        console.log('데이터베이스 연결 성공');

        const query = `INSERT INTO MEMBERS (NAME, USERNAME, PASSWORD, BIRTHDATE, PHONENUMBER, AGREETERMS) VALUES (?, ?, ?, ?, ?, ?)`;
        const values = [name, username, password, birthdate, phoneNumber, agreeTerms ? 1 : 0];

        await conn.query(query, values);
        conn.release();

        res.status(200).send('회원가입 성공');
    } catch (err) {
        console.error('회원가입 오류:', err);
        res.status(500).send('회원가입 실패');
    }
});

// "/certifications"에 대한 POST 요청을 처리하는 controller
app.post("/certifications", async (request, response) => {
    const { imp_uid } = request.body;
    try {
        const getToken = await axios({
            url: "https://api.iamport.kr/users/getToken",
            method: "post",
            headers: { "Content-Type": "application/json" },
            data: {
                imp_key: imp_key,
                imp_secret: imp_secret
            },
        });
        const { access_token } = getToken.data.response;

        const getCertifications = await axios({
            url: `https://api.iamport.kr/certifications/${imp_uid}`,
            method: "get",
            headers: { Authorization: access_token },
        });
        const certificationsInfo = getCertifications.data.response;
        const { name, gender, birth, unique_key, phone } = certificationsInfo;

        response.status(200).json({
            name,
            gender,
            birth,
            unique_key,
            phone,
        });

    } catch (e) {
        console.error(e);
        response.status(500).json({ error: '서버 오류가 발생했습니다.'});
    }
});

app.listen(PORT, () => {
    console.log(`서버 작동 포트 ${PORT}`);
});

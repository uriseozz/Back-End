# 🎊항해99 6주차 미니프로젝트 <9조>🎊
📆2021.12.06 ~ 2021.12.11
</br>

### 개팔자가 상팔자🐕🐈
<p>우리 반려동물들의 꿀템을 공유해보세요!</p>
----link----
</br>
</br>

### MEMBER

<p>프론트엔드</p>
<p>정상일</p>
<p>이지연</p>
<p>김형래</p>

<p>백엔드</p>
<p>이현범</p>
<p>이한울</p>
<p></p>

</br>
</br>

---
</br>

## 🎞프로젝트 시연 영상🎞
----영상----
</br>
</br>

## 🛠TECH STACK🛠
<img height="40" src="https://img.shields.io/badge/github-181717?style=flat&logo=github&logoColor=white"></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/MySQL-005C84?style=flat&logo=mysql&logoColor=white"></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/Springboot-47?style=flat&logo=Springboot&logoColor=white"/></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white"/></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/JWT-000000?style=flat&logo=JSON%20web%20tokens&logoColor=white"></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=Swagger&logoColor=white"></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/gradle-02303A?style=flat&logo=gradle&logoColor=white"></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/Amazon_AWS-FF9900?style=flat&logo=amazonaws&logoColor=white"></a>&nbsp;
<img height="40" src="https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white"></a>&nbsp;
</br>
</br>

## ⚙주요 기능⚙
❤**로그인/회원가입**
  - 아이디 중복확인 기능
  - 회원가입시 아이디, Email, 비밀번호 유효성 검사
  </br>
  
🧡**메인페이지**
  * 로그인한 유저이름 출력
  * 게시글 목록 출력
    - 키워드 클릭 시 해당 카테고리의 출력
    - 카드별 이미지, Title, 작성자, 조회수 표시
  * 카테고리 별 게시글 출력(필터)
    - 키워드 클릭 시 해당 카테고리의 출력
  * 상세페이지 이동
    - 카드 클릭 시 해당 상세페이지 이동
  </br>
  
💛**게시글 작성 페이지**
  * 사진 업로드
  * 빈칸 메세지
  </br>
  
💚**상세페이지**
  * 게시글 상세내용 출력
  * 댓글기능
    - 로그인한 사용자만 댓글 작성가능
</br>

💙**마이페이지**
  * 로그인한 사용자의 게시물 확인 가능
</br>
</br>

## 📅Entity 다이어그램
![image](https://user-images.githubusercontent.com/86363774/145669436-86147f96-db82-425f-90da-4e547e81d086.png)
</br>
</br>

## 🖥와이어프레임
</br>
<details>
    <summary>
        와이어프레임 
    </summary>
  
    <div markcown="1">

    </div>
</details>

**<메인 페이지>**
![image](https://user-images.githubusercontent.com/86363774/145669516-3b976230-9dfa-4d5a-9607-2e46fd99d928.png)
**<게시글 작성 페이지>**
![image](https://user-images.githubusercontent.com/86363774/145669517-71ab5f8b-a987-47fc-8042-80d39ef6dbe7.png)
**<로그인 페이지>**
![image](https://user-images.githubusercontent.com/86363774/145669519-0a9454c6-7eb2-41c5-a788-cf41b74727c1.png)
**<회원가입 페이지>**
![image](https://user-images.githubusercontent.com/86363774/145669523-d206b31e-d38d-48b0-ba5b-c6dd9f494a01.png)
**<상세페이지>**
![image](https://user-images.githubusercontent.com/86363774/145669525-d16e7d28-2889-461d-9772-2fb90831663c.png)
</br>
</br>

## 📜API TABLE
</br>
      
**회원가입**
| 기능 | Method | URL | Request | response |
| --- | --- | --- | --- | --- |
| 회원가입 | POST | /api/user/signup | {"username": "username", "password":"password", "passwordCheck":"password", "email": "email"} | 회원가입 성공시: Success Sign up |
| 아이디 중복확인 | POST | /api/user/checkid | {"username":"beomin123"} | 아이디 존재시: Existed Id, 아이디 없을시: You can use this Id |
| 이메일 중복확인 | POST | /api/user/checkemail | {"email":"email@naver.com"} | 이메일 존재시: Existed Email, 이메일 없을시: You can use this Email |
</br>
  
**로그인**
| 기능 | Method | URL | Request | response |
| --- | --- | --- | --- | --- |
| 로그인 | POST | /api/user/login | {"username": "username", "password": "password"} | msg :'로그인 성공' 성공시: [{"username": "username"}, {"token": "해쉬값"}] / 실패시: msg:'로그인 실패' |
  
**메인페이지**
| 기능 | Method | URL | Request | response |
| --- | --- | --- | --- | --- |
| 카테고리에 
따라 분류된 전체 게시글 
목록조회 | GET | /api/category/1 |  |  |
| 전체 게시글 목록 보이기 | GET | /api/user/main |  | {{"id": 1, "username":"username", "title":"title", "img":"src", "view" : 1, "categoryname":"dog"}} |
  
![image](https://user-images.githubusercontent.com/86363774/145669813-5e22eccd-eb4a-4c2d-a8cb-f561b9478f98.png)
![image](https://user-images.githubusercontent.com/86363774/145669824-218e9e81-6162-4a1c-b10a-566f0d6db688.png)
![image](https://user-images.githubusercontent.com/86363774/145669841-a2164ca2-5002-4a5b-b2bb-e8c29e77c7b3.png)

</br>
</br>

## ✒Trouble Shooting
<details>
    <summary>
        요약
    </summary>
    <div markcown="1">
        숨김페이지
    </div>
</details>
</br>
</br>

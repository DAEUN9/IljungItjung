import React, { useState } from "react";
import { postRegister } from "@api/register";
import { useNavigate } from "react-router-dom";
import styles from "@styles/Register/Register.module.scss";
import iljung from "@assets/iljung.png";
import logo from "@assets/logo.png";
import CustomButton from "@components/common/CustomButton";
import TextField from "@mui/material/TextField";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [text, setText] = useState("");
  // 닉네임 체크 후 출력할 문자
  const [check, setcheck] = useState("");
  // 닉네임 중복검사 버튼 활성화 상태
  const [ableName, setAbleName] = useState(true);
  // 시작하기 버튼 상태 - 중복체크 api 아직 미구현으로 false처리
  const [able, setAble] = useState(false);

  // 입력값 변화할 때 유효성 검사 하고 값 담고 중복검사 버튼 활성화
  const regex = /^[가-힣|a-z|A-Z|]{2,10}$/;
  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (regex.test(event.target.value)) {
      setName(event.target.value);
      setcheck("");
      setAbleName(false);
    } else if (event.target.value === "") {
      setName(event.target.value);
      setcheck("");
    } else {
      setName(event.target.value);
      setcheck("공백을 제외한 한글과 영어만 입력해주세요.");
      setAbleName(true);
    }
  };

  const handleInput2 = (event: React.ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value);
  };

  // 중복 확인 버튼 누르면 중복검사 API로 확인하고 결과값 출력
  const checkOverlap = () => {
    setcheck("사용 가능한 닉네임 입니다.");
    // fetch(`http://k7d106.p.ssafy.io/api/nicknames/${name}`).then((res) => {
    //   console.log(res.status);
    //   if (res.status === 200) {
    //     시작하기 버튼 활성화
    //     setAble(false)
    //   } else res.status === 409;
    //   {
    //     setcheck("이미 등록된 닉네임 입니다.");
    //     시작하기 버튼 비활성화
    //     setAble(true)
    //   }
    // });
  };

  // 시작하기 버튼 누르면
  const onSubmit = () => {
    // 회원가입 API 요청
    postRegister(name, text, () => {
      // 결과에 따라서 페이지 이동. 가이드 페이지 출력
      navigate(`/calender/my`);
    });
  };

  return (
    <div className={styles["register-page"]}>
      <div className={styles["left"]}>
        <div className={styles["picturebox"]}>
          <img src={logo} alt="로고" className={styles["logo"]} />
          <img src={iljung} alt="일정이" className={styles["iljung"]} />
        </div>
      </div>
      <div className={styles["right"]}>
        <div className={styles["content"]}>
          <h1>회원가입</h1>
          <p>최소한의 정보를 입력하고 빠르게 시작해보세요</p>
          <p>각 정보는 추후에 수정할 수 있습니다.</p>
          <br />
          <h2>닉네임</h2>
          <p>2~10글자의 한글, 영어 대/소문자</p>
          <div className={styles["nicknamebox"]}>
            <TextField
              className={styles["nickname"]}
              onChange={handleInput}
              placeholder="닉네임을 입력해주세요"
              inputProps={{ maxLength: 10, minLength: 2 }}
              focused={check === "사용 가능한 닉네임 입니다." ? true : false}
              error={
                check === "공백을 제외한 한글과 영어만 입력해주세요."
                  ? true
                  : false
              }
              color={
                check === "사용 가능한 닉네임 입니다." ? "success" : "info"
              }
              label={check}
              value={name}
            />
            <CustomButton
              variant="outlined"
              onClick={checkOverlap}
              disabled={ableName}
            >
              중복 확인
            </CustomButton>
          </div>
          <br />
          <h2>한 줄 소개</h2>
          <TextField
            id="oneline"
            value={text}
            onChange={handleInput2}
            type="text"
            placeholder="한 줄 소개를 입력해주세요"
            inputProps={{ maxLength: 50 }}
            className={styles["oneline"]}
          />
          <span className={styles["count"]}>{text.length}/50</span>
          <div className={styles["startbox"]}>
            <CustomButton
              className={styles["startbtn"]}
              size="large"
              disabled={able}
              onClick={onSubmit}
            >
              시작하기
            </CustomButton>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;

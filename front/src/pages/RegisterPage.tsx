import { useState } from "react";
import { useNavigate } from "react-router-dom";
import TextField from "@mui/material/TextField";
import { ThemeProvider } from "@mui/material";

import { getOverlap, postPhone, postRegister, putNum } from "@api/register";
import styles from "@styles/Register/Register.module.scss";
import iljung from "@assets/iljung.png";
import logo from "@assets/logo.png";
import CustomButton from "@components/common/CustomButton";
import { registerCategory } from "@api/setting";
import theme from "@components/common/theme";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  // 닉네임 체크 후 출력할 문자
  const [check, setCheck] = useState("");
  // 닉네임 중복검사 버튼 활성화 상태
  const [ableName, setAbleName] = useState(true);
  // 닉네임 박스 포커스 설정
  const [focus, setFocus] = useState(false);
  // 휴대폰 번호, 번호 확인문구, 인증번호, 인증번호 확인문구
  const [phone, setPhone] = useState("");
  const [checkPhone, setCheckPhone] = useState("");
  const [ableInputPhone, setAbleInputPhone] = useState(false);
  const [ablePhone, setAblePhone] = useState(true);
  const [authNum, setNum] = useState("");
  const [checkNum, setCheckNum] = useState("");
  const [ableInputNum, setAbleInputNum] = useState(true);
  const [ableNum, setAbleNum] = useState(true);
  // 한 줄 소개
  const [intro, setIntro] = useState("");

  // 입력값 변화할 때 유효성 검사 하고 값 담고 중복검사 버튼 활성화
  const regex = /^[가-힣|a-z|A-Z|]{2,10}$/;
  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (regex.test(event.target.value)) {
      setName(event.target.value);
      setCheck("");
      setAbleName(false);
    } else if (event.target.value === "") {
      setName(event.target.value);
      setCheck("");
    } else {
      setName(event.target.value);
      setCheck("공백을 제외한 한글과 영어만 입력해주세요.");
      setAbleName(true);
      setFocus(true);
    }
  };
  const handleFocus = (event: React.FocusEvent<HTMLInputElement>) => {
    setFocus(true);
  };
  const handleBlur = (event: React.FocusEvent<HTMLInputElement>) => {
    if (name !== "") {
      setFocus(true);
    } else {
      setFocus(false);
    }
  };

  const handleIntro = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIntro(event.target.value);
  };

  // 중복 확인 버튼 누르면 중복검사 API로 확인하고 결과값 출력
  const checkOverlap = () => {
    getOverlap(
      name,
      (res: object) => {
        setCheck("사용 가능한 닉네임 입니다.");
        setAbleName(true);
        setFocus(true);
      },
      (err: any) => {
        setCheck("이미 등록된 닉네임 입니다.");
        setAbleName(true);
        setFocus(true);
      }
    );
  };
  const handleKeyDown = (event: React.KeyboardEvent<HTMLElement>) => {
    if (event.key === "Enter") {
      getOverlap(
        name,
        (res: object) => {
          setCheck("사용 가능한 닉네임 입니다.");
          setAbleName(true);
          setFocus(true);
        },
        (err: any) => {
          setCheck("이미 등록된 닉네임 입니다.");
          setAbleName(true);
          setFocus(true);
        }
      );
    }
  };

  // 입력값 변화할 때 휴대폰 번호 번호 실시간으로 담고 유효성 검사 후 휴대폰 인증 버튼 활성화
  const regexPhone = /^01([0|1|6|7|8|9])([0-9]{7,8})$/;
  const regexBar = /-/;
  const handlePhone = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (regexPhone.test(event.target.value)) {
      setPhone(event.target.value);
      setCheckPhone("");
      setAblePhone(false);
    } else if (event.target.value === "") {
      setPhone(event.target.value);
      setCheckPhone("");
    } else if (regexBar.test(event.target.value)) {
      setPhone(event.target.value);
      setCheckPhone("'-'를 제외한 숫자만 입력해주세요.");
      setAblePhone(true);
    } else {
      setPhone(event.target.value);
      setCheckPhone("정확한 휴대폰 번호를 입력해주세요.");
      setAblePhone(true);
    }
  };

  const handleGetNum = () => {
    // 휴대폰 번호 입력 비활성화
    setAbleInputPhone(true);
    // 인증번호 요청 버튼 비활성화 - 여러번 안누르도록
    setAblePhone(true);
    // 인증번호 요청 api
    postPhone(
      phone,
      (res: object) => {
        setCheckPhone("인증번호가 발송되었습니다.");
        // 인증번호 입력 칸 활성화
        setAbleInputNum(false);
      },
      (err: any) => {
        setCheckPhone("이미 등록된 휴대폰 번호 입니다.");
        // 휴대폰 번호 입력 활성화
        setAbleInputPhone(false);
        // 인증번호 입력 칸 비활성화 처리
        setAbleInputNum(true);
      }
    );
  };

  // 인증번호 입력값 변화할 때 번호 실시간으로 담고 유효성 검사 후 번호 인증 버튼 활성화
  const regexNum = /[0-9|a-z]{6}/;
  const handleNum = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (regexNum.test(event.target.value)) {
      setNum(event.target.value);
      setCheckNum("");
      setAbleNum(false);
    } else if (event.target.value === "") {
      setNum(event.target.value);
      setCheckNum("");
    } else {
      setNum(event.target.value);
      setCheckNum("ex)123abc");
      setAbleNum(true);
    }
  };

  // 인증번호 확인
  const handleCheckNum = () => {
    // 인증 번호 입력 비활성화
    setAbleInputNum(true);
    // 인증번호 확인 버튼 비활성화 - 여러번 안누르도록
    setAbleNum(true);
    putNum(
      phone,
      authNum,
      (res: object) => {
        setCheckNum("인증 완료!");
      },
      (err: any) => {
        setCheckNum("인증번호 오류");
        // 인증번호 입력 활성화
        setAbleInputNum(false);
        // 인증번호 확인 버튼 활성화
        setAbleNum(false);
      }
    );
  };

  // 시작하기 버튼 누르면
  const onSubmit = () => {
    // 회원가입 API 요청
    postRegister(name, intro, phone, () => {
      const category = [
        { categoryName: "기본", color: "#D5EAEF", time: "0100" },
      ];
      registerCategory(category, () => {});
      // 회원가입 후 다시 로그인페이지로 이동
      navigate(`/`);
    });
  };

  return (
    <div className={styles["register-page"]}>
      <div className={styles.left}>
        <div className={styles.picturebox}>
          <img src={logo} alt="로고" className={styles.logo} />
          <img src={iljung} alt="일정이" className={styles.iljung} />
        </div>
      </div>
      <div className={styles.right}>
        <div className={styles.content}>
          <ThemeProvider theme={theme}>
            <div className={styles.top}>
              <h1>회원가입</h1>
              <p>최소한의 정보를 입력하고 빠르게 시작해보세요</p>
              <p>각 정보는 추후에 수정할 수 있습니다.</p>
            </div>
            <div className={styles["nickname-wrapper"]}>
              <h2>닉네임</h2>
              <p>2~10글자의 한글, 영어 대/소문자</p>
              <div className={styles.nicknamebox}>
                <TextField
                  className={styles.nickname}
                  onChange={handleInput}
                  placeholder="닉네임을 입력해주세요"
                  size="small"
                  fullWidth
                  inputProps={{ maxLength: 10, minLength: 2 }}
                  onFocus={handleFocus}
                  onBlur={handleBlur}
                  focused={focus}
                  error={
                    check === "공백을 제외한 한글과 영어만 입력해주세요." ||
                    check === "이미 등록된 닉네임 입니다."
                      ? true
                      : false
                  }
                  color={
                    check === "사용 가능한 닉네임 입니다."
                      ? "success"
                      : "primary"
                  }
                  label={check}
                  value={name}
                  onKeyDown={handleKeyDown}
                />
                <CustomButton
                  variant="outlined"
                  onClick={checkOverlap}
                  disabled={ableName}
                >
                  중복 확인
                </CustomButton>
              </div>
            </div>
            <div className={styles["phone-wrapper"]}>
              <h2>휴대폰 번호 인증</h2>
              <p>
                문자로 예약 관련 알림을 드리기 위해 휴대폰 번호 인증을
                진행해주세요.
              </p>
              <div className={styles.phonecheck}>
                <div className={styles.numgetbox}>
                  <TextField
                    className={styles.numget}
                    size="small"
                    placeholder="ex)01012345678"
                    onChange={handlePhone}
                    value={phone}
                    disabled={ableInputPhone}
                    inputProps={{ maxLength: 11, minLength: 10 }}
                    label={checkPhone}
                    error={
                      checkPhone === "'-'를 제외한 숫자만 입력해주세요." ||
                      checkPhone === "정확한 휴대폰 번호를 입력해주세요." ||
                      checkPhone === "이미 등록된 휴대폰 번호 입니다."
                        ? true
                        : false
                    }
                  />
                  <CustomButton
                    variant="outlined"
                    disabled={ablePhone}
                    onClick={handleGetNum}
                  >
                    인증번호 요청
                  </CustomButton>
                </div>
                <div className={styles.numcheckbox}>
                  <TextField
                    className={styles.numcheck}
                    size="small"
                    placeholder="ex)123abc"
                    onChange={handleNum}
                    value={authNum}
                    disabled={ableInputNum}
                    inputProps={{ maxLength: 6, minLength: 6 }}
                    label={checkNum}
                    error={
                      checkNum === "인증번호 오류" || checkNum === "ex)123abc"
                        ? true
                        : false
                    }
                  />
                  <CustomButton
                    variant="outlined"
                    disabled={ableNum}
                    onClick={handleCheckNum}
                  >
                    인증번호 확인
                  </CustomButton>
                </div>
              </div>
            </div>
            <div className={styles["intro-wrapper"]}>
              <h2>한줄 소개</h2>
              <TextField
                id="oneline"
                size="small"
                value={intro}
                onChange={handleIntro}
                type="text"
                placeholder="한줄 소개를 입력해주세요"
                inputProps={{ maxLength: 50 }}
                className={styles.oneline}
              />
              <span className={styles.count}>{intro.length}/50</span>
              <div className={styles.startbox}>
                <CustomButton
                  className={styles.startbtn}
                  size="large"
                  disabled={
                    !(
                      check === "사용 가능한 닉네임 입니다." &&
                      checkNum === "인증 완료!"
                    )
                  }
                  onClick={onSubmit}
                >
                  시작하기
                </CustomButton>
              </div>
            </div>
          </ThemeProvider>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;

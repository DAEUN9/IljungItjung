import styles from "@styles/Register/Register.module.scss";
import iljung from "@assets/iljung.png";
import logo from "@assets/logo.png";
import CustomButton from "@components/common/CustomButton";
import { useState } from "react";


const RegisterPage = () => {
  const [text, setText] = useState("");
  const count = (event : React.ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value)
  }
  return (
    <div className={styles["register-page"]}>
      <div className={styles["left"]}>
        <img src={logo} alt="로고" className={styles["logo"]} />
        <img src={iljung} alt="일정이" className={styles["iljung"]} />
      </div>
      <div className={styles["right"]}>
        <div className={styles["content"]}>
          <div className={styles["text"]}>
            <h1>회원가입</h1>
            <p>최소한의 정보를 입력하고 빠르게 시작해보세요</p>
            <p>각 정보는 추후에 수정할 수 있습니다.</p>
            <br/>
            <h2>닉네임</h2>
            <p>2~10글자의 한글, 영어 대/소문자</p>
            <input type="text" placeholder="닉네임을 입력해주세요" maxLength={10} className={styles["nickname"]}></input>
            <CustomButton className={styles["overlap"]}>중복 확인</CustomButton>
            <br/>
            <h2>한 줄 소개</h2>
            <input id="oneline" value={text} onChange={count} type="text" placeholder="한 줄 소개를 입력해주세요" maxLength={50} className={styles["oneline"]} />
            <span className={styles["count"]}>{text.length}/50</span>
          </div>
            <CustomButton size="large">시작하기</CustomButton>
        </div>
      </div>
    </div>
  );
}

export default RegisterPage;
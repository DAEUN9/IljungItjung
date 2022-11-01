import CustomButton from "@components/common/CustomButton";
import CustomCarousel from "@components/Login/CustomCarousel";
import styles from "@styles/Login/Login.module.scss";
import iljung from "@assets/iljung.png";
import logo from "@assets/logo.png";
import { getKakaoLogin } from "@api/login";

const LoginPage = () => {
  return (
    <div className={styles["login-page"]}>
      <div className={styles["left"]}>
        <img src={logo} alt="로고" className={styles["logo"]} />
        <img src={iljung} alt="일정이" className={styles["iljung"]} />
      </div>
      <div className={styles["right"]}>
        <div className={styles["welcome"]}>
          일정있정에 오신 것을 환영합니다.
        </div>
        <a href="/api/login/kakao">임시 카카오 로그인</a>
        <CustomCarousel />
        <CustomButton
          size="large"
          color="secondary"
          className={styles["login"]}
          onClick={getKakaoLogin}
        >
          임시 카카오 로그인
        </CustomButton>
      </div>
    </div>
  );
};

export default LoginPage;

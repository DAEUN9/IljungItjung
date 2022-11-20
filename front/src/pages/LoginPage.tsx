import CustomCarousel from "@components/Login/CustomCarousel";
import styles from "@styles/Login/Login.module.scss";
import iljung from "@assets/iljung.png";
import logo from "@assets/logo.png";
import login from "@assets/kakao_login_large_wide.png";

const LoginPage = () => {
  return (
    <div className={styles["login-page"]}>
      <div className={styles.left}>
        <div className={styles.picturebox}>
          <img src={logo} alt="로고" className={styles.logo} />
          <img src={iljung} alt="일정이" className={styles.iljung} />
        </div>
      </div>
      <div className={styles.right}>
        <div className={styles.welcome}>일정있정에 오신 것을 환영합니다.</div>
        <CustomCarousel />
        <a
          className={styles.kakao}
          href={"https://k7d106.p.ssafy.io/api/login/kakao"}
        >
          <img src={login} alt="카카오로그인" />
        </a>
      </div>
    </div>
  );
};

export default LoginPage;

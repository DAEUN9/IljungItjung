import TextField from "@mui/material/TextField";
import CustomButton from "@components/common/CustomButton";
import Sidebar from "@components/common/Sidebar";
import styles from "@styles/ProfileEdit/ProfileEdit.module.scss";
import defaultImg from "@assets/defaultImg.png";
import React, { useState } from "react";
import CustomModal from "@components/common/CustomModal";
import { getMyInfo, putProfile } from "@api/profile";
import { delWithdraw } from "@api/register";
import { useNavigate } from "react-router-dom";
import { SearchState } from "@components/types/types";

const ProfileEditPage = () => {
  // 세션 ID 확인 하고 없으면 로그인 화면으로 내보냄
  // onLoad 이용해서 페이지 로드될 때 작동하도록 설정
  // 잘못된 접근임을 알리는 alert 출력후 이동되도록
  const cookieRegex = /JSESSIONID/;
  const checkLogin = () => {
    if (cookieRegex.test(document.cookie)) {
      console.log("ㅇㅋ 있으셈");
    } else {
      console.log("너 나가");
      alert("비정상적인 접근입니다. 로그인 페이지로 이동합니다.");
      navigate(`/`);
    }
  };

  // 내 데이터 불러오는 요청
  interface SearchApiData {
    status: string;
    data: {
      users: SearchState[];
    };
  }
  const loadMyInfo = () => {
    getMyInfo((res: SearchApiData) => {
      console.log(res.data);
    });
  };

  // 불러온 데이터와 저장된 데이터 비교하여 차이가 없으면 저장하기 버튼 비활성화
  const [able, setAble] = useState(false);

  const navigate = useNavigate();
  const [name, setName] = useState("");
  // 닉네임 체크 후 출력할 문자
  const [check, setcheck] = useState("");
  // 닉네임 중복검사 버튼 활성화 상태
  const [ableName, setAbleName] = useState(true);
  const [intro, setIntro] = useState("");
  const [description, setDescription] = useState("");

  // 중복 확인 버튼 누르면 중복검사 API로 확인하고 결과값 출력
  const checkOverlap = () => {
    setcheck("사용 가능한 닉네임 입니다.");
    // fetch(`http://k7d106.p.ssafy.io/api/nicknames/${name}`).then((res) => {
    //   console.log(res.status);
    //   if (res.status === 200) {
    //     overlap = "사용 가능한 닉네임 입니다.";
    //     // 시작하기 버튼 활성화
    //     // setAble(false)
    //   } else res.status === 409;
    //   {
    //     overlap = "이미 등록된 닉네임 입니다.";
    //     // 시작하기 버튼 비활성화
    //     // setAble(true)
    //   }
    // });
  };

  // 닉네임
  const regex = /^[가-힣|a-z|A-Z|]{2,10}$/;
  const handleName = (event: React.ChangeEvent<HTMLInputElement>) => {
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

  // 한 줄 소개
  const handleIntro = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIntro(event.target.value);
  };

  // 설명
  const handleDescription = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setDescription(event.target.value);
  };

  // 내용 변경 후 저장 Modal
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  // 확인 버튼 누를 시
  const handleConfirm = () =>
    putProfile(name, intro, description, () => {
      // 성공적으로 저장되었습니다 출력하거나 다시 profile 페이지로 새로고침(미구현)
    });

  // 회원 탈퇴 모달
  const [openWithdrow, setOpenWithdrow] = React.useState(false);
  const handleOpenWithdrow = () => setOpenWithdrow(true);
  const handleConfirmWithdrow = () => {
    // 회원 탈퇴 요청
    delWithdraw(() => {
      // 탈퇴 후 로그인 화면으로 이동
      alert("회원 탈퇴 되었습니다. 로그인 페이지로 이동합니다.");
      navigate(`/`);
    });
  };

  return (
    <div
      className={styles["profile-edit-page"]}
      // onLoad={checkLogin}
      onLoad={loadMyInfo}
    >
      <Sidebar />
      <CustomModal
        open={open}
        setOpen={setOpen}
        handleConfirm={handleConfirm}
        children={
          <div className={styles["modal"]}>
            <div className={styles["picturebox"]}>
              <img src={defaultImg} className={styles["picture"]} />
            </div>
            <div className={styles["textbox"]}>
              <p>회원 정보가 수정됩니다.</p>
              <p>계속 하시겠습니까?</p>
            </div>
          </div>
        }
      />
      <CustomModal
        open={openWithdrow}
        setOpen={setOpenWithdrow}
        handleConfirm={handleConfirmWithdrow}
        children={
          <div className={styles["modal"]}>
            <div className={styles["picturebox"]}>
              <img src={defaultImg} className={styles["picture"]} />
            </div>
            <div className={styles["textbox"]}>
              <p>정말 탈퇴하시겠습니까?</p>
            </div>
          </div>
        }
      />
      <div className={styles["left"]}>
        <div>
          <div className={styles["pictureBox"]}>
            <img
              className={styles["picture"]}
              src={defaultImg}
              alt="임시사진"
            />
          </div>
          <h1 className={styles["name"]}>임시 이름</h1>
          <CustomButton className={styles["savebtn"]} onClick={handleOpen}>
            저장하기
          </CustomButton>
        </div>
      </div>
      <div className={styles["right"]}>
        <div className={styles["content"]}>
          <h2>닉네임</h2>
          <div className={styles["nicknamebox"]}>
            <TextField
              className={styles["nickname"]}
              onChange={handleName}
              placeholder="2~10글자의 한글, 영어 대/소문자"
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
            className={styles["intro"]}
            value={intro}
            onChange={handleIntro}
            multiline
            minRows={2}
            placeholder="한 줄 소개를 입력해주세요"
            inputProps={{ maxLength: 50 }}
          />
          <span className={styles["count"]}>{intro.length}/50</span>
          <br />
          <h2>설명</h2>
          <TextField
            className={styles["description"]}
            multiline
            minRows={9}
            value={description}
            onChange={handleDescription}
            placeholder="상세 설명을 입력해주세요"
            inputProps={{ maxLength: 300 }}
          />
          <span className={styles["count"]}>{description.length}/300</span>
          <br />
          <br />
          <div className={styles["withdrow"]} onClick={handleOpenWithdrow}>
            회원 탈퇴
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfileEditPage;

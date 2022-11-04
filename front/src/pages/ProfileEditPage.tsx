import CustomButton from "@components/common/CustomButton";
import Sidebar from "@components/common/Sidebar";
import styles from "@styles/ProfileEdit/ProfileEdit.module.scss";
import defaultImg from "@assets/defaultImg.png";
import PictureEdit from "@assets/PictureEdit.png";
import PictureEdit2 from "@assets/PictureEdit2.png";
import React, { useState } from "react";
import CustomModal from "@components/common/CustomModal";
import { getMyInfo, putProfile } from "@api/profile";

const ProfileEditPage = () => {
  // 내 데이터 불러오는 요청 - 아직 api 미구현
  // getMyInfo;
  // 불러온 데이터와 저장된 데이터 비교하여 차이가 없으면 저장하기 버튼 비활성화
  // const [able, setAble] = useState(false);

  const [name, setName] = useState("");
  const [intro, setIntro] = useState("");
  const [description, setDescription] = useState("");

  var overlap = "";
  // 중복 확인 버튼 누르면 중복검사 API로 확인하고 결과값 출력
  const checkOverlap = () => {
    fetch(`http://k7d106.p.ssafy.io/api/nicknames/${name}`).then((res) => {
      console.log(res.status);
      if (res.status === 200) {
        overlap = "사용 가능한 닉네임 입니다.";
        // 시작하기 버튼 활성화
        // setAble(false)
      } else res.status === 409;
      {
        overlap = "이미 등록된 닉네임 입니다.";
        // 시작하기 버튼 비활성화
        // setAble(true)
      }
    });
  };

  // 닉네임
  const inputName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  // 한 줄 소개
  const inputIntro = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIntro(event.target.value);
  };

  // 설명
  const inputDescription = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
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
    console.log("탈퇴");
  };
  return (
    <div className={styles["profile-edit-page"]}>
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
          <div className={styles["nicknameinput"]}>
            <input
              value={name}
              onChange={inputName}
              type="text"
              placeholder="2~10글자의 한글, 영어 대/소문자"
              maxLength={10}
              className={styles["nickname"]}
            ></input>
            <CustomButton variant="outlined" onClick={checkOverlap}>
              중복 확인
            </CustomButton>
          </div>
          <span>{overlap}</span>
          <br />
          <br />
          <h2>한 줄 소개</h2>
          <input
            className={styles["intro"]}
            value={intro}
            onChange={inputIntro}
            type="text"
            placeholder="한 줄 소개를 입력해주세요"
            maxLength={50}
          />
          <span className={styles["count"]}>{intro.length}/50</span>
          <br />
          <br />
          <h2>설명</h2>
          <textarea
            className={styles["description"]}
            value={description}
            onChange={inputDescription}
            placeholder="상세 설명을 입력해주세요"
            maxLength={300}
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

import CustomButton from "@components/common/CustomButton";
import Sidebar from "@components/common/Sidebar";
import styles from "@styles/ProfileEdit/ProfileEdit.module.scss";
import defaultImg from "@assets/defaultImg.png";
import PictureEdit from "@assets/PictureEdit.png";
import React, { useState } from "react";
import CustomModal from "@components/common/CustomModal";

import { ref, getDownloadURL, uploadBytes, uploadBytesResumable } from "firebase/storage"
import { storage } from "src/firebase";

const ProfileEditPage = () => {

  // 한 줄 소개
  const [text, setText] = useState("");
  const count = (event : React.ChangeEvent<HTMLInputElement>) => {
    setText(event.target.value)
  }

  // 설명
  const [explain, setexplain] = useState("");
  const count2 = (event : React.ChangeEvent<HTMLTextAreaElement>) => {
    setexplain(event.target.value)
  }

  // 변경 후 저장 Modal
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleConfirm = () => // API 저장 요청 
  void(0)

  // 프로필 사진 업로드 Modal
  const [openPic, setOpenPic] = React.useState(false);
  const handleOpenPic = () => setOpenPic(true);
  const handleConfirmPic = () => // 사진 저장 요청
  void(0)

  return (
    <div className={styles["profile-edit-page"]}>
      <Sidebar />
      <CustomModal open={open} setOpen={setOpen} handleConfirm={handleConfirm} 
      children={
      <div className={styles["editmodal"]}>
        <div className={styles["picturebox"]}>
          <img src={defaultImg} className={styles["picture"]} />
        </div>
        <div className={styles["textbox"]}>
          <p>회원 정보가 수정됩니다.</p>
          <p>계속 하시겠습니까?</p>
        </div>
      </div>
      }/>
      <CustomModal open={openPic} setOpen={setOpenPic} handleConfirm={handleConfirmPic} 
      children={
        <div>
          <input type="file" />
        </div>
      } />
      <div className={styles["left"]}>
        <div>
          <div className={styles["pictureBox"]}>
            <img className={styles["pictureEdit"]} src={PictureEdit} alt="사진 변경" onClick={handleOpenPic} />
            <img className={styles["picture"]} src={defaultImg} alt="임시사진" />
          </div>
          <h1 className={styles["name"]}>임시 이름</h1>
          <CustomButton size="large" onClick={handleOpen}>저장하기</CustomButton>
        </div>
      </div>
      <div className={styles["right"]}>
        <div className={styles["content"]}>
          <h2>닉네임</h2>
          <div className={styles["nicknameinput"]}>
            <input type="text" placeholder="2~10글자의 한글, 영어 대/소문자" maxLength={10} className={styles["nickname"]}></input>
            <CustomButton className={styles["overlap"]}>중복 확인</CustomButton>
          </div>
          <br/><br/>
          <h2>한 줄 소개</h2>
          <input className={styles["oneline"]} value={text} onChange={count} type="text" placeholder="한 줄 소개를 입력해주세요" maxLength={50}  />
          <span className={styles["count"]}>{text.length}/50</span>
          <br/><br/>
          <h2>설명</h2>
          <textarea className={styles["explain"]} value={explain} onChange={count2} placeholder="상세 설명을 입력해주세요" maxLength={300}  />
          <span className={styles["count"]}>{explain.length}/300</span>
          <br/><br/>
          <div className={styles["withdrow"]}>회원탈퇴</div>
        </div>
      </div>
    </div>
  );
};

export default ProfileEditPage;

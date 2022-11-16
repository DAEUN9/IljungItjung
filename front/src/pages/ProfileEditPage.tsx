import TextField from "@mui/material/TextField";
import Swal from "sweetalert2";
import CustomButton from "@components/common/CustomButton";
import Sidebar from "@components/common/Sidebar";
import styles from "@styles/ProfileEdit/ProfileEdit.module.scss";
import defaultImg from "@assets/defaultImg.png";
import React, { useEffect, useState } from "react";
import CustomModal from "@components/common/CustomModal";
import { putProfile } from "@api/profile";
import { delWithdraw, getOverlap } from "@api/register";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "@modules/index";

const ProfileEditPage = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [defalutName, setDefaultName] = useState("");
  const [intro, setIntro] = useState<string | null>("");
  const [description, setDescription] = useState<string | null>("");
  const [img, setImg] = useState("");
  // 닉네임 체크 후 출력할 문자
  const [check, setcheck] = useState("");
  // 닉네임 박스 포커스 설정
  const [focus, setFocus] = useState(false);
  // 닉네임 중복검사 버튼 활성화 상태
  const [ableName, setAbleName] = useState(true);

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
  // 중복 확인 버튼 누르면 중복검사 API로 확인하고 결과값 출력
  const checkOverlap = () => {
    getOverlap(
      name,
      (res: object) => {
        setcheck("사용 가능한 닉네임 입니다.");
        setAbleName(true);
        setFocus(true);
      },
      (err: any) => {
        setcheck("이미 등록된 닉네임 입니다.");
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
          setcheck("사용 가능한 닉네임 입니다.");
          setAbleName(true);
          setFocus(true);
        },
        (err: any) => {
          setcheck("이미 등록된 닉네임 입니다.");
          setAbleName(true);
          setFocus(true);
        }
      );
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
  const handleConfirm = () => {
    putProfile(name, intro, description, (res: object) => {
      location.reload();
    });
  };

  // 회원 탈퇴 모달
  const [openWithdrow, setOpenWithdrow] = React.useState(false);
  const handleOpenWithdrow = () => setOpenWithdrow(true);
  const handleConfirmWithdrow = () => {
    navigate(`/`);
    // 회원 탈퇴 요청
    delWithdraw(() => {
      // 탈퇴 후 로그인 화면으로 이동
      Swal.fire(
        "회원 탈퇴 되었습니다.",
        "로그인 페이지로 이동합니다.",
        "success"
      );
      navigate(`/`);
    });
  };

  // 내 정보 불러오는 부분
  const profile = useSelector((state: RootState) => state.profile.profile);
  useEffect(() => {
    setName(profile.nickname);
    setDefaultName(profile.nickname);
    setIntro(profile.introduction);
    setDescription(profile.description);
    setImg(profile.imagePath);
  }, [profile]);

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
            <img className={styles["picture"]} src={img} />
          </div>
          <h1 className={styles["name"]}>{defalutName}</h1>
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
                check === "사용 가능한 닉네임 입니다." ? "success" : "primary"
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
          <br />
          <h2>한 줄 소개</h2>
          <TextField
            className={styles["intro"]}
            onChange={handleIntro}
            multiline
            minRows={2}
            placeholder="한 줄 소개를 입력해주세요"
            value={intro}
            inputProps={{ maxLength: 50 }}
          />
          <span className={styles["count"]}>
            {intro === null ? "0" : intro.length}/50
          </span>
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
          <span className={styles["count"]}>
            {description === null ? "0" : description.length}/300
          </span>
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

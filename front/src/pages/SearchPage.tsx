import { IoSearchOutline } from "react-icons/io5";
import { IconButton, InputBase } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "@modules/index";

import Sidebar from "@components/common/Sidebar";
import SearchResult from "@components/Search/SearchResult";
import styles from "@styles/Search/Search.module.scss";
import defaultImg from "@assets/defaultImg.png";
import SearchItem from "@components/Search/SearchItem";
import { getSearchList } from "@api/search";
import { SearchState } from "@components/types/types";

const data = [
  {
    imagePath: defaultImg,
    nickname: "이순재 교수",
    email: "hello@naver.com",
    desc: "경북대학교 이순재 교수",
    detail: "010-1234-1234 전화주세요",
    categories: [{ categoryName: "진로 상담" }, { categoryName: "학업 상담" }],
  },
  {
    imagePath: defaultImg,
    nickname: "이순재네일",
    email: "hello@naver.com",
    desc: "경북대 북문에 위치한 이순재네일 입니다. 연중무휴 입니다~! 쉬는 날 없이 일하고 있습니다 :)",
    detail: "네일, 손톱 관리, 페디큐어",
    categories: [
      { categoryName: "네일" },
      { categoryName: "손톱 관리" },
      { categoryName: "패디큐어" },
    ],
  },
  {
    imagePath: defaultImg,
    nickname: "갱알지목욕",
    desc: "강아지 목욕 시켜요",
    email: "hello@naver.com",
    detail: "갱알지 목욕 시킵니다.",
    categories: [{ categoryName: "목욕" }, { categoryName: "미용" }],
  },
];

interface SearchApiData {
  status: string;
  data: SearchState[];
}

const SearchPage = () => {
  const [search, setSearch] = useState("");
  const [searchList, setSearchList] = useState<SearchState[]>([]);

  const selectedName = useSelector((state: RootState) => state.search.nickname);

  useEffect(() => {
    console.log(searchList);
  }, [searchList]);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(event.target.value);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLElement>) => {
    if (event.key === "Enter") {
      getSearchList(search, (res: SearchApiData) => {
        setSearchList(res.data);
        console.log(res.data);
      });
    }
  };

  return (
    <div className={styles["search-page"]}>
      <Sidebar />
      <div className={styles["content"]}>
        <div className={styles["left"]}>
          <div className={styles["search-bar"]}>
            <IconButton disableTouchRipple>
              <IoSearchOutline />
            </IconButton>
            <InputBase
              className={styles["input"]}
              placeholder="사용자 닉네임을 검색하세요."
              onChange={handleChange}
              onKeyDown={handleKeyDown}
            />
          </div>
          <div className={styles["result"]}>
            {searchList.length > 0 ? (
              searchList.map((item, index) => (
                <SearchItem
                  key={index}
                  nickname={item.nickname}
                  email={item.email}
                  imagePath={item.imagePath}
                  introduction={item.introduction}
                  description={item.description}
                  categories={item.categories}
                />
              ))
            ) : (
              <div className={styles["no-data"]}>검색 결과가 없습니다.</div>
            )}
          </div>
        </div>
        <div className={styles["right"]}>
          {selectedName ? (
            <SearchResult />
          ) : (
            <div className={styles["no-user"]}>사용자를 선택해 주세요.</div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SearchPage;

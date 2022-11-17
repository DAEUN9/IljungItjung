import { IoSearchOutline } from "react-icons/io5";
import { IconButton, InputBase } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import Sidebar from "@components/common/Sidebar";
import SearchResult from "@components/Search/SearchResult";
import styles from "@styles/Search/Search.module.scss";
import SearchItem from "@components/Search/SearchItem";
import { getSearchList } from "@api/search";
import { SearchState } from "@components/types/types";
import { selectUser } from "@modules/search";
import { RootState } from "@modules/index";

interface SearchApiData {
  status: string;
  data: {
    users: SearchState[];
  };
}

interface SearchApiData {
  status: string;
  data: {
    users: SearchState[];
  };
}

const SearchPage = () => {
  const dispatch = useDispatch();

  const [search, setSearch] = useState("");
  const [searchList, setSearchList] = useState<SearchState[]>([]);
  const selectedName = useSelector((state: RootState) => state.search.nickname);

  useEffect(() => {
    dispatch(
      selectUser({
        nickname: "",
        email: "",
        introduction: "",
        description: "",
        imagePath: "",
        categories: [],
      })
    );
  }, []);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(event.target.value);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLElement>) => {
    if (event.key === "Enter") {
      getSearchList(search, (res: SearchApiData) => {
        setSearchList(res.data.users);
      });
    }
  };

  return (
    <div className={styles["search-page"]}>
      <Sidebar />
      <div className={styles.content}>
        <div className={styles.left}>
          <div className={styles["search-bar"]}>
            <IconButton disableTouchRipple>
              <IoSearchOutline />
            </IconButton>
            <InputBase
              className={styles.input}
              placeholder="사용자 닉네임을 검색하세요."
              onChange={handleChange}
              onKeyDown={handleKeyDown}
            />
          </div>
          <div className={styles.result}>
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
        <div className={styles.right}>
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

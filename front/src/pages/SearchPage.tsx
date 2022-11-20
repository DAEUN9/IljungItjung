import { IoSearchOutline } from "react-icons/io5";
import { IconButton, InputBase, Snackbar } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "@modules/index";

import Sidebar from "@components/common/Sidebar";
import SearchResult from "@components/Search/SearchResult";
import styles from "@styles/Search/Search.module.scss";
import SearchItem from "@components/Search/SearchItem";
import { getSearchList } from "@api/search";
import { SearchState } from "@components/types/types";
import { selectUser } from "@modules/search";

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
  const [snackbar, setSnackbar] = useState(false);
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
      if (search.length < 2) {
        setSnackbar(true);
        setSearchList([]);
      } else {
        getSearchList(
          search,
          (res: SearchApiData) => {
            setSearchList(res.data.users);
          },
          (err: any) => setSearchList([])
        );
      }
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
      <Snackbar
        open={snackbar}
        autoHideDuration={3000}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        onClose={() => setSnackbar(false)}
        message="두 글자 이상 입력해 주세요."
      />
    </div>
  );
};

export default SearchPage;

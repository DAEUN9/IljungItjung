import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

import LoginPage from '@pages/LoginPage';
import RegisterPage from '@pages/RegisterPage';
import CalendarPage from '@pages/CalendarPage';
import MyCalendar from '@components/Calendar/MyCalendar';
import OtherCalendar from '@components/Calendar/OtherCalendar';
import ProfileEditPage from '@pages/ProfileEditPage';
import ReservationPage from '@pages/ReservationPage';
import SearchPage from '@pages/SearchPage';
import SettingPage from '@pages/SettingPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate replace to="/login" />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/calendar" element={<CalendarPage />}>
          <Route path="my" element={<MyCalendar />} />
          <Route path=":seqNum" element={<OtherCalendar />} />
        </Route>
        <Route path="/profile" element={<ProfileEditPage />} />
        <Route path="/reservation" element={<ReservationPage />} />
        <Route path="/search" element={<SearchPage />} />
        <Route path="/setting" element={<SettingPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

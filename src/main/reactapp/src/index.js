import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// 내가 만든 컴포넌트 import(가져오기) 호출
import JSX선언 from './chapter3/1_JSX선언';

// chapter3 실습
import Book from './chapter3/Book';
import Library from './chapter3/Library';

// chapter4 실습
import Clock from './chapter4/Clock';

// chapter5 실습
import CommentList from './chapter5/CommentList';

// chapter7 예제/실습
import Counter from './chapter7/Counter';
import UseStateList from './chapter7/UseStateList';
import Counter2 from './chapter7/Counter2';

// chapter8 예제/실습
import ConfirmButton from './chapter8/ConfirmButton';

// chapter9 예제/실습
import LandingPage from './chapter9/LandingPage';

// chapter10 예제/실습
import AttendanceBook from './chapter10/AttendanceBook';

// chapter11 예제/실습
import NameForm from './chapter11/NameForm';
import SignUp2 from './chapter11/SignUp2';

// chapter12 예제/실습
import Calculator from './chapter12/Calculator';

// chapter13 예제/실습
import ProfileCard from './chapter13/ProfileCard';

// chapter0 axios
import Axios컴포넌트 from './chapter0/Axios컴포넌트';
import Route컴포넌트 from './chapter0/Route컴포넌트';

import SignUp from './component/member/SignUp';
import Index from './component/Index';

const root = ReactDOM.createRoot(document.getElementById('root'));
// setInterval(()=>{
//   root.render( // 여기가 렌더링 되는 곳
//     <React.StrictMode>
//       <Clock />
//     </React.StrictMode>
//   );
// },1000);

root.render( // 여기가 렌더링 되는 곳
  <React.StrictMode>
    <Index />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

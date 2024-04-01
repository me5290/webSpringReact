import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { LoginInfoContext } from "../Index";

export default function Header(props){
    const {loginInfo , setLoginInfo} = useContext(LoginInfoContext);

    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    useEffect(()=>{
        axios.get('/member/login/info/get.do')
        .then(r=>{
            console.log(r);
            setLoginInfo(r.data);
        }).catch(e=>{console.log(e)})
    },[])

    const logout = ()=>{
        axios.get('/member/logout/get.do')
        .then(r=>{
            if(r){
                alert('로그아웃 성공');
                setLoginInfo('');
                window.location.href = '/member/login';
            }else{
                alert('로그아웃 실패');
            }
        }).catch(e=>{console.log(e)})
    }

    return(
        <div>
            {loginInfo && <span>{loginInfo.memail}님</span>}
            <ul>
                <li>
                    <Link to="/">홈</Link>
                </li>
                {loginInfo && 
                    <li>
                        <Link to="/board/write">글쓰기</Link>
                    </li>
                }
                <li>
                    <Link to="/board">전체글보기</Link>
                </li>
                <li>
                    <Link to="/member/signup">회원가입</Link>
                </li>
                <li>
                    <Link to="/member/login">로그인</Link>
                </li>
                {loginInfo && 
                    <li>
                        <a href="#" onClick={logout}>로그아웃</a>
                    </li>
                }
            </ul>
        </div>
    )
}
import axios from "axios";
import { useContext, useEffect, useRef, useState } from "react"
import { LoginInfoContext } from "../Index";

export default function Write(props){
    const {loginInfo , setLoginInfo} = useContext(LoginInfoContext);
    const [bcontentinput,setBcontentInput] = useState('');

    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    useEffect(()=>{
        axios.get('/member/login/info/get.do')
        .then(r=>{
            console.log(r);
            setLoginInfo(r.data);
        }).catch(e=>{console.log(e)})
    },[])

    const contentChange = (e)=>{
        setBcontentInput(e.target.value);
    }

    const boardWrite = ()=>{
        let boardInfo = {bcontent : bcontentinput};
        
        if(loginInfo.mname != null){
            axios.post('/board/post.do',boardInfo)
            .then(r=>{
                if(r){
                    alert('작성 완료');
                    window.location.href = '/board';
                }else{
                    alert('작성 실패');
                }
            }).catch(e=>{
                console.log(e);
            })
        }else{
            alert('로그인이 필요한 서비스입니다.');
        }
    }

    return(
        <form>
            내용 : <input value={bcontentinput} onChange={contentChange} type="text"/>
            <button onClick={boardWrite} type="button">작성완료</button>
        </form>
    )
}
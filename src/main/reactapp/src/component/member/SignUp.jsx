import { useState } from "react";
import axios from "axios"; // axios 라이브러리 호출

export default function SignUp(props){
    const [memail,setMemail] = useState('');

    const [mpassword,setMpassword] = useState('');

    const [mname,setMname] = useState('');

    const onChangeMemail = (e)=>{
        setMemail(e.target.value);
    }

    // 3. 전송 함수
    const onSignUp = (e) =>{
        console.log(memail);
        console.log(mpassword);
        console.log(mname);

        let info = {memail : memail , mpassword : mpassword , mname : mname};

        axios.get("/member/idConfirm/get.do",{params:{id:memail}})
        .then(r=>{
            console.log(r)
            if(r.data){
                alert('아이디가 중복 되었습니다.');
            }else{
                /*
                    axios.HTTP메소드명(url,data).then(응답매개변수 => {응답로직})
                */
                axios.post("/member/signup/post.do",info)
                .then(response=>{
                    console.log(response);
                    if(response.data){
                        alert('회원가입 성공');
                        window.location.href="/member/login";
                    }else{
                        alert('회원가입 실패');
                    }
                })
                .catch(error=>{console.log(error)})
            }
        }).catch(error=>{console.log(error)})
    }

    return(
        <form>
            이메일 : <input type="text" value={memail} onChange={onChangeMemail}/>
            패스워드 : <input type="password" value={mpassword} onChange={(e)=>{setMpassword(e.target.value);}}/>
            이름 : <input type="text" value={mname} onChange={(e)=>{setMname(e.target.value);}}/>
            <button type="button" onClick={onSignUp}>제출</button>
        </form>
    );
}
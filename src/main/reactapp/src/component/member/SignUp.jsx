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

        /*
            axios.HTTP메소드명(url,data).then(응답매개변수 => {응답로직})
        */
        let info = {memail : memail , mpassword : mpassword , mname : mname};
        axios.post("http://localhost:8080/member/signup/post.do",info).then(response=>{console.log(response)})
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
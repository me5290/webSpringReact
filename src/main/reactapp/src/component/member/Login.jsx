import axios from "axios";

export default function Login(props){
    // 1. 로그인 요청 함수
    const onLogin = ()=>{
        let loginForm = document.querySelector('#loginForm');
        let loginFormData = new FormData(loginForm);

        axios.post('/member/login/post.do',loginFormData)
        .then((r)=>{
            console.log(r);
            if(r.data){
                alert('로그인 성공');
                window.location.href="/";
            }else{
                alert('로그인 실패');
            }
        })
        .catch((e)=>{console.log(e)})
    }

    return(
        <div>
            <form id="loginForm">
                이메일 : <input type="text" name="memail"/>
                비밀번호 : <input type="password" name="mpassword"/>
                <button type="button" onClick={onLogin}>로그인</button>
            </form>
        </div>
    )
}
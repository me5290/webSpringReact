import { useContext, useEffect, useRef, useState } from "react";
import { LoginInfoContext } from "../Index";
import styles from "./chat.css";
import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';

export default function Chatting(props){
    let [chatstate , setChatState] = useState(false);
    // 1. 해당 컴포넌트가 렌더링 될때 소켓은 재렌더링 방지
    let clientSocket = useRef(null);
    const {loginInfo} = useContext(LoginInfoContext);

    // 2. 
    if(!clientSocket.current){
        // ================= 클라이언트 웹소켓 구현 ================= //
        // 1. new WebSocket(서버소켓url);
        clientSocket.current = new WebSocket('ws://192.168.17.11:8080/chat');
        console.log(clientSocket.current);
        // onclose , onerror , onmessage , onopen : WebSocket객체내 포함된 메소드들

        // 2. 각 메소드 정의
            // - 1. 클라이언트소켓이 close 발생했을때 콜백함수 정의
        clientSocket.current.onclose = (e)=>{
            console.log(e);
            document.querySelector('.chatcont').innerHTML += `<p>${loginInfo.mname}님이 퇴장하였습니다.</p>`
            setChatState(!chatstate);
        }
            // - 2. 클라이언트소켓이 error 발생했을때 콜백함수 정의
        clientSocket.current.onerror = (e)=>{
            console.log(e);
        }
            // - 3. 클라이언트소켓이 message 받았을때 콜백함수 정의
        clientSocket.current.onmessage = (e)=>{
            console.log(e);
            msgList.push(JSON.parse(e.data));
            setMsgList([...msgList]);
        };
            // - 4. 클라이언트소켓이 open 발생했을때 콜백함수 정의
        clientSocket.current.onopen = (e)=>{
            console.log(e);
            document.querySelector('.chatcont').innerHTML += `<p>${loginInfo.mname}님이 입장하였습니다.</p>`
            setChatState(!chatstate);
        }
    }

    // 4. 연결종료
    // clientSocket.close();

    const onSend = ()=>{
        let info = {
            msg : msgInput,
            fromMname : loginInfo.mname,
            type : 'msg'
        }

        setMsgInput('');

        // 3. 연결된 서버소켓에게 메시지 보내기
            // send() : 데이터타입 = 텍스트
                // JSON -> 문자 : JSON.stringify(js객체)
                // 문자 -> JSON : JSON.parse(문자열)
        clientSocket.current.send(JSON.stringify(info)); // 입력받은 데이터를 서버소켓에게 보내기
    }

    const [msgInput , setMsgInput] = useState('');
    const [msgList , setMsgList] = useState([]);


    // 시간
    let today = new Date();
    let hours = today.getHours(); // 시
    let minutes = today.getMinutes();  // 분

    // 엔터 누르기
    const activeEnter = (e)=>{
        // 컨트롤 + 엔터
        if(e.keyCode == 13 && e.ctrlKey){
            setMsgInput(msgInput + '\n');
            return;
        }

        // 엔터
        if(e.keyCode == 13){
            onSend();
        }
    }

    // 스크롤 자동으로 최하단으로 내리기
    useEffect(()=>{
        let chatcont = document.querySelector('.chatcont');

        chatcont.scrollTop = chatcont.scrollHeight;
    })

    // 드롭다운 mui
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    // 이모티콘 이미지를 클릭했을때 전송
    const onEmoSend = (emo)=>{
        let info = {
            msg : emo,
            fromMname : loginInfo.mname,
            type : 'emo'
        }

        clientSocket.current.send(JSON.stringify(info));

        handleClose();
    }

    // msg 타입에 따른 html 반환 함수
    const typeHTML = (m)=>{
        if (m.type === 'msg') {
            const msgLines = m.msg.split('\n');
            return (
                <div className="content">
                    {msgLines.map((line, index) => (
                        <React.Fragment key={index}>
                            {line}
                            <br />
                        </React.Fragment>
                    ))}
                </div>
            );
        } else if (m.type === 'emo') {
            return <img src={`/emo/${m.msg}`} />;
        }
    }

    return(
        <div>
            <h3>채팅방</h3>
            <div className="chatbox">
                <div className="chatcont">
                    {
                        msgList.map((m)=>{
                            return(
                                <>
                                    {
                                        loginInfo.mname == m.fromMname ?
                                        (
                                            <div className="rcont">
                                                <div className="subcont">
                                                    <div className="date">
                                                    {hours <= 12 ? '오전 ' : '오후 '}{hours <= 12 ? hours : hours-12}:{minutes < 10 ? "0"+minutes : minutes}
                                                    </div>
                                                    {typeHTML(m)}
                                                </div>
                                            </div>
                                        ) :
                                        (
                                            <div className="lcont">
                                                <img className="pimg" src={'/uploadimg/default.jpg'}/>
                                                <div className="tocont">
                                                    <div className="name">{ m.fromMname } </div>
                                                    <div className="subcont">
                                                        {typeHTML(m)}
                                                        <div className="date"> {hours <= 12 ? '오전 ' : '오후 '}{hours <= 12 ? hours : hours-12}:{minutes < 10 ? "0"+minutes : minutes}</div>
                                                    </div>
                                                </div>
                                            </div>
                                        )
                                    }
                                </>
                            )
                        })
                    }
                </div>
                <div className="chatbottom">
                    <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}} onKeyDown={activeEnter}></textarea>
                    <button type="button" onClick={onSend}>전송</button>
                </div>
                <div>
                <Button
                    id="basic-button"
                    aria-controls={open ? 'basic-menu' : undefined}
                    aria-haspopup="true"
                    aria-expanded={open ? 'true' : undefined}
                    onClick={handleClick}
                >
                    이모티콘
                </Button>
                <Menu
                    id="basic-menu"
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    MenuListProps={{
                    'aria-labelledby': 'basic-button',
                    }}
                >
                    <div style={{height:200 , overflowY:'scroll'}}>
                        {
                            Array(43).fill().map((v,i)=>{
                                return (
                                    <>
                                        <img src={`/emo/emo${i+1}.gif`} onClick={()=>onEmoSend(`emo${i+1}.gif`)}/>
                                        {(i+1) % 5 == 0 && <br/>}
                                    </>
                                )
                            })
                        }
                    </div>
                </Menu>
                </div>
            </div>
        </div>
    )
}
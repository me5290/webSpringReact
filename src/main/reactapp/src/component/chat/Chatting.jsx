import { useRef, useState } from "react";

export default function Chatting(props){
    // 1. 해당 컴포넌트가 렌더링 될때 소켓은 재렌더링 방지
    let clientSocket = useRef(null);

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
        }
            // - 2. 클라이언트소켓이 error 발생했을때 콜백함수 정의
        clientSocket.current.onerror = (e)=>{
            console.log(e);
        }
            // - 3. 클라이언트소켓이 message 받았을때 콜백함수 정의
        clientSocket.current.onmessage = (e)=>{
            console.log(e);
            msgList.push(e.data);
            setMsgList([...msgList]);
        };
            // - 4. 클라이언트소켓이 open 발생했을때 콜백함수 정의
        clientSocket.current.onopen = (e)=>{
            console.log(e);
        }
    }

    // 4. 연결종료
    // clientSocket.close();

    const onSend = ()=>{
        // 3. 연결된 서버소켓에게 메시지 보내기
        clientSocket.current.send(msgInput); // 입력받은 데이터를 서버소켓에게 보내기
    }

    const [msgInput , setMsgInput] = useState('');
    const [msgList , setMsgList] = useState([]);

    return(
        <div>
            <h3>채팅방</h3>
            <div className="printChat">
                {
                    msgList.map((msg)=>{
                        return(
                            <p>
                                {msg}
                            </p>
                        )
                    })
                }
            </div>
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}></textarea>
            <button type="button" onClick={onSend}>전송</button>
        </div>
    )
}
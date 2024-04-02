import axios from "axios";
import { useRef } from "react"

export default function Write(props){
    const boardWriteFormRef = useRef();

    const boardWrite = ()=>{
        console.log(boardWriteFormRef);
        console.log(boardWriteFormRef.current);
        
        axios.post('/board/post.do',boardWriteFormRef.current)
        .then(r=>{
            if(r.data){
                alert('작성 완료');
                window.location.href = '/board';
            }else{
                alert('작성 실패');
            }
        }).catch(e=>{
            console.log(e);
        })
    }

    return(
        <form ref={boardWriteFormRef}>
            내용 : <input name="bcontent" type="text"/>
            사진 : <input name="uploadList" type="file" multiple accept="image/*"/>
            <button onClick={boardWrite} type="button">작성완료</button>
        </form>
    )
}
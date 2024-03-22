import { useState } from "react";

export default function UseStateList(props){
    // point 상태관리변수
        // 1. input 입력된 값을 저장하는 상태관리변수
    let [pointInput, setPointInput] = useState('');
        // 2. input 입력된 값들을 저장하는 리스트 상태관리변수
    let [pointList, setPointList] = useState([]);

    // input에 입력할때마다 재렌더링
    function changeValue(e){
        setPointInput(e.target.value);
    }

    function clickBtn(){
        console.log('clickBtn()');
        pointList.push(pointInput);
        // setPointList(pointList);     주소값이 바뀌지 않아서 재렌더링이 안됨
        setPointList([...pointList]);   // []...기존배열] 기존배열을 새로운 배열에 넣어 주소값 변경
    }

    return(
        <div>
            <div>
                <input value={pointInput} onChange={changeValue} type="text"/>
                <button type="button" onClick={clickBtn}>등록</button>
            </div>
            <div>
                {pointList.map((point)=>{
                    return(<div>{point}</div>)
                })}
            </div>
        </div>
    );
}
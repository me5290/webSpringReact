import { useState } from "react";

export default function Counter(props){
    var countValue = 0;

    function clickBtn(){
        countValue++;
        console.log(countValue);
    }

    // useState('초기값');
        // [0] : state 초기값 또는 값
        // [1] : state의 set 함수 (state 값을 변경할때 사용되는 함수)
        // 재렌더링을 하기 위해서는 주소값 변경 필요 : 복사
    let stateArray = useState('훅이란무엇인가?');
    console.log(stateArray);

    const [count , setCount] = useState(0);

    const [inputValue1 , setInputValue1] = useState('');
    function inputValue1Update(e){
        console.log(e);
        setInputValue1(e.target.value);
    }

    return(
        <div>
            <div>
                <p>총 {countValue}번 클릭했습니다.</p>
                <button type="button" onClick={()=>countValue++}>
                    클릭
                </button>
            </div>

            <div>
            <p>총 {count}번 클릭했습니다.</p>
            <button type="button" onClick={()=>setCount(count+1)}>
                클릭
            </button>
            </div>
            <div>
                <input type="text"/>
                <input type="text" value={inputValue1}/>
                <input type="text" value={inputValue1} onChange={inputValue1Update}/>
            </div>
        </div>
    );
}
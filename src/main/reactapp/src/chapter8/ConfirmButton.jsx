import { useState } from "react";

export default function ConfirmButton(props){
    // 1. 상태(state) 관리 변수
    const [isConfirmed , setIsConfirmed] = useState(false);

    // 2. 함수
    const handleConfirm = ()=>{
        setIsConfirmed((prevIsConfirmed) => !prevIsConfirmed)
    }

    return(
        <div>
            <button onClick={handleConfirm} disabled={isConfirmed}>
                {isConfirmed ? '확인됨' : '확인하기'}
            </button>
        </div>
    )
}
import { useRef } from "react";

export default function TextInputWithFocusButton(props){
    const inputElem = useRef(null);

    const onButtonClick = () => {
        // current : 마운트 된 input element
        inputElem.current.focus();
        console.log(inputElem);
    };

    return(
        <div>
            <input ref={inputElem} type="text" />
            <button type="button" onClick={onButtonClick}>Focus the input</button>
        </div>
    )
}
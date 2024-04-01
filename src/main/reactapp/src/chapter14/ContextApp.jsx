// 1. 컴포넌트1 : 조부모
export default function ContextApp(props){
    return(
        <div>
            <Toolbar theme="dark"/>
        </div>
    )
}

// 2. 컴포넌트2 : 부모
function Toolbar(props){
    console.log(props);
    return(
        <div>
            <ThemeButton theme={props.theme}/>
        </div>
    )
}

// 3. 컴포넌트3 : 자식
function ThemeButton(props){
    console.log(props);
    return(
        <div>
            <Button theme = {props.theme}/>
        </div>
    )
}
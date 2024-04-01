// 0. 컨텍스트 이용한 컴포넌트 트리로 곧바로 전달하기 위한 컨텍스트 저장소 생성
const ThemeContext = React.createContext('light');

export default function ContextApp2(props){
    return(
        <div>
            <ThemeContext.Provider value="dark">
                <Toolbar/>
            </ThemeContext.Provider>
        </div>
    )
}

function Toolbar(props){
    console.log(props);
    return(
        <div>
            <ThemeButton/>
        </div>
    )
}

function ThemeButton(props){
    console.log(props);
    return(
        <div>
            <ThemeContext.Consumer>
                {value => <Button theme={value}/>}
            </ThemeContext.Consumer>
        </div>
    )
}
import Comment from "./Comment";

export default function CommentList(props){
    let responese = [
        {name:'강호동',content:'안녕하세요1'},
        {name:'유재석',content:'안녕하세요2'},
        {name:'신동엽',content:'안녕하세요3'},
        {name:'마동석',content:'안녕하세요4'}
    ];
    return (
        <div>
            {responese.map((result)=>{
                return <Comment name={result.name} comment={result.content}/>
            })}
        </div>
    );
}
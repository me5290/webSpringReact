import axios from "axios";
import { useEffect, useState } from "react"
import MediaCard from "./MediaCard";

export default function List(props){
    const [boardlist , setBoardList] = useState([]);

    useEffect(()=>{
        axios.get('/board/get.do').then((response)=>{
            console.log(response); 
            setBoardList(response.data);
        }).catch(error=>{
            console.log(error);
        })
    },[])

    return(
        <div style={{display:'flex' , flexWrap: "wrap"}}>
            {
                boardlist.map((r,index)=>{
                    return(
                        <MediaCard r = {r}/>
                    )
                })
            }
        </div>
    )
}
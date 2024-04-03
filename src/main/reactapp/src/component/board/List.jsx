import axios from "axios";
import { useCallback, useEffect, useState } from "react"
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function List(props){
    const [pageDto , setPageDto] = useState({page : 1 , pageCount : 0 , data : []});
    const [deleteCard,setDeleteCard] = useState(false);

    useEffect(()=>{
        const info = {page : pageDto.page , view : 3};
        axios.get('/board/get.do',{params:info}).then((response)=>{
            console.log(response); 
            setPageDto(response.data);
        }).catch(error=>{
            console.log(error);
        })
    },[pageDto.page,deleteCard])

    const handleChange = (event, value) => {
        pageDto.page = value;
        setPageDto({...pageDto});
    };

    const onDelete = useCallback((bno)=>{
        const deleteInfo = {bno:bno};
        axios.delete('/board/delete.do',{params:deleteInfo})
        .then(r=>{
            console.log(r);
            if(r.data){
                alert('삭제 성공');
                setDeleteCard(prevDeleteCard => !prevDeleteCard);
            }else{
                alert('삭제 실패');
            }
        })
        .catch(e=>{
            console.log(e);
        })
    },[deleteCard]);

    return(
        <div>
            <div style={{display:'flex' , flexWrap: "wrap"}}>
                {
                    pageDto.data.map((r,index)=>{
                        return(
                            <MediaCard r = {r} delete = {() => onDelete(r.bno)}/>
                        )
                    })
                }
            </div>
            <Pagination count={pageDto.pageCount} page={pageDto.page} onChange={handleChange} color="secondary" />
        </div>
    )
}
import axios from "axios";
import { useEffect, useState } from "react"

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
        <table>
            <thead>
                <tr>
                    <th>
                        내용
                    </th>
                    <th>
                        작성자
                    </th>
                </tr>
            </thead>
            <tbody>
                {
                    boardlist.map(r=>{
                        return(
                            <tr>
                                <td>
                                    {r.bcontent}
                                </td>
                                <td>
                                    {r.memail}
                                </td>
                            </tr>
                        )
                    })
                }
            </tbody>
        </table>
    )
}
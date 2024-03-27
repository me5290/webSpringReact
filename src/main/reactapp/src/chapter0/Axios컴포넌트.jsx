import axios from "axios";

export default function Axios컴포넌트(props){
    // ===================================================================================== //
    // 1. 기본함수
    function 함수명1(e){
        console.log('함수명1 : ' + e);
    }

    // 2. 화살표 함수
    const 함수명2 = (e) => {
        console.log('함수명2 : ' + e);
    }

    // 3. 매개변수를 포함한 화살표 함수
    const 함수명3 = (e,매개변수1) => {
        console.log('함수명3 : ' + e + 매개변수1);
    }
    // ===================================================================================== //

    // 1. GET
    const doGet = async()=>{
        console.log(1);
        await axios.get('https://jsonplaceholder.typicode.com/posts').then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)})
        console.log(2);
        await axios.get('https://jsonplaceholder.typicode.com/posts/1').then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)})
        console.log(3);
        await axios.get('https://jsonplaceholder.typicode.com/comments?postId=1').then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)})
        console.log(4);
        await axios.get('https://jsonplaceholder.typicode.com/comments',{params : {postId : 1}}).then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)})
    }

    // 2. POST
    const doPost = ()=>{
        let saveInfo = {
            title: 'foo',
            body: 'bar',
            userId: 1,
        }
        axios.post('https://jsonplaceholder.typicode.com/posts' , saveInfo).then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)}) // Content-Type:application/json
        
        const axiosForm = document.querySelector('#axiosForm');
        const axiosFormData = new FormData(axiosForm);
        axios.post('http://localhost:8080',axiosFormData).then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)}) // Content-Type:multipart/form-data
    }

    // 3. PUT
    const doPut = ()=>{
        let updateInfo = {
            title: 'roo',
            body: 'zxc',
            userId: 43,
        }
        axios.put('https://jsonplaceholder.typicode.com/posts/1' , updateInfo).then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)}) // Content-Type:application/json
    }

    // 4. DELETE
    const doDelete = ()=>{
        axios.delete('https://jsonplaceholder.typicode.com/posts/1').then((respons)=>{console.log(respons)}).catch(error=>{console.log(error)}) // Content-Type:application/json
    }

    return(
        <div>
            <h3>AXIOS 테스트</h3>
            <button type="button" onClick={함수명1}>함수명1 실행</button>
            <button type="button" onClick={함수명2}>함수명2 실행</button>
            <button type="button" onClick={(e)=>{함수명3(e , 3)}}>함수명3 실행</button>
            <button type="button" onClick={doGet}>doGet 테스트</button>
            <form id="axiosForm">
                <input type="text"/>
            </form>
            <button type="button" onClick={doPost}>doPost 테스트</button>
            <button type="button" onClick={doPut}>doPut 테스트</button>
            <button type="button" onClick={doDelete}>doDelete 테스트</button>
        </div>
    )
}
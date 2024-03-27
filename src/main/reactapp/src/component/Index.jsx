import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layouts/Header";
import Home from "./layouts/Home";
import Footer from "./layouts/Footer";
import SignUp from "./member/SignUp";
import Login from "./member/Login";

export default function Index(props){
    return(
        <div>
            <BrowserRouter>
                <div id="wrap">
                    <Header/>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/member/signup" element={<SignUp/>}/>
                        <Route path="/member/login" element={<Login/>}/>
                    </Routes>
                    <Footer/>
                </div>
            </BrowserRouter>
        </div>
    )
}
import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {MainPage} from 'core/pages'

const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="restroom/:restroomId" element={<MainPage />} />
                <Route path="*" element={<>Not found Content</>} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRoutes;
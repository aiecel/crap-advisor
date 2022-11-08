import React, {useState} from "react";
import {Map, Sidebar} from "core/components";
import "./index.css";
import "style/main.css";
import {useAppDispatch, useAppSelector} from "app/typings/redux";
import {start} from "store/restrooms/slice";
import {clearCurrentRestroom, setCurrentRestroom, start as reviewStart} from "store/reviews/slice";
import {getRestroomIdFromURL} from "utils";

const MainPage = (): JSX.Element => {
    const {result: restrooms} = useAppSelector(state => state.RESTROOM);;
    const [restroomFromURL] = React.useState(getRestroomIdFromURL())
    const dispatch = useAppDispatch();

    React.useEffect(() => {
        dispatch(start());
    }, [])


    return (
        <main className="mainContainer">
            <Sidebar />
            <Map />
        </main>
    );
};

export default MainPage;

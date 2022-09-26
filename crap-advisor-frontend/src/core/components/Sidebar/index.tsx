import React, {useEffect, useState} from "react";
import "./index.css";
import Review from "./Review";
import {ApiEndpoint, apiUrl} from "../../../app/constants";
import {IReview, Restroom} from "../../../app/typings";
import {AxiosError, AxiosResponse} from "axios";
import {Api} from "../../../app/api";
import {useDataApi} from "../../../hooks/useDataApi";

interface ISidebarProps {
    selectedRestroom: Restroom | null;
}

const Sidebar = ({selectedRestroom}: ISidebarProps): JSX.Element => {

    const url = `${ApiEndpoint.GetReviewById}${selectedRestroom && selectedRestroom.id}`;
    const {setUrl, primary: {data: reviews, isLoading, isError}} = useDataApi<IReview>(url, [], "GET");

    useEffect(() => {
        if (selectedRestroom) {
            setUrl(url);
        }
    }, [selectedRestroom])

    return (
        <div className="sidebar">
            {selectedRestroom && (
                <>
                    <div className="sidebar-restroom-header">{selectedRestroom.name}</div>
                    <div className="sidebar-restroom-rating">
                        {selectedRestroom.rating && `★${selectedRestroom.rating}`}
                    </div>
                    <hr/>
                    <div className="sidebar-reviews-header">
                        {reviews.length === 0 ? "Нет визитов" : "Визитов: " + reviews.length}
                    </div>
                    {reviews.map((review) => {
                        return <Review key={review.id} review={review}/>;
                    })}
                </>
            )}
        </div>
    );
};

export default Sidebar;

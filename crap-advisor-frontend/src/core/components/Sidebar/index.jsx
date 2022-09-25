import React, {useEffect, useState} from 'react';
import "./index.css"
import Review from "./Review";
import {apiUrl} from "../../../app/constants";

const Sidebar = ({selectedRestroom}) => {
    const [reviews, setReviews] = useState([])

    useEffect(() => {
        if (selectedRestroom != null) {
            fetch(apiUrl + "/review/restroom/" + selectedRestroom.id)
                .then((response) => response.json())
                .then((data) => {
                    setReviews(data)
                    console.log(`Received ${data.length} reviews for restroom with id ${selectedRestroom.id}`)
                })
        }
    }, [selectedRestroom])

    if (selectedRestroom == null) return (<div className="sidebar"/>)

    return (
        <div className="sidebar">
            <div className="sidebar-restroom-header">{selectedRestroom.name}</div>
            <div className="sidebar-restroom-rating">
                {selectedRestroom.rating != null ? "★" + selectedRestroom.rating : null}
            </div>
            <hr/>
            <div className="sidebar-reviews-header">
                {reviews.length === 0 ? "Нет визитов" : "Визитов: " + reviews.length}
            </div>
            {
                reviews.map((review, i) => {
                    return (<Review key={i} review={review}/>)
                })
            }
        </div>
    );
};

export default Sidebar;
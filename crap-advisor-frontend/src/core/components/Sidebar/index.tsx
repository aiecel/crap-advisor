import React, {useEffect, useState} from "react";
import "./index.css";
import Review from "./Review";
import { apiUrl} from "../../../app/constants";
import {IReview, Restroom} from "../../../app/typings";

interface ISidebarProps {
    selectedRestroom: Restroom | null;
}

const Sidebar = ({selectedRestroom}: ISidebarProps): JSX.Element => {
   const [reviews, setReviews] = useState<IReview[]>([]);

    useEffect(() => {
        if (selectedRestroom) {
            try {
                fetch(apiUrl + "/review/restroom/" + selectedRestroom.id)
                    .then((response) => response.json())
                    .then((data) => {
                        setReviews(data);
                    });
            } catch (e) {
                console.error(e);
            }
        }
    }, [selectedRestroom]);

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
                    {reviews.map((review: IReview) => {
                        return <Review key={review.id} review={review}/>;
                    })}
                </>
            )}
        </div>
    );
};

export default Sidebar;

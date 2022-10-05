import React, {useEffect} from "react";
import Review from "./Review";
import {ApiEndpoint} from "../../../app/constants";
import {IReview, Restroom} from "../../../app/typings";
import {useDataApi} from "../../../hooks/useDataApi";
import "./index.css";
import "../../../style/main.css";

interface ISidebarProps {
    selectedRestroom: Restroom | null;
}

const Sidebar = ({selectedRestroom}: ISidebarProps): JSX.Element => {

    const url = `${ApiEndpoint.GetAllReviewsByRestroomId}${selectedRestroom && selectedRestroom.id}`;
    const {setUrl, state: {data: reviews}} = useDataApi<IReview>(url, [], "GET");

    useEffect(() => {
        if (selectedRestroom) {
            setUrl(url);
        }
    }, [selectedRestroom])

    return (
        <div className="sidebar">
            <p className="logo">Crap Advisor</p>
            {!selectedRestroom && (
                <>
                    <p className="welcome">Добро пожаловать!<br/>Выберите уборную...</p>
                </>
            )}
            {selectedRestroom && (
                <>
                    <section className="section-restroom">
                        <p className="restroom-created">{new Date(selectedRestroom.created).toLocaleDateString()}</p>
                        <p className="restroom-name">{selectedRestroom.name}</p>
                        {selectedRestroom.rating && <p className="restroom-rating">{selectedRestroom.rating}</p>}
                        <p className="restroom-reviews-count">
                            {reviews.length === 0 ? "Нет визитов" : "Визитов: " + reviews.length}
                        </p>
                    </section>
                    <section className="section-reviews">
                        <button className="button-add-review">Добавить отзыв</button>
                        {reviews.map((review) => {
                            return <Review key={review.id} review={review}/>;
                        })}
                    </section>
                </>
            )}
        </div>
    );
};

export default Sidebar;

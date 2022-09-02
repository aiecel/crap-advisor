import React from 'react';
import {YMaps, Map as YMap, ZoomControl, Placemark} from '@pbe/react-yandex-maps';
import "./index.css"
import restroomImage from '../../../assets/restroom.png'

const Map = () => {
    return (
        <YMaps class={"map"}>
            <YMap
                defaultState={{center: [52.75, 52.75], zoom: 5}}
                height="100vh"
                width="100vw"
                modules={['control.ZoomControl']}
            >
                <ZoomControl options={{position: {right: "30px", bottom: "50px"}}}/>
                <Placemark
                    geometry={[55.68, 37.73]}
                    options={{
                        iconLayout: "default#image",
                        iconImageHref: restroomImage,
                        iconImageSize: [32, 32],
                        iconContentOffset: [32, 32]
                    }}
                />
            </YMap>
        </YMaps>
    );
};

export default Map;
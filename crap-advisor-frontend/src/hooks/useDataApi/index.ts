import React from "react";
import {AxiosError, AxiosResponse} from "axios";
import {RequestType, Location} from "app/typings";
import {Api} from "app/api";
import {useParams} from "react-router-dom";
import {defaultLocation} from "app/constants";

type A<T> =
    | { type: "PENDING" }
    | { type: "FETCH_SUCCESS"; payload: T[] }
    | { type: "FETCH_FAILURE" };

type State<T> = {
    data: T[];
    isLoading: boolean;
    isError: boolean;
};

interface IUseDataApiProps<T> {
    state: State<T>;
    setUrl: React.Dispatch<React.SetStateAction<string>>;
    selectedRestroom: Location
}

const createDataFetchReducer =
    <T>() =>
        (state: State<T>, action: A<T>): State<T> => {
            switch (action.type) {
                case "PENDING":
                    return {
                        ...state,
                        isLoading: true,
                        isError: false,
                    };
                case "FETCH_SUCCESS":
                    return {
                        ...state,
                        isLoading: false,
                        isError: false,
                        data: action.payload,
                    };
                case "FETCH_FAILURE":
                    return {
                        ...state,
                        isLoading: false,
                        isError: true,
                    };
                default:
                    throw new Error();
            }
        };

export const useDataApi = <T>(
    initialUrl: string,
    initialData: T[],
    request: RequestType,
    body?: any
): IUseDataApiProps<T> => {

    const initialState: State<T> = {
        isLoading: false,
        isError: false,
        data: [],
    };
    const [url, setUrl] = React.useState<string>(initialUrl);
    const [selectedRestroom, setSelectedRestroom] = React.useState<Location>(defaultLocation);
    const dataFetchReducer = createDataFetchReducer<T>();
    const [state, dispatch] = React.useReducer(dataFetchReducer, initialState);
    const {restroomId} = useParams<{ restroomId: string }>();

    React.useEffect(() => {
        let didCancel: boolean = false;
        const fetchData = async () => {
            dispatch({type: "PENDING"});
            try {
                switch (request) {
                    case "GET": {
                        if (!url.includes("null")) {
                            const {data}: AxiosResponse = await Api.getFromAPI(url);
                            if (!didCancel) {
                                dispatch({type: "FETCH_SUCCESS", payload: data});
                                if (restroomId) {
                                    console.log(state.data)
                                    // const currentLocation: Location = (state.data.find((restroom: any) => String(restroom.id) === restroomId) as any)?.location;
                                    // console.log(currentLocation)
                                    // setSelectedRestroom(currentLocation)
                                }
                            }
                        }
                        break;
                    }
                    case "POST": {
                        const {data}: AxiosResponse = await Api.postFromAPI(body, url);
                        break;
                    }
                    case "PUT": {
                        const {data}: AxiosResponse = await Api.postFromAPI(body, url);
                        break;
                    }
                    default: {
                        dispatch({type: "FETCH_SUCCESS", payload: initialData});
                    }
                }
            } catch (e: unknown) {
                if (!didCancel) {
                    console.error(e as AxiosError);
                }
            }
        };
        fetchData();

        return () => {
            didCancel = true;
        };
    }, [url]);

    return {state, setUrl, selectedRestroom};
};

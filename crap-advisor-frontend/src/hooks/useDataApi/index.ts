import React, {useEffect} from "react";
import {AxiosError, AxiosResponse} from "axios";
import {RequestType} from "../../app/typings";
import {Api} from "../../app/api";

type TUseDataApi<T> = {
    data: T[];
    isLoading: boolean;
    isError: boolean;
}

export const useDataApi = <T>(initialUrl: string, initialData: T[], request: RequestType, body?: any): TUseDataApi<T>[]  => {
    const [data, setData] = React.useState<T[]>(initialData);
    const [url, setUrl] = React.useState<string>(initialUrl);
    const [isLoading, setLoading] = React.useState<boolean>(false);
    const [isError, setIsError] = React.useState<boolean>(false);

    useEffect(() => {
        const fetchData = async () => {
            setIsError(false);
            setLoading(true);
            try {
                switch (request) {
                    case "GET": {
                        const {data}: AxiosResponse = await Api.getFromAPI(url);
                        setData(data);
                        break;
                    }
                    case "POST": {
                        const {data}: AxiosResponse = await Api.postFromAPI(body, url);
                        setData(data);
                        break;
                    }
                    case "PUT": {
                        const {data}: AxiosResponse = await Api.postFromAPI(body, url);
                        setData(data);
                        break;
                    }
                    default: {
                        setData(initialData);
                    }
                }
            } catch (e: unknown) {
                console.error(e as AxiosError);
                setIsError(true);
            } finally {
                setLoading(false);
            }
        }
        fetchData();
    }, [url]);

    return [{data, isLoading, isError}];
}
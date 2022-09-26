import axios, { AxiosResponse } from 'axios';

import { Primitive } from '../typings';
import { params2form } from 'utils';
import {apiUrl} from "../constants";

const server = axios.create({
    baseURL: apiUrl
});

const generateAPIParams = (
    options: Record<string, Primitive | Primitive[]> | null | undefined
) => {
    const headers = {};
    const query = options ? `?${params2form(options)}` : '';
    return { headers, query };
};

export class Api {
    static getFromAPI(
        endpoint: string,
        options?: Record<string, Primitive | Primitive[]> | null
    ): Promise<AxiosResponse<unknown>> {
        const { headers, query } = generateAPIParams(options);
        return server.get(`${endpoint}${query}`, { headers });
    }

    static postFromAPI(
        body: Record<string, Primitive | Primitive[]>,
        endpoint: string,
        options?: Record<string, Primitive | Primitive[]> | null
    ): Promise<AxiosResponse<unknown>> {
        const { headers, query } = generateAPIParams(options!);
        return server.post(`${endpoint}${query}`, body, { headers });
    }
    static putFromAPI<T>(
        body: Record<string, Primitive | Primitive[]> | null,
        endpoint: string,
        options?: Record<string, Primitive | Primitive[]> | null,
    ): Promise<AxiosResponse<T>> {
        const { headers = {}, query = '' } = options
            ? generateAPIParams(options)
            : {};
        return server.put(`${endpoint}${query}`, body, { headers });
    }
}

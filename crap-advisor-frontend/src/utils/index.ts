import {Primitive} from "../app/typings";
import {isNil} from 'lodash';

export const params2form = (
    payload: Record<string, Primitive | Primitive[]>
): string =>
    Object.keys(payload)
        .filter((key) => !isNil(payload[key]))
        .map((key) => {
            if (Array.isArray(payload[key])) {
                return (payload[key] as Primitive[])
                    .filter((item) => !isNil(item))
                    .map(
                        (item) => `${encodeURIComponent(key)}=${encodeURIComponent(item)}`
                    )
                    .join('&');
            }
            return `${encodeURIComponent(key)}=${encodeURIComponent(
                payload[key] as Primitive
            )}`;
        })
        .join('&');

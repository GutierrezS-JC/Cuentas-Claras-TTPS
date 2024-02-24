import { jwtDecode } from "jwt-decode";

export const decodeJwt = (token: string): any => {
  try {
    return jwtDecode(token);
  } catch (error) {
    console.log(error);
    return null;
  }
};
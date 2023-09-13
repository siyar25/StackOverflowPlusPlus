export const isUserSignedIn = () => {
    return !!JSON.parse(window.localStorage.getItem("loginInfo"))
}

export const getSignedInUserObject = () => {
    return JSON.parse(window.localStorage.getItem("loginInfo"))
}

export const setSignedInUser = userDTO => {
    window.localStorage.setItem("loginInfo", JSON.stringify(userDTO));
}

export const signUserOut = () => {
    window.localStorage.setItem("loginInfo", null);
}

export const isSignedInUserAdmin = () => {
    return JSON.parse(window.localStorage.getItem("loginInfo")).isAdmin === true;
}
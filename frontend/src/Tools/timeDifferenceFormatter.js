export const timeDifferenceFormatter = date => {
    const relativeTimeFormatter = new Intl.RelativeTimeFormat('en', { numeric: "auto" });
    const now = new Date();
    const diffType = Math.abs(date - now) > 1000 * 60 * 60 * 24 ? "days" :
        Math.abs(date - now) > 1000 * 60 * 60 ? "hours" :
            "minutes";

    const dateDiff = diffType === "days" ? (date - now) / 1000 / 60 / 60 / 24 : diffType === "hours" ? (date - now) / 1000 / 60 / 60 : (date - now) / 1000 / 60;

    return relativeTimeFormatter.format(Math.floor(dateDiff) + 1, diffType);
}
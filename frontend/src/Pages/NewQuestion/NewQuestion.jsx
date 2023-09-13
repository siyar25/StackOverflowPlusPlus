import { useEffect } from "react";
import { useNavigate } from "react-router-dom"
import { isUserSignedIn } from "../../Tools/userFunctions";
import "./NewQuestion.css"

export default function NewQuestion() {
    const navigate = useNavigate();

    useEffect( () => {
        window.document.title = "New Question - Stackoverflow++"
    }, []);

    const onSubmit = e => {
        e.preventDefault();

        const entries = [...new FormData(e.target).entries()];
        const newQuestion = entries.reduce((acc, entry) => {
            const [k, v] = entry;
            acc[k] = v;
            return acc;
        }, {});

        fetch("/api/questions/", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...newQuestion,
                user_id: JSON.parse(window.localStorage.getItem("loginInfo")).id
            })
        })
        .then(res => res.json())
        .then(data => navigate("/question/" + data))
    }

    return <>
        {
            !isUserSignedIn() ?
                <>
                    <h3>You need to be signed in to ask a new question.</h3>
                    <p><b className="clickable" onClick={() => navigate("/signin")}>Sign in</b> or <b className="clickable" onClick={() => navigate("/register")}>Register</b></p>
                </>
                :
                <>
                    <h2>New Question</h2>
                    <form className="new-q-form" onSubmit={onSubmit}>
                        <div className="label" htmlFor="title">Title</div>
                        <input type="text" name="title" />

                        <div className="label" htmlFor="description">Description</div>
                        <textarea rows={5} name="description" />
                        <button type="submit">Submit</button>
                    </form>
                </>
        }
    </>
}
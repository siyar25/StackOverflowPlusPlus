import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AuthPages.css";
import logo from "../../Components/logo-wide.png";

export default function SignIn() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    window.document.title = "Sign in - Stackoverflow++";
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await fetch(
      `/api/user/auth?name=${username}&password=${password}&colorHex=0000`
    );
    try {
      const data = await res.json();
      window.localStorage.setItem("loginInfo", JSON.stringify(data));
      setErrorMessage("");
      history.back();
    } catch (error) {
      setErrorMessage("Username and password match not found."); // TODO: értelmesen
    }
  };

  return (
    <>
      <img
        className="logo-img clickable"
        src={logo}
        alt="Stackoverflow++ logo"
        onClick={() => navigate("/")}
      />
      <h2>
        Sign in to
        <br />
        <i>Stackoverflow++</i>
      </h2>
      <div className="authcard">
        <form>
          <div className="label">Username</div>
          <div>
            <input type="text" onInput={(e) => setUsername(e.target.value)} />
          </div>

          <div className="label">Password</div>
          <div>
            <input
              type="password"
              onInput={(e) => setPassword(e.target.value)}
            />
          </div>

          <div className="error message">{errorMessage}</div>

          <button type="submit" className="authbutton" onClick={handleSubmit}>
            Sign in
          </button>

          <div className="message">
            Don't have an account?{" "}
            <b className="clickable" onClick={() => navigate("/register")}>
              Register
            </b>
            .
          </div>
        </form>
      </div>
    </>
  );
}

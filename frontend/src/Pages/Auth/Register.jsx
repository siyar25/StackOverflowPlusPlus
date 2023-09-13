import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { setSignedInUser } from "../../Tools/userFunctions";
import logo from "../../Components/logo-wide.png";
import { CirclePicker } from "react-color";

export default function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [passwordAgain, setPasswordAgain] = useState("");
  const [errorMessage, setErrorMessage] = useState(
    "Don't use your real passwords, the passwords on this page are not yet encrypted!"
  );
  const navigate = useNavigate();

  const [pickedColor, setPickedColor] = useState("");

  useEffect(() => {
    window.document.title = "Register - Stackoverflow++";
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password === passwordAgain) {
      const res = await fetch(`/api/user/`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: username,
          password: password,
          colorHex: pickedColor,
        }),
      });

      try {
        const user_id = await res.json();
        const userData = await (await fetch("/api/user/" + user_id)).json();
        setSignedInUser(userData);
        navigate("/user/" + user_id);
      } catch (error) {
        setErrorMessage("Failed to register. Maybe username already exists?");
      }
    } else {
      setErrorMessage("Passwords don't match!");
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
        Register to
        <br />
        <i>Stackoverflow++</i>
      </h2>
      <div
        style={{
          background: `radial-gradient(circle at center, ${pickedColor} 30%, black 185%)`,
          boxShadow: " 0px 5px 3px #000000",
        }}
        className="authcard"
      >
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

          <div className="label">Password again:</div>
          <div>
            <input
              type="password"
              onInput={(e) => setPasswordAgain(e.target.value)}
            />
          </div>
          <div className="label">Pick your color:</div>
          <div id="picker">
            <CirclePicker
              width="100%"
              color={pickedColor}
              onChangeComplete={(color) => {
                setPickedColor(color.hex);
              }}
            />
          </div>
          <div className="error message">{errorMessage}</div>
          <button type="submit" className="authbutton" onClick={handleSubmit}>
            Register
          </button>
          <div className="message">
            Already have an account?{" "}
            <b className="clickable" onClick={() => navigate("/signin")}>
              Sign&nbsp;in
            </b>
            .
          </div>
        </form>
      </div>
    </>
  );
}

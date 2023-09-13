import { Outlet, useNavigate } from "react-router-dom";
import "./Layout.css";

export default function Layout() {
  const navigate = useNavigate();
  return (
    <>
      <header>
        <div className="logo clickable" onClick={() => navigate("/")}>
          Stackoverflow++
        </div>
        <div className="clickable" onClick={() => navigate("/users")}>
          Users
        </div>
        {JSON.parse(window.localStorage.getItem("loginInfo")) ? (
          <>
            <div className="clickable" onClick={() => navigate("/new")}>
              New Question
            </div>
            <div
              className="clickable"
              onClick={() =>
                navigate(
                  "/user/" +
                    JSON.parse(window.localStorage.getItem("loginInfo")).id
                )
              }
            >
              {JSON.parse(window.localStorage.getItem("loginInfo")).name}'s
              Profile
            </div>
          </>
        ) : (
          <>
            <div className="clickable" onClick={() => navigate("/signin")}>
              Sign in
            </div>
            <div className="clickable" onClick={() => navigate("/register")}>
              Register
            </div>
          </>
        )}
      </header>
      <Outlet />
    </>
  );
}

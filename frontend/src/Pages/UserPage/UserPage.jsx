import { useNavigate, useParams } from "react-router-dom";
import UserAvatar from "../../Components/UserAvatar";
import { useEffect, useState } from "react";
import Loading from "../../Components/Loading";
import "./UserPage.css";
import QuestionsOption from "./QuestionsOption";
import AnswersOption from "./AnswersOption";
import QARatio from "../../Components/QARatio";
import { signUserOut } from "../../Tools/userFunctions";

export default function UserPage() {
  const { id } = useParams();
  const [userPageDTO, setUserPageDTO] = useState(null);
  const [activeOption, setActiveOption] = useState("Questions");
  const [refresh, setRefresh] = useState(false);
  const navigate = useNavigate();

  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  function handleOptions(e) {
    e.target.innerText === "Questions"
      ? setActiveOption("Questions")
      : setActiveOption("Answers");
  }

  useEffect(() => {
    async function fetchData() {
      const response = await fetch("/api/user/" + id);
      const data = await response.json();
      setUserPageDTO(data);
      window.document.title = `User ${data.name} - Stackoverflow++`;
    }
    fetchData();
  }, [id, refresh]);

  const signOut = () => {
    window.localStorage.setItem("loginInfo", null);
    history.back();
  };

  const deleteUser = async () => {
    const resp = await fetch("/api/user/" + userPageDTO.id, {
      method: "DELETE",
    });
    const data = await resp.json();
    if (data) {
      signUserOut();
      navigate("/");
    } else {
      alert("Couldn't delete user");
    }
  };

  return userPageDTO ? (
    <>
      <div
        style={{
          background: `radial-gradient(circle at center, ${userPageDTO.colorHex} 80%, black 195%)`,
          boxShadow: " 0px 5px 3px #000000",
        }}
        className="user-card"
      >
        <div className="user-avatar">
          <UserAvatar user={userPageDTO} isBigSize />
        </div>
        <div className="user-details">
          <div className="user-name">{userPageDTO.name}</div>
          <div className="user-registration">
            Joined: <i>{new Date(userPageDTO.registration).toLocaleString()}</i>
          </div>
          <div>
            Q/A: <QARatio user={userPageDTO} span />{" "}
          </div>
          {userPageDTO.isAdmin ? (
            <div className="user-registration">
              <i>{userPageDTO.name} is an admin.</i>
            </div>
          ) : (
            <></>
          )}
        </div>
      </div>

      <div className="options">
        <div className="button clickable" onClick={(e) => handleOptions(e)}>
          Questions
        </div>
        <div className="button clickable" onClick={(e) => handleOptions(e)}>
          Answers
        </div>
      </div>
      <div className="options">
        {JSON.parse(window.localStorage.getItem("loginInfo")) &&
        JSON.parse(window.localStorage.getItem("loginInfo")).id ===
          userPageDTO.id ? (
          <>
            <div className="button clickable warning" onClick={deleteUser}>
              Delete account
            </div>
            <div className="button clickable" onClick={signOut}>
              Sign Out
            </div>
          </>
        ) : (
          <></>
        )}
      </div>
      <div className="user-option">
        {activeOption === "Questions" ? (
          <QuestionsOption user={userPageDTO} refresh={handleRefresh} />
        ) : (
          <AnswersOption user={userPageDTO} refresh={handleRefresh} />
        )}
      </div>
    </>
  ) : (
    <Loading />
  );
}

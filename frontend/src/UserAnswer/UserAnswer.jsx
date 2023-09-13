import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getSignedInUserObject } from "../Tools/userFunctions";
import { timeDifferenceFormatter } from "../Tools/timeDifferenceFormatter";
import UserAvatar from "../Components/UserAvatar";
import Loading from "../Components/Loading";
import "./UserAnswer.css";

export default function UserAnswer({ answerDTO, deleteAnswer, user }) {
  const navigate = useNavigate();
  const [userQuestion, setUserQuestion] = useState(null);

  useEffect(() => {
    async function fetchQuestionData() {
      const response = await fetch("/api/questions/" + answerDTO.question_id);
      const data = await response.json();
      setUserQuestion(data);
    }
    fetchQuestionData();
  }, []);

  return answerDTO && userQuestion ? (
    <>
      <div className="answer-card">
        <div
          style={{
            background: `linear-gradient(to right, ${answerDTO.user.colorHex}30%, black 185%)`,
            color: "white",
          }}
          className="user-profile"
        >
          <div
            className="username clickable"
            onClick={() => navigate("/user/" + userQuestion.user.id)}
          >
            <UserAvatar username={userQuestion.user.name} />
            <p>{userQuestion.user.name}</p>
          </div>
        </div>

        <div className="answer-info">
          <div
            className="question-title"
            onClick={() => navigate("/question/" + userQuestion.id)}
          >
            <h2>{userQuestion.title}</h2>
          </div>

          <div className="answer user">
            <span
              className="clickable"
              onClick={() => navigate("/user/" + user.id)}
            >
              {user.name}
            </span>{" "}
            said:
          </div>
          <p>{answerDTO.answer}</p>
          <div className="answer infos">
            <div>
              {getSignedInUserObject()?.id === user.id ? (
                <div className="clickable" onClick={deleteAnswer}>
                  🗑️
                </div>
              ) : (
                ""
              )}
            </div>
            <div
              className="flex-end"
              title={new Date(answerDTO.created).toLocaleString()}
            >
              {timeDifferenceFormatter(new Date(answerDTO.created))}
            </div>
          </div>
        </div>
      </div>
    </>
  ) : (
    <Loading />
  );
}

import { useNavigate } from "react-router-dom";
import UserAvatar from "../../Components/UserAvatar";
import {
  getSignedInUserObject,
  isSignedInUserAdmin,
} from "../../Tools/userFunctions";
import { timeDifferenceFormatter } from "../../Tools/timeDifferenceFormatter";
import Vote from "../../Components/Vote";
import { useState } from "react";

export default function QuestionCardDetailed({
  questionPageDTO,
  deleteQuestion,
  refresh,
}) {
  const navigate = useNavigate();

  return (
    <div
      style={{
        background: `radial-gradient(circle at center, ${questionPageDTO.user.colorhex} 60%, black 195%)`,
        color: "white",
      }}
      className="card detailed"
    >
      <div
        className="username clickable"
        onClick={() => navigate("/user/" + questionPageDTO.user.id)}
      >
        <UserAvatar user={questionPageDTO.user} />
        <p>{questionPageDTO.user.name}</p>
      </div>
      <div className="question detailed">
        <h2>{questionPageDTO.title}</h2>
        <p>{questionPageDTO.description}</p>
        <div className="answer infos">
          <div>
            <Vote card={questionPageDTO} refresh={refresh} />
          </div>
          {getSignedInUserObject()?.id === questionPageDTO.user.id ||
          isSignedInUserAdmin() ? (
            <div className="clickable" onClick={deleteQuestion}>
              🗑️
            </div>
          ) : (
            <></>
          )}
          <div
            className="flex-end"
            title={new Date(questionPageDTO.created).toLocaleString()}
          >
            {timeDifferenceFormatter(new Date(questionPageDTO.created))}
          </div>
        </div>
      </div>
    </div>
  );
}

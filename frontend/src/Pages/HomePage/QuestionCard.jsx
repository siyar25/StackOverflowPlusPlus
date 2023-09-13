import { useNavigate } from "react-router-dom";
import UserAvatar from "../../Components/UserAvatar";
import Vote from "../../Components/Vote";
import { timeDifferenceFormatter } from "../../Tools/timeDifferenceFormatter";
import "./Question.css";

export default function QuestionCard({ question, refresh }) {
  const navigate = useNavigate();

  return (
    <div
      className="card clickable"
      style={{
        background: `radial-gradient(ellipse at center, ${question.user.colorhex} 60%, black 195%)`,
        boxShadow: " 0px 5px 3px #000000",
      }}
    >
      <div
        className="username"
        onClick={() => navigate("/user/" + question.user.id)}
      >
        <UserAvatar user={question.user} />
        <p>{question.user.name}</p>
      </div>
      <div
        className="question"
        onClick={() => navigate("/question/" + question.id)}
      >
        <h2>{question.title}</h2>
        <div className="question-infos">
          <p>
            <Vote card={question} refresh={refresh} />
          </p>
          <p>
            💬{" "}
            {question.answerCount > 0
              ? "Answers: " + question.answerCount
              : "No answers yet."}
          </p>
          <p title={new Date(question.created).toLocaleString()}>
            🕒 {timeDifferenceFormatter(new Date(question.created))}
          </p>
        </div>
      </div>
    </div>
  );
}

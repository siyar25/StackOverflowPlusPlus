import { useEffect, useState } from "react";
import QuestionCard from "./QuestionCard";
import "./HomePage.css";
import Loading from "../../Components/Loading";

export default function HomePage() {
  const [questions, setQuestions] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  useEffect(() => {
    async function fetchData() {
      const response = await fetch("/api/questions/all");
      const data = await response.json();
      data.sort(
        (questionA, questionB) =>
          new Date(questionB.created) - new Date(questionA.created)
      );
      setQuestions(data);
    }
    fetchData();
    window.document.title = "Stackoverflow++";
  }, [refresh]);

  return (
    <div className="card-holder">
      {questions ? (
        <>
          {questions.map((question) => (
            <QuestionCard
              key={question.id}
              question={question}
              refresh={handleRefresh}
            />
          ))}
        </>
      ) : (
        <Loading />
      )}
    </div>
  );
}

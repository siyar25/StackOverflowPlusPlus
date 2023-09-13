import React from "react";
import { useEffect, useState } from "react";
import Loading from "../../Components/Loading";
import QuestionCard from "../HomePage/QuestionCard";

export default function QuestionsOption({ user, refresh }) {
  const [questions, setQuestions] = useState(null);

  useEffect(() => {
    async function fetchData() {
      const response = await fetch("/api/user/" + user.id + "/questions");
      const data = await response.json();
      setQuestions(data);
    }
    fetchData();
  }, [refresh]);

  return questions ? (
    <div className="user-questions">
      {questions.map((question) => {
        return <QuestionCard key={question.id} question={question} refresh={refresh}/>;
      })}
    </div>
  ) : (
    <Loading />
  );
}

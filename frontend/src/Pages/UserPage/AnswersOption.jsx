import React from "react";
import { useEffect, useState } from "react";
import Loading from "../../Components/Loading";
import UserAnswer from "../../UserAnswer/UserAnswer";

export default function AnswersOption({ user }) {
  const [answers, setAnswers] = useState(null);

  useEffect(() => {
    async function fetchAnswerData() {
      const response = await fetch("/api/user/" + user.id + "/answers");
      const data = await response.json();
      setAnswers(data);
    }
    fetchAnswerData();
  }, []);

  return answers ? (
    <>
      {answers.map((answer) => {
        return <UserAnswer key={answer.id} answerDTO={answer} user={user} />;
      })}
    </>
  ) : (
    <Loading />
  );
}

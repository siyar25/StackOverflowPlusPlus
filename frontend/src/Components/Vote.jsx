import React from "react";
import { useState, useEffect } from "react";
import "./Vote.css";

export default function Vote({ card, refresh }) {
  function handleVote(e) {
    e.stopPropagation();
    let vote = null;
    e.target.id === "upVote"
      ? (vote = true)
      : e.target.id === "upVote-green"
      ? (vote = null)
      : (vote = false);

    if ("answer" in card) {
      fetch("/api/answers/vote", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          vote: vote,
          userId: JSON.parse(window.localStorage.getItem("loginInfo")).id,
          answerId: card.id,
        }),
      }).then(() => {
        refresh();
      });
    } else {
      fetch("/api/questions/vote", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          questionId: card.id,
          userId: JSON.parse(window.localStorage.getItem("loginInfo")).id,
          vote: vote,
        }),
      }).then(() => {
        refresh();
      });
    }
  }

  return (
    <>
      <span className="emoji" onClick={(e) => handleVote(e)}>
        <img
          id={card.upVoteCount > 0 ? "upVote-green" : "upVote"}
          src="https://cdn3.emoji.gg/emojis/8558-upvote.png"
          alt="High Quality Imgflip Upvote Blank Meme Template - Arrow Up@pngkey.com"
        ></img>
      </span>{" "}
      {card.upVoteCount}&emsp;
      <span className="emoji" onClick={(e) => handleVote(e)}>
        <img
          id={card.downVoteCount > 0 ? "downVote-red" : "downVote"}
          src="https://png2.cleanpng.com/sh/b7558cb7e1d575c4f10ea621f58b6081/L0KzQYm3VMA5N6pBiZH0aYP2gLBuTgBqdJYyh9g2cHByPbb0jBpqNaR5gdV0ZYKwdrbqhgMubZ50jNtsb36wgLF2kL02aZRmfKs9OXW2SYTtVr4xO2k8UaI6NkG4QoS5VMM0O2gAS6Q7LoDxd1==/kisspng-pile-of-poo-emoji-sticker-feces-emoticon-poop-5acad949e393f6.0387901615232433379322.png"
          alt="Poop - Poop Emoji Transparent Background@clipartmax.com"
        />
      </span>{" "}
      {card.downVoteCount}
    </>
  );
}

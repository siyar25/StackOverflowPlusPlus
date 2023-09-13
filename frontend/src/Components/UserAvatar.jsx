import "./avatar.css";

export default function UserAvatar({ user, isBigSize = false }) {
  return (
    <div className={isBigSize ? "letter-avatar-big" : "letter-avatar"}>
      {user.name.slice(0, 1)}
      {user.isAdmin ? <span id="isAdmin">👮</span> : <></>}
    </div>
  );
}

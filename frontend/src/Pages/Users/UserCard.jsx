import { useNavigate } from "react-router-dom";
import QARatio from "../../Components/QARatio";
import UserAvatar from "../../Components/UserAvatar";

export default function UserCard({ userDTO }) {
  const navigate = useNavigate();
  console.log(userDTO);
  return (
    <div
      style={{
        background: `radial-gradient(ellipse at center, ${userDTO.colorHex} 70%, black 185%)`,
        boxShadow: " 0px 5px 3px #000000",
      }}
      className="usercard"
      onClick={() => navigate("/user/" + userDTO.id)}
    >
      <UserAvatar user={userDTO} />

      <b>{userDTO.name}</b>

      <QARatio user={userDTO} />
    </div>
  );
}

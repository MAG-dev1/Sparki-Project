import styles from "./nav.module.css";
import { useNavigate } from "react-router-dom";

export default function Nav({onPauseTimer}) {
  const navigate = useNavigate();
  return (
    <header>
      <nav className={styles.nav}>
        <span id="spanUsername" className={styles.nav_object}>
          {localStorage.getItem("username")}
        </span>
        <ul
          className={styles.nav_object}
          onClick={() => navigate("/dashboard")}
        >
          Home
        </ul>
        <ul className={styles.nav_object} onClick={() => navigate("/subjects")}>
          Materias
        </ul>
        <ul className={styles.nav_object} onClick={() => navigate("/pomodoro")}>Pomodoro</ul>
      </nav>
    </header>
  );
}

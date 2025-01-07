import { useEffect, useState } from "react";
import Nav from "./Nav";
import styles from "./pomodoro.module.css";

export default function Pomodoro() {
  const [time, setTime] = useState(25 * 60);
  const [isRunning, setIsRunning] = useState(false);

  useEffect(() => {
    let timer;
    console.log(isRunning);
    console.log(time);
    if (isRunning) {
      timer = setInterval(() => {
        setTime((prevTime) => (prevTime > 0 ? prevTime - 1 : 0));
      }, 1000);
    } else {
      clearInterval(timer);
    }
    return () => clearInterval(timer);
  }, [isRunning]);

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${String(minutes).padStart(2, "0")}:${String(secs).padStart(2, "0")}`;
  };

  const toggleTimer = () => setIsRunning((prev) => !prev);

  const resetTimer = () => {
    setIsRunning(false);
    setTime(25 * 60); // Reinicia a 25 minutos.
  };

  return (
    <>
      <Nav onPauseTimer={time}/>
      <div className={styles.globalContainer}>
        <div className={styles.container}>
          <h1>Pomodoro Timer</h1>
          <div className={styles.timer}>{formatTime(time)}</div>

          <div className={styles.controls}>
            <button className={styles.button} onClick={resetTimer}>
              Reset
            </button>
            <button className={styles.button} onClick={toggleTimer}>
              {isRunning ? "Pause" : "Start"}
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

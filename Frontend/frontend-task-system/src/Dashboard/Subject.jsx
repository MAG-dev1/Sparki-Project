import { useNavigate } from "react-router-dom";
import styles from "./Subject.module.css";
import { useState } from "react";
import { useAppContext } from "../AppContext";
import Nav from "./Nav";

export default function Subject() {
  const navigate = useNavigate();
  const { subjects, setSubjects } = useAppContext();

  return (
    <>
      <Nav/>
      <main className={styles.main}>
        <table className={styles.subjects_table}>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Descripcion</th>
              <th className={styles.phonehidden}>semestre</th>
              <th className={styles.phonehidden}>Puntuacion</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody className={styles.subjects_table}>
            {subjects.map((subject, index) => (
              <tr key={index}>
                <td>{subject.name}</td>
                <td>{subject.description}</td>
                <td className={styles.phonehidden}>{subject.semester}</td>
                <td className={styles.phonehidden}>{subject.grade}</td>
                <td>{subject.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </main>
    </>
  );
}

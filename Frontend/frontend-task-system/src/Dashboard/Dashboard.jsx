import { useEffect, useRef, useState } from "react";
import styles from "./Dashboard.module.css";

function Dasboard() {
  const taskSectionRef = useRef(null);
  const task_url = import.meta.env.VITE_BACKEND_TASK;
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetch(`${task_url}/tasks`, {
      method: "GET",
      headers: {
        Accept: "*/*",
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("invalid request");
        }
        return response.json();
      })
      .then((data) => {
        if (data) {
          console.log(data);
          //let tasks = setdata(data);
          setTasks(data);
          localStorage.setItem("tasks", JSON.stringify(tasks));
        } else {
          console.log("error to get the token");
        }
      })
      .catch((e) => {
        console.log("error:", e);
      });
  }, []);

  function setdata(newTasks) {
    let tasks = [];
    newTasks.forEach((taskData) => {
      const taskRow = document.createElement("tr");

      taskRow.innerHTML = `
          <td>${taskData.description}</td>
          <td>${taskData.subject}</td>
          <td> ${taskData.expired_date}</td>
          <td>${taskData.score}</td>
          <td>${taskData.type}</td>
        `;

      if (taskSectionRef.current) {
        taskSectionRef.current.appendChild(taskRow);
      }
      tasks.push(taskData);
    });
    return tasks;
  }

  function HandleaddTaskButton(e) {
    e.preventDefault();
    toggleModal();
  }

  function toggleModal() {
    setIsModalOpen(!isModalOpen);
  }
  function handleSubmit(e) {
    e.preventDefault();
    const taskData = {
      name: e.target.name.value,
      description: e.target.description.value,
      type: e.target.type.value,
      semester: e.target.semester.value,
      days: parseInt(e.target.days.value, 10),
      score: parseInt(e.target.score.value, 10),
    };

    console.log("Task Data:", taskData);
    let name_subject = e.target.subject.value;
    console.log(localStorage.getItem("authToken"));
    execute(taskData, name_subject);

    toggleModal();
    e.target.reset();
  }

  function execute(data, name_subject) {
    fetch(
      `${task_url}/tasks/associate?name_subject=${encodeURIComponent(name_subject)}`,
      {
        method: "POST",
        headers: {
          Accept: "*/*",
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("authToken")}`,
        },
        body: JSON.stringify(data),
      }
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        addTask(data);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  function addTask(taskData) {
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    tasks.push(taskData);
    localStorage.setItem("tasks", JSON.stringify(tasks));

    let taskRow = document.createElement("tr");

    taskRow.innerHTML = `
          <td>${taskData.description}</td>
          <td>${taskData.subject}</td>
          <td> ${taskData.expired_date}</td>
          <td>${taskData.score}</td>
          <td>${taskData.type}</td>
        `;

    if (taskSectionRef.current) {
      taskSectionRef.current.appendChild(taskRow);
    }
  }

  return (
    <>
      <header>
        <span id="spanUsername">Student Dashboard</span>
        <button>☰</button>
      </header>
      <main className={styles.main_content} id="content">
        <section className={styles.cards}>
          <div className={styles.card}>
            <h3>Tareas Pendientes</h3>
            <p>5 Tareas</p>
          </div>
          <div className={styles.card}>
            <h3>Materias Activas</h3>
            <p>3 Materias</p>
          </div>
          <div className={styles.card}>
            <h3>Progreso Semanal</h3>
            <p>70% Completado</p>
          </div>
          <button
            className={styles.button_add_task}
            id="button_add_task"
            type="submit"
            onClick={HandleaddTaskButton}
          >
            añadir tarea
          </button>
        </section>
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Materia</th>
              <th>Fecha Límite</th>
              <th>Tarea</th>
              <th>Tipo</th>
            </tr>
          </thead>
          <tbody id="tasks-container" ref={taskSectionRef}>
            {tasks.map((task, index) => (
              <tr key={index}>
                <td>{task.name}</td>
                <td>{task.subject}</td>
                <td>{task.expired_date}</td>
                <td>{task.description}</td>
                <td>{task.type}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </main>
      {isModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modal_content}>
            <span className={styles.close} onClick={toggleModal}>
              &times;
            </span>
            <h2>Crear Tarea</h2>
            <form id="create-task-form" onSubmit={handleSubmit}>
              <div>
                <label htmlFor="name">Task Name:</label>
                <input
                  type="text"
                  id="name"
                  name="name"
                  placeholder="Enter task name"
                  required
                />
              </div>
              <div>
                <label htmlFor="description">Description:</label>
                <input
                  type="text"
                  id="description"
                  name="description"
                  placeholder="Enter task description"
                  required
                />
              </div>
              <div>
                <label htmlFor="type">Type:</label>
                <input
                  type="text"
                  id="type"
                  name="type"
                  placeholder="Enter task type"
                  required
                />
              </div>
              <div>
                <label htmlFor="semester">Semester:</label>
                <input
                  type="text"
                  id="semester"
                  name="semester"
                  placeholder="Enter semester"
                  required
                />
              </div>
              <div>
                <label htmlFor="days">Days:</label>
                <input
                  type="number"
                  id="days"
                  name="days"
                  placeholder="Enter number of days"
                  required
                />
              </div>
              <div>
                <label htmlFor="score">Score:</label>
                <input
                  type="number"
                  id="score"
                  name="score"
                  placeholder="Enter score"
                  required
                />
              </div>
              <div>
                <label htmlFor="subject">Subject:</label>
                <input
                  type="text"
                  id="subject"
                  name="subject"
                  placeholder="Enter subject"
                  required
                />
              </div>
              <button type="submit">Add Task</button>
            </form>
          </div>
        </div>
      )}
    </>
  );
}

export default Dasboard;

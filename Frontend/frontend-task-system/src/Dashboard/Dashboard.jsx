import { useEffect, useRef, useState } from "react";
import styles from "./Dashboard.module.css";
import createTask from "./createTask";
import getTasks from "./getTasks";

function Dasboard() {
  const taskSectionRef = useRef(null);
  const task_url = import.meta.env.VITE_BACKEND_TASK;

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [tasks, setTasks] = useState([]);

  const [isModalOpenSubject, setIsModalOpenSubject] = useState(false);

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

  function HandleaddTaskButton(e) {
    e.preventDefault();
    toggleTaskModal();
  }

  function toggleTaskModal() {
    setIsModalOpen(!isModalOpen);
  }

  const HandleAddSubjectButton = (e) => {
    e.preventDefault();
    setIsModalOpenSubject(!isModalOpenSubject);
  }


  function handleSubmit(e) {

    e.preventDefault();

    const taskData = {
      name: e.target.name.value,
      description: e.target.description.value,
      type: e.target.type.value,
      semester: e.target.semester.value,
      days: parseInt(e.target.days.value, 10),
      importance: parseInt(e.target.importance.value, 10),
    };

    let name_subject = e.target.subject.value;
    let data = createTask(taskData, name_subject);

    setTasks((prevTasks) => [...prevTasks, data]);
    toggleTaskModal();
    
    e.target.reset();
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
            <p>{tasks.length} Tareas</p>
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
          <button
             className={styles.button_add_subject}
            type="submit"
            onClick={HandleAddSubjectButton}
          >
            añadir Materia
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
            <span className={styles.close} onClick={toggleTaskModal}>
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
                <select id="type" name="type" required>
                  <option value="DAYLY">DAYLY</option>
                  <option value="WEEKLY">WEEKLY</option>
                  <option value="MONTHLY">MONTHLY</option>
                  <option value="ANNUAL">ANNUAL</option>
                </select>
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
                <label htmlFor="importance">Importance:</label>
                <input
                  type="number"
                  id="importance"
                  name="importance"
                  placeholder="Enter importance (if needed)"
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
      {isModalOpenSubject && (
         <div className={styles.modal}>
          <div className={styles.modal_content}>
            <span className={styles.close} onClick={HandleAddSubjectButton}>
              &times;
            </span>
            <h2>Crear Materia</h2>
            <form id="create-subject-form" onSubmit={handleSubmit}>
              <div>
                <label htmlFor="name">Nombre de la materia:</label>
                <input
                  type="text"
                  id="name"
                  name="name"
                  placeholder="Enter subject name"
                  required
                />
              </div>
              <div>
                <label htmlFor="description">Semestre:</label>
                <input
                  type="text"
                  id="semester"
                  name="semester"
                  placeholder="Enter semester"
                  required
                />
              </div>
              <div>
                <label htmlFor="type">Estado:</label>
                <select id="type" name="type" required>
                  <option value="APPROVED">APPROVED</option>
                  <option value="DISAPPROVED">DISAPPROVED</option>
                  <option value="IN_PROGRESS">IN_PROGRESS</option>       
                </select>
              </div>
              <div>
                <label htmlFor="grade">Grade:</label>
                <input
                  type="text"
                  id="grade"
                  name="grade"
                  placeholder="Enter grade (if needed)"
                  required
                />
              </div>
              <button type="submit">Add Subject</button>
            </form>
          </div>
        </div>
      )}
    </>
  );
}

export default Dasboard;

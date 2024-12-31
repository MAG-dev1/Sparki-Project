import { useEffect, useRef, useState } from "react";
import styles from "./Dashboard.module.css";
import createTask from "./operations/createTask";
import createSubject from "./operations/createSubject";
import loadTasks from "./operations/loadTasks";
import loadSubjects from "./operations/loadSubjects";
import { useNavigate } from "react-router-dom";

function Dasboard() {

 

  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [tasks, setTasks] = useState([]);

  const [isModalOpenSubject, setIsModalOpenSubject] = useState(false);
  const [subjects, setSubjects] = useState([]);
  const taskSectionRef = useRef(null);


  const handleLogout = () => {
  
    localStorage.removeItem("authToken");
    localStorage.removeItem("username");
    setSubjects([]);
    setTasks([]);

    navigate("/");
  };

  useEffect(() => {

    async function loadTask () {
      try {
        let data = await loadTasks();
        if(data){
          setTasks(data);
        }
      } catch (error) {
        console.log(error);
      }
    }

    async function loadSubs () {
      try {
        let data  = await loadSubjects();
        if(data){
          setSubjects(data);
        }
        
      } catch (error) {
        console.log(error);
      }
    }

    loadSubs();
    loadTask();

    console.log(subjects);
    const handlePopState = () => {
      handleLogout();
    };

    // Agrega el listener al montar el componente
    window.addEventListener("popstate", handlePopState);

    // Limpia el listener al desmontar el componente
    return () => {
      window.removeEventListener("popstate", handlePopState);
    };

   

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
  };

  async function handleSubmit(e) {
    e.preventDefault();

    let taskData = {
      name: e.target.name.value,
      description: e.target.description.value,
      type: e.target.type.value,
      days: parseInt(e.target.days.value, 10),
      importance: parseInt(e.target.importance.value, 10),
    };

    let name_subject = e.target.subject.value;
    let data = await createTask(taskData, name_subject);

    if (data) {
      setTasks((prevTasks) => [...prevTasks, data]);
      toggleTaskModal();

      e.target.reset();
    } else {
      console.error("Error: Task creation failed");
    }
  }

  async function createSubjectHandler(e) {
    e.preventDefault();

    let data = {
      username: localStorage.getItem("username"),
      name: e.target.name.value,
      semester: e.target.semester.value,
      status: e.target.status.value,
      description: e.target.description.value,
      grade: e.target.grade.value,
    };

    let subject = await createSubject(data);

    if (subject) {
      setSubjects((prevSubjects) => [...prevSubjects, subject]);
      console.log(subjects);

      e.target.reset();
    } else {
      console.error("Error: Subject creation failed");
    }

    setIsModalOpenSubject(!isModalOpenSubject);
  }

  function handleSubjectChange(e){
    const selectedSubject = e.target.value;
    console.log(selectedSubject); 
  }

  return (
    <>
      <header>
        <span id="spanUsername">{localStorage.getItem('username')} Dashboard</span>
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
            Añadir tarea
          </button>
          <button
            className={styles.button_add_subject}
            type="submit"
            onClick={HandleAddSubjectButton}
          >
            Añadir Materia
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
                <select name="subject" id="subject" onChange={handleSubjectChange}>
                  {subjects != null ?subjects.map((sub, index) => (
                    <option key={index} value={sub.name}>{sub.name}</option>
                  )): <option>no subjects</option>}
                </select>
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
            <form id="create-subject-form" onSubmit={createSubjectHandler}>
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
                <label htmlFor="description">Descripcion:</label>
                <input
                  type="text"
                  id="description"
                  name="description"
                  placeholder="Enter description"
                  required
                />
              </div>
              <div>
                <label htmlFor="semester">Semestre:</label>
                <input
                  type="text"
                  id="semester"
                  name="semester"
                  placeholder="Ej: 1-2024"
                  pattern="[1-2]-\d{4}"
                  title="Debe ser en formato 1-XXXX o 2-XXXX"
                  required
                />
              </div>
              <div>
                <label htmlFor="status">Estado:</label>
                <select id="status" name="status" required>
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

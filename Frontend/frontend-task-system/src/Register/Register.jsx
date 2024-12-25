import React from "react";
import styles from "./Register.module.css";
import { useNavigate } from "react-router-dom";

const register_url = import.meta.env.VITE_BACKEND_LOGIN;
function Register() {
  const navigate = useNavigate();
  const handleRegisterButton = () => {
    setUserData();
    navigate("/");
  };

  const handleLoginButton = () => {
    navigate("/");
  }
  return (
    <div className={styles.register_container}>
      <form className={styles.register_form}>
        <h2>Register</h2>
        <div className={styles.form_group}>
          <label htmlFor="name">Name</label>
          <input
            type="text"
            id="name"
            placeholder="Enter your name"
            defaultValue="David"
            required
          />
        </div>
        <div className={styles.form_group}>
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            placeholder="Enter your email"
            defaultValue="string@mail"
            required
          />
        </div>
        <div className={styles.form_group}>
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            placeholder="Choose a username"
            defaultValue="string"
            required
          />
        </div>
        <div className={styles.form_group}>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Create a password"
            defaultValue="string"
            required
          />
        </div>
        <p id="login" onClick={handleLoginButton} className={styles.login}>
          <a>Iniciar Sesion</a>
        </p>
        <button
          id="registerButton"
          type="button"
          className={styles.register_button}
          onClick={handleRegisterButton}
        >
          Register
        </button>
      </form>
    </div>
  );
}

function setUserData() {
  const data = [];

  for (let i = 0; i < 4; i++) {
    const element = document.getElementsByTagName("input").item(i).value;
    data.push(element);
  }

  const userData = {
    name: data[0],
    email: data[1],
    username: data[2],
    password: data[3],
    rol: "USER_ROLE",
  };
  console.log(userData);
  createUser(userData);
}

function createUser(userData) {
  fetch(`${register_url}/users`, {
    method: "POST",
    headers: {
      Accept: "*/*",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userData),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
    })
    .catch((e) => console.error(e));
}

export default Register;

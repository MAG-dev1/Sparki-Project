import React, { useEffect } from "react";
import styles from "./Login.module.css";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const handleRegisterButton = () => {
    navigate("/register");
  };


  return (
    <>
      <div className={styles.login_container}>
        <form className={styles.login_form}>
          <h2>Login</h2>
          <div className={styles.form_group}>
            <label htmlFor="usernameInput">Username</label>
            <input
              type="text"
              id="usernameInput"
              placeholder="Enter your username"
              required
            />
          </div>
          <div className={styles.form_group}>
            <label htmlFor="passwordInput">Password</label>
            <input
              type="password"
              id="passwordInput"
              placeholder="Enter your password"
              required
            />
          </div>
          <p id="register" className={styles.register} onClick={handleRegisterButton}>
            <a href="">Registro</a>
          </p>
          <button
            id="loginButton"
            type="button"
            className={styles.login_btn}
            onClick={ButtonHandlerHttp}
          >
            Login
          </button>
        </form>
      </div>
    </>
  );
  function ButtonHandlerHttp() {
    const task_url = import.meta.env.VITE_BACKEND_LOGIN;
  
    console.log("Login button clicked!");
    const email = document.getElementById("usernameInput").value;
    const password = document.getElementById("passwordInput").value;
  
    let data = {
      username: email,
      password: password,
    };
  
    fetch(`${task_url}/auth/login`, {
      method: "POST",
      headers: {
        Accept: "*/*",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("invalid request");
        }
        return response.json();
      })
      .then((data) => {
        if (data) {
          console.log("token is:" + data.token);
          localStorage.setItem("authToken", data.token);
          localStorage.setItem("username", email);
          navigate("/dashboard");
        } else {
          console.log("error to get the token");
        }
      })
      .catch((e) => {
        console.log("error:", e);
      });
  
    console.log("Email:", email);
    console.log("Password:", password);
  }
}



export default Login;

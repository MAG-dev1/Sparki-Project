
const task_url = import.meta.env.VITE_BACKEND_TASK;

const createTask = async (data, nameSubject) => {
  try {
    const response = await fetch(
      `${task_url}/tasks/associate?name_subject=${encodeURIComponent(nameSubject)}`,
      {
        method: "POST",
        headers: {
          Accept: "*/*",
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("authToken")}`,
        },
        body: JSON.stringify(data),
      }
    );

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const responseData = await response.json();
    console.log(responseData);
    return responseData;
  } catch (error) {
    console.error("Error:", error);
    return null; // Manejo de errores
  }
};


export default createTask;
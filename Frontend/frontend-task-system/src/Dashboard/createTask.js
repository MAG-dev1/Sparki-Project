

const createTask = (data, nameSubject) => {
    fetch(
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
      )
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          return data;
        })
        .catch((error) => {
          console.error("Error:", error);
        });
}

export default createTask;
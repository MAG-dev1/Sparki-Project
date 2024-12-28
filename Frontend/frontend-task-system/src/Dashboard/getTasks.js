const task_url = import.meta.env.VITE_BACKEND_TASK;

const getTasks = () => {
    
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
                return data;
            } 
            else {
                console.log("error to get the token");
            }
        })
        .catch((e) => {
            console.log("error:", e);
            return null;
        });
}

export default getTasks;
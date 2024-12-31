const task_url = import.meta.env.VITE_BACKEND_TASK;

const loadTasks = async () => {
    try {
        const response = await fetch(`${task_url}/tasks/${localStorage.getItem('username')}`, {
            method: "GET",
            headers: {
                Accept: "*/*",
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem('authToken')}`
            },
        });

        if (!response.ok) {
            throw new Error("Invalid request");
        }

        if (response.status == 204) {
            return null;
        } 

        const data = await response.json();
        return data; // Retorna los datos si la respuesta es exitosa
    } catch (error) {
        console.error("Error:", error);
        throw error; // Propaga el error para manejarlo en el componente
    }
}

export default loadTasks;
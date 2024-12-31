const task_url = import.meta.env.VITE_BACKEND_TASK;

const loadSubjects = async () => {

    try {
        const response = await fetch( `${task_url}/subjects/getAll?username=${encodeURIComponent(localStorage.getItem('username'))}`, {
            method: "GET",
            headers: {
                Accept: "*/*",
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem('authToken')}`
            },
        });
    
       if (response.status==204){
        return null
       }

        if (!response.ok) {
            throw new Error("error to fetch subjects");
        }
    
        let data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
        return null
    }
   
}

export default loadSubjects;
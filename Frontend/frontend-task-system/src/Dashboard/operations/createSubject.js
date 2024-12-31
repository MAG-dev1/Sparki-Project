const createSubject = async (subjectData) => {

    const task_url = import.meta.env.VITE_BACKEND_TASK;

    try {
        const response = await fetch(`${task_url}/subjects`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('authToken')}`
            },
            body: JSON.stringify(subjectData)
        });

        if(!response.ok){
            throw new Error("invalid request to the server");
        }

        let data = await response.json();
        return data;

    } catch (error) {
        console.log(error);
        return null
    }
  

}

export default createSubject;
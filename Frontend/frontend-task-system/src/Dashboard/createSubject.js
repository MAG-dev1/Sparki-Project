const createSubject = (subjectData) => {

    const task_url = import.meta.env.VITE_BACKEND_TASK;
    fetch(`${task_url}/subjects`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${localStorage.getItem('authToken')}`
        },
        body: JSON.stringify(subjectData)
     })
     .then((response) => response.json())
     .then((data) => {
         console.log(data);
         return data;
     })
     .catch((error) => {return error});

}

export default createSubject;
import { createContext, useContext, useState } from "react";

// Crear el contexto
const AppContext = createContext();

// Hook personalizado para usar el contexto
export const useAppContext = () => useContext(AppContext);

// Proveedor del contexto
export const AppProvider = ({ children }) => {
  const [subjects, setSubjects] = useState([]);
  const [tasks, setTasks] = useState([]);

  return (
    <AppContext.Provider value={{ subjects, setSubjects, tasks, setTasks }}>
      {children}
    </AppContext.Provider>
  );
};

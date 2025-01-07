import Login from "./Login/Login";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Register from "./Register/Register";
import Dasboard from "./Dashboard/Dashboard";
import Subject from "./Dashboard/Subject";
import { AppProvider } from "./AppContext";
import Pomodoro from "./Dashboard/Pomodoro";

function App() {
  return (
    <AppProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dasboard />} />
          <Route path="/subjects" element={<Subject />} />
          <Route path="/pomodoro" element={<Pomodoro />}></Route>
        </Routes>
      </Router>
    </AppProvider>
  );
}

export default App;

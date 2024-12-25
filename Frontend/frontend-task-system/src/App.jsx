import Login  from "./Login/Login";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from "./Register/Register";
import Dasboard from "./Dashboard/Dashboard";

function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />}/>
        <Route path="/dashboard" element={<Dasboard />}/>
      </Routes>
    </Router>
  );
}

export default App

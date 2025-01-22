import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from "./components/NavBar";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import HomePage from "./components/HomePage";
import EmployeePage from "./components/employee/EmployeePage";
import EmployeeData from "./components/employee/EmployeeData";
import JobData from "./components/job/JobData";
import JobPage from "./components/job/JobPage";
import DepartmentPage from "./components/department/DepartmentPage";
import DepartmentData from "./components/department/DepartmentData";
import CountryPage from "./components/country/CountryPage";
import CountryData from "./components/country/CountryData";
import EmployeeAdd from "./components/employee/EmployeAdd";
import DepartmentAdd from "./components/department/DepartmentAdd";
import JobAdd from "./components/job/JobAdd";
import CountryAdd from "./components/country/CountryAdd";

function App() {
  return (
      <Router>
          <NavBar/>
          <Routes>
              <Route path={"/"} element={<HomePage/>}/>
              <Route path={"/home"} element={<HomePage/>}/>
              <Route path={"/employee"} element={<EmployeePage/>}/>
              <Route path={"/employee-details/:userId"} element={<EmployeeData/>}/>
              <Route path={"/add-employee"} element={<EmployeeAdd/>}/>
              <Route path={"/job"} element={<JobPage/>}/>
              <Route path={"/job-details/:jobId"} element={<JobData/>} />
              <Route path={"/add-job"} element={<JobAdd/>}/>
              <Route path={"/department"} element={<DepartmentPage/>}/>
              <Route path={"/department-details/:departmentId"} element={<DepartmentData/>} />
              <Route path={"/add-department"} element={<DepartmentAdd/>}/>
              <Route path={"/country"} element={<CountryPage/>}/>
              <Route path={"/country-details/:countryId"} element={<CountryData/>} />
              <Route path={"/add-country"} element={<CountryAdd/>}/>
          </Routes>
      </Router>
  );
}

export default App;

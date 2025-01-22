import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from "react-router-dom";

const HomePage = () => {

    const [employees, setEmployees] = useState("");
    const [avgSalary, setAvgSalary] = useState("");

    const [departments, setDepartments] = useState("");
    const [mostEmployeesDepartment, setMostEmployeesDepartment] = useState({});

    const [jobs, setJobs] = useState("");
    const [mostEmployeesJob, setMostEmployeesJob] = useState({});
    const [minSalaryJob, setMinSalaryJob] = useState({});
    const [maxSalaryJob, setMaxSalaryJob] = useState({});

    const [countries, setCountries] = useState("");
    const [mostCountries, setMostCountries] = useState({});
    const [mostLocations, setMostLocations] = useState({});

    const fetchCountEmployees = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/employees/count",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setEmployees(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchAvgSalary = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/employees/avg-salary",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setAvgSalary(data);
        } catch (error) {
            console.log(error.message);
        }
    }

    const fetchDepartments = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/departments/count",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setDepartments(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchMostEmployees = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/departments/most-employees",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setMostEmployeesDepartment(data);
        } catch (error) {
            console.log(error.message);
        }
    }

    const fetchJobs = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/jobs/count",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setJobs(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchMinSalary = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/jobs/min-salary",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setMinSalaryJob(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchMaxSalary = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/jobs/max-salary",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setMaxSalaryJob(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchMostEmployeesJob = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/jobs/most-employees-job",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setMostEmployeesJob(data);
        } catch (error) {
            console.log(error.message);
        }
    }

    const fetchCountries = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/countries/count",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setCountries(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchMostCountries = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/countries/most-countries",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setMostCountries(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchMostLocations = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/countries/most-locations",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setMostLocations(data);
        } catch (error) {
            console.log(error.message);
        }
    }

    useEffect(() => {
        fetchCountEmployees();
        fetchAvgSalary();
        fetchDepartments();
        fetchMostEmployees();
        fetchJobs();
        fetchMinSalary();
        fetchMaxSalary();
        fetchMostEmployeesJob();
        fetchCountries();
        fetchMostCountries();
        fetchMostLocations();
    }, []);

    return (
        <div className="container mt-4">
            <div className="row g-4 d-flex align-items-stretch">
                <div className="col-12 col-sm-6 col-lg-4">
                    <div className="card shadow-sm h-100">
                        <div className="card-body d-flex flex-column">
                            <div className="d-flex justify-content-between align-items-center">
                                <h5 className="card-title">
                                    <i className="bi bi-person-circle me-1"></i>
                                    Employees
                                </h5>
                                <Link className="text-muted small" to="/add-employee">Add employee</Link>
                            </div>
                            <p className="mt-3 mb-0">Total employees: <strong>{employees}</strong></p>
                            <p className="mt-3 mb-0">Average salary: <strong>{avgSalary}</strong></p>
                        </div>
                    </div>
                </div>
                <div className="col-12 col-sm-6 col-lg-4">
                    <div className="card shadow-sm h-100">
                        <div className="card-body d-flex flex-column">
                            <div className="d-flex justify-content-between align-items-center">
                                <h5 className="card-title">
                                    <i className="bi bi-person-circle me-1"></i>
                                    Departments
                                </h5>
                                <Link className="text-muted small" to="/add-department">Add department</Link>
                            </div>
                            <p className="mt-3 mb-0">Departments: <strong>{departments}</strong></p>
                            <p className="mt-3 mb-0">The most employees
                                in <strong>{mostEmployeesDepartment.DEPARTMENT_NAME}</strong>:
                                <strong> {mostEmployeesDepartment.EMPLOYEES_COUNT}</strong></p>
                        </div>
                    </div>
                </div>
                <div className="col-12 col-sm-6 col-lg-4">
                    <div className="card shadow-sm h-100">
                        <div className="card-body d-flex flex-column">
                            <div className="d-flex justify-content-between align-items-center">
                                <h5 className="card-title">
                                    <i className="bi bi-person-circle me-1"></i>
                                    Jobs
                                </h5>
                                <Link className="text-muted small" to="/add-job">Add job</Link>
                            </div>
                            <p className="mt-3 mb-0">Jobs: <strong>{jobs}</strong></p>
                            <p className="mt-3 mb-0">The most employees in
                                <strong> {mostEmployeesJob.JOB_TITLE}</strong>:
                                <strong> {mostEmployeesJob.EMPLOYEES_COUNT}</strong>
                            </p>
                            <p className="mt-3 mb-0">The best-paid job:
                                <strong> {maxSalaryJob.JOB_TITLE}</strong>
                                , salary:
                                <strong> {maxSalaryJob.MAX_SALARY}$</strong>
                            </p>
                            <p className="mt-3 mb-0">The worst-paid job:
                                <strong> {minSalaryJob.JOB_TITLE}</strong>
                                , salary:
                                <strong> {minSalaryJob.MIN_SALARY}$</strong>
                            </p>
                        </div>
                    </div>
                </div>
                <div className="col-12 col-sm-6 col-lg-4">
                    <div className="card shadow-sm h-100">
                        <div className="card-body d-flex flex-column">
                            <div className="d-flex justify-content-between align-items-center">
                                <h5 className="card-title">
                                    <i className="bi bi-person-circle me-1"></i>
                                    Countries
                                </h5>
                                <Link className="text-muted small" to="/add-country">Add country</Link>
                            </div>
                            <p className="mt-3 mb-0">Countries: <strong>{countries}</strong></p>
                            <p className="mt-3 mb-0">The most countries in
                                <strong> {mostCountries.REGION_NAME}</strong>:
                                <strong> {mostCountries.COUNTRIES_COUNT}</strong>
                            </p>
                            <p className="mt-3 mb-0">Country with the most locations:
                                <strong> {mostLocations.COUNTRY_NAME}</strong>
                                , locations:
                                <strong> {mostLocations.LOCATIONS_COUNT}</strong>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HomePage;
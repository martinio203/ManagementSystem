import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from "react-router-dom";

const HomePage = () => {

    const [stats, setStats] = useState({});

    const fetchStats = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/stats",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setStats(data);
        } catch (error) {
            console.log(error.message);
        }
    }

    useEffect(() => {
        fetchStats();
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
                            <p className="mt-3 mb-0">Total employees: <strong>{stats.employeeStats?.countedEmployees}</strong></p>
                            <p className="mt-3 mb-0">Average salary: <strong>{stats.employeeStats?.avgSalary}</strong></p>
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
                            <p className="mt-3 mb-0">Departments: <strong>{stats.departmentStats?.countedDepartments}</strong></p>
                            <p className="mt-3 mb-0">The most employees
                                in <strong>{stats.departmentStats?.mostEmployees?.DEPARTMENT_NAME}</strong>:
                                <strong> {stats.departmentStats?.mostEmployees?.EMPLOYEED_COUNT}</strong></p>
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
                            <p className="mt-3 mb-0">Jobs: <strong>{stats.jobStats?.countedJobs}</strong></p>
                            <p className="mt-3 mb-0">The most employees in
                                <strong> {stats.jobStats?.mostEmployees?.JOB_TITLE}</strong>:
                                <strong> {stats.jobStats?.mostEmployees?.EMPLOYEES_COUNT}</strong>
                            </p>
                            <p className="mt-3 mb-0">The best-paid job:
                                <strong> {stats.jobStats?.maxSalary?.JOB_TITLE}</strong>
                                , salary:
                                <strong> {stats.jobStats?.maxSalary?.MAX_SALARY}$</strong>
                            </p>
                            <p className="mt-3 mb-0">The worst-paid job:
                                <strong> {stats.jobStats?.minSalary?.JOB_TITLE}</strong>
                                , salary:
                                <strong> {stats.jobStats?.minSalary?.MIN_SALARY}$</strong>
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
                            <p className="mt-3 mb-0">Countries: <strong>{stats.countryStats?.countedCountries}</strong></p>
                            <p className="mt-3 mb-0">The most countries in
                                <strong> {stats.countryStats?.mostCountries?.REGION_NAME}</strong>:
                                <strong> {stats.countryStats?.mostCountries?.COUNTRIES_COUNT}</strong>
                            </p>
                            <p className="mt-3 mb-0">Country with the most locations:
                                <strong> {stats.countryStats?.mostLocations?.COUNTRY_NAME}</strong>
                                , locations:
                                <strong> {stats.countryStats?.mostLocations?.LOCATIONS_COUNT}</strong>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HomePage;
import {useEffect, useState} from "react";
import  {useNavigate} from "react-router-dom";

const DepartmentPage = () => {
    const navigate = useNavigate();
    const [departments, setDepartments] = useState([]);

    const fetchDepartment = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/departments/all",{
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
    const fetchDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/departments/delete/${id}`,{
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (response.ok) fetchDepartment();
        } catch (error) {
            console.log(error.message);
        }
    }

    const detailsId = (departmentId) => {
        navigate(`/department-details/${departmentId}`)
    }

    const handleDelete = (id) => {
        fetchDelete(id);
    }

    useEffect(() => {
        fetchDepartment();
    }, []);

    return (
        <div className="container">
            <table className="table table-bordered text-center">
                <thead className="table-dark align-middle">
                <tr>
                    <th scope="col">Department Id</th>
                    <th scope="col">Department name</th>
                    <th scope="col">City</th>
                    <th scope="col">Country</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                {departments.map((department) => (
                    <tr key={department.DEPARTMENT_ID}>
                        <td>{department.DEPARTMENT_ID}</td>
                        <td>{department.DEPARTMENT_NAME}</td>
                        <td>{department.CITY}</td>
                        <td>{department.COUNTRY_NAME}</td>
                        <td>
                            <button type="button" onClick={() => detailsId(department.DEPARTMENT_ID)}
                                    className="btn btn-info">Department details
                            </button>
                            <button type="button" onClick={() => handleDelete(department.DEPARTMENT_ID)}
                                    className="btn btn-danger">Delete departments
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default DepartmentPage;
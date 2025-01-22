import {useEffect, useState} from "react";
import  {useNavigate} from "react-router-dom";

const EmployeePage = () => {
    const navigate = useNavigate();
    const [users, setUsers] = useState([]);

    const fetchUsers = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/employees/all",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setUsers(data);
        } catch (error) {
            console.log(error.message);
        }
    }
    const fetchDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/employees/delete/${id}`,{
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (response.ok) fetchUsers();
        } catch (error) {
            console.log(error.message);
        }
    }

    const detailsId = (employeeId) => {
        navigate(`/employee-details/${employeeId}`)
    }

    const handleDelete = (id) => {
        fetchDelete(id);
    }

    useEffect(() => {
        fetchUsers();
    }, []);

    return (
        <div className="container">
            <table className="table table-bordered text-center">
                <thead className="table-dark align-middle">
                <tr>
                    <th scope="col">User Id</th>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                    <th scope="col">Email</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                        <tr key={user.EMPLOYEE_ID}>
                            <td>{user.EMPLOYEE_ID}</td>
                            <td>{user.FIRST_NAME}</td>
                            <td>{user.LAST_NAME}</td>
                            <td>{user.EMAIL}</td>
                            <td>
                                <button type="button" onClick={() => detailsId(user.EMPLOYEE_ID)}
                                        className="btn btn-info">Employee details
                                </button>
                                <button type="button" onClick={() => handleDelete(user.EMPLOYEE_ID)}
                                        className="btn btn-danger">Delete emloyee
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default EmployeePage;

import {useState} from "react";

const EmployeeAdd = () => {

    const [request, setRequest] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const fetchAdd = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/employees/add",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(
                    request
                )
            });
            if (response.ok) {
                setSuccessMessage("Employee has been added");
            }
            else setSuccessMessage("")
        } catch (error) {
            setSuccessMessage("");
            setErrorMessage(error.message);
        }
    }

    const handleChange = (e) => {
        const {name, value} = e.target;
        setRequest((prev) => ({
            ...prev,
            [name]: ["employeeId","managerId", "salary", "departmentId"].includes(name) ?
                parseInt(value) : value,
        }));
    };

    const handleSubmit = () => {
        console.log(request);
        fetchAdd();
    }

    return (
        <div className="container">
            <div className="d-flex justify-content-center">
                <div className="card p-4 shadow-sm" style={{width: "100%", maxWidth: "500px"}}>
                    <div className="text-center mb-3">
                        <h5>Add Employee</h5>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Employee Id</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"employeeId"}
                            value={request.employeeId}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">First name</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"firstName"}
                            value={request.firstName}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Last name</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"lastName"}
                            value={request.lastName}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Email</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"email"}
                            value={request.email}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Phone number </label>
                        <input
                            type="text"
                            className="form-control"
                            name={"phoneNumber"}
                            value={request.phoneNumber}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Hire date</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"hireDate"}
                            value={request.hireDate}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Job id</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"jobId"}
                            value={request.jobId}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Salary</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"salary"}
                            value={request.salary}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Manager Id</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"managerId"}
                            value={request.managerId}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Department Id</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"departmentId"}
                            value={request.departmentId}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="text-center">
                        <button className="btn btn-primary w-100" onClick={handleSubmit}>Add employee</button>
                    </div>

                    <div className="text-center mt-2">
                        {successMessage && <span className="text-success">{successMessage}</span>}
                        {errorMessage && <span className="text-danger">{errorMessage}</span>}
                    </div>
                </div>
            </div>
        </div>
    );
}
export default EmployeeAdd;
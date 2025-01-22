import {useState} from "react";

const JobAdd = () => {

    const [request, setRequest] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const fetchAdd = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/jobs/add",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(
                    request
                )
            });
            if (response.ok) {
                setSuccessMessage("Job has been added");
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
            [name]: ["jobTitle"].includes(name) ?
                value : parseInt(value),
        }));
    };

    const handleSubmit = () => {
        fetchAdd();
    }

    return (
        <div className="container">
            <div className="d-flex justify-content-center">
                <div className="card p-4 shadow-sm" style={{width: "100%", maxWidth: "500px"}}>
                    <div className="text-center mb-3">
                        <h5>Add Job</h5>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Job Id</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"jobId"}
                            value={request.jobId}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Job title</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"jobTitle"}
                            value={request.jobTitle}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Min salary</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"minSalary"}
                            value={request.minSalary}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Max salary</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"maxSalary"}
                            value={request.maxSalary}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="text-center">
                        <button className="btn btn-primary w-100" onClick={handleSubmit}>Add job</button>
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
export default JobAdd;
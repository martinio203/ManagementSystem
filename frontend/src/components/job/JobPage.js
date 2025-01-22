import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

const JobPage = () => {
    const navigate = useNavigate();
    const [jobs, setJobs] = useState([]);

    const fetchJob = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/jobs/all",{
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

    const fetchDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/jobs/delete/${id}`,{
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (response.ok) fetchJob();
        } catch (error) {
            console.log(error.message);
        }
    }

    const detailsId = (jobId) => {
        navigate(`/job-details/${jobId}`);
    }

    const handleDelete = (id) => {
        fetchDelete(id);
    }

    useEffect(() => {
        fetchJob();
    }, []);

    return (
        <div className="container">
            <table className="table table-bordered text-center">
                <thead className="table-dark align-middle">
                <tr>
                    <th scope="col">Job Id</th>
                    <th scope="col">Job title</th>
                    <th scope="col">Min salary</th>
                    <th scope="col">Max salary</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                {jobs.map((job) => (
                    <tr key={job.JOB_ID}>
                        <td>{job.JOB_ID}</td>
                        <td>{job.JOB_TITLE}</td>
                        <td>{job.MIN_SALARY}</td>
                        <td>{job.MAX_SALARY}</td>
                        <td>
                            <button type="button" onClick={() => detailsId(job.JOB_ID)}
                                    className="btn btn-info">Edit details
                            </button>
                            <button type="button" onClick={() => handleDelete(job.JOB_ID)}
                                    className="btn btn-danger">Delete job
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
};

export default JobPage;
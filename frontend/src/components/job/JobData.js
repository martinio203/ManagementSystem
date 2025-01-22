import {Button, Form, Modal} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

const JobData = () => {
    const {jobId}  = useParams();
    const [jobData, setJobData] = useState({});
    const [showModal, setShowModal] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [changedFieldValue, setChangedFieldValue] = useState(null);
    const [modalTitle, setModalTitle] = useState("");
    const [urlChange, setUrlChange] = useState("");

    const fetchChange = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/jobs/${urlChange}/${jobId}`,{
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: changedFieldValue
            });
            if (response.ok) {
                setSuccessMessage(`${modalTitle.charAt(0).toUpperCase() + modalTitle.slice(1)} has been changed`);
                fetchJob();
            }
        } catch (error) {
            setErrorMessage(error.message);
        }
    }

    const fetchJob = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/jobs/details/${jobId}`,{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setJobData(data);
        } catch (error) {
            setErrorMessage(error.message);
        }
    }

    useEffect(() => {
        fetchJob();
    }, [jobId]);

    const handleModalShow = (url) => {
        setUrlChange(url);
        setShowModal(true);
    }

    const handleModalClose = () => {
        setShowModal(false);
        setChangedFieldValue(null);
        setErrorMessage("");
        setSuccessMessage("");
    }

    const handleSubmit = () => {
        fetchChange();
        setChangedFieldValue(null);
    }

    return (
        <div className="container mt-5">
            <div className="card shadow">
                <div className="card-body">
                    <div className="d-flex justify-content-between align-items-center">
                        <h5 className="card-title">
                            {jobData.JOB_ID}
                        </h5>
                    </div>
                    <p className="card-text">Below is the job information:</p>
                    <ul className="list-group list-group-flush">
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Job title: {jobData.JOB_TITLE}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-title");
                                    setModalTitle("title");
                                }}
                            >
                                Edit title
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Minimal salary: {jobData.MIN_SALARY}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-min-salary");
                                    setModalTitle("minimal salary");
                                }}
                            >
                                Edit salary
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Maximal salary: {jobData.MAX_SALARY}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-max-salary");
                                    setModalTitle("maximal salary");
                                }}
                            >
                                Edit salary
                            </button>
                        </li>
                    </ul>
                </div>
            </div>

            <Modal show={showModal} onHide={handleModalClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Change {modalTitle}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="form">
                            <Form.Label>Enter new {modalTitle}</Form.Label>
                            <Form.Control
                                value={undefined}
                                onChange={(e) => setChangedFieldValue(e.target.value)}
                            />
                        </Form.Group>
                        {errorMessage && <div className="text-danger mt-2">{errorMessage}</div>}
                        {successMessage && <div className="text-success mt-2">{successMessage}</div>}
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleModalClose}>
                        Cancel
                    </Button>
                    <Button variant="primary" onClick={handleSubmit}>
                        Submit
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default JobData;
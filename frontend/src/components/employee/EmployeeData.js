import React, {useEffect, useState} from "react";
import { Modal, Button, Form } from 'react-bootstrap';
import {useParams} from "react-router-dom";

const EmployeeData = () => {
    const {userId}  = useParams();
    const [userData, setUserData] = useState({});
    const [showModal, setShowModal] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [changedFieldValue, setChangedFieldValue] = useState(null);
    const [modalTitle, setModalTitle] = useState("");
    const [urlChange, setUrlChange] = useState("");

    const fetchChange = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/employees/${urlChange}/${parseInt(userId)}`,{
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: changedFieldValue

            });
            if (response.ok) {
                setSuccessMessage(`${modalTitle.charAt(0).toUpperCase() + modalTitle.slice(1)} has been changed`);
                fetchUser();
            }
        } catch (error) {
            setErrorMessage(error.message);
        }
    }

    const fetchUser = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/employees/details/${parseInt(userId)}`,{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setUserData(data);
        } catch (error) {
            setErrorMessage(error.message);
        }
    }

    useEffect(() => {
        fetchUser();
    }, [userId]);


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

    const formatDate = (date) => {
        if (!date) return "N/A";
        return date.substring(0,10);
    }

    return (
        <div className="container mt-5">
            <div className="card shadow">
                <div className="card-body">
                    <div className="d-flex justify-content-between align-items-center">
                        <h5 className="card-title">
                            {userData.FIRST_NAME} {userData.LAST_NAME}
                        </h5>
                        <button
                            className="btn btn-sm btn-primary"
                            onClick={() => {
                                handleModalShow("change-name");
                                setModalTitle("employee name");
                            }}
                        >
                            Edit Employee Name
                        </button>
                    </div>
                    <p className="card-text">Below is the employee information:</p>
                    <ul className="list-group list-group-flush">
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Email: {userData.EMAIL}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-email");
                                    setModalTitle("email");
                                }}
                            >
                                Edit Email
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Phone Number: {userData.PHONE_NUMBER}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-number");
                                    setModalTitle("number");
                                }}
                            >
                                Edit Number
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Hire Date: {formatDate(userData.HIRE_DATE)}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-hire-date");
                                    setModalTitle("hire date");
                                }}
                            >
                                Edit Date
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Salary: {userData.SALARY}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-salary");
                                    setModalTitle("salary");
                                }}
                            >
                                Edit Salary
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Job Title: {userData.JOB_TITLE}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-name");
                                    setModalTitle("job name");

                                }}
                            >
                                Edit Job
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Department: {userData.DEPARTMENT_NAME}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-department");
                                    setModalTitle("department");
                                }}
                            >
                                Edit department
                            </button>
                        </li>
                        <li className="list-group-item d-flex justify-content-between align-items-center">
                            Manager: {userData.MANAGER_NAME}
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-manager");
                                    setModalTitle("manager");
                                }}
                            >
                                Edit manager
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

export default EmployeeData;
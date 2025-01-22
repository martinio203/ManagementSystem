import React, {useEffect, useState} from "react";
import { Modal, Button, Form } from 'react-bootstrap';
import {useParams} from "react-router-dom";

const CountryData = () => {
        const {countryId}  = useParams();
        const [countryData, setCountryData] = useState({});
        const [showModal, setShowModal] = useState(false);
        const [errorMessage, setErrorMessage] = useState("");
        const [successMessage, setSuccessMessage] = useState("");
        const [changedFieldValue, setChangedFieldValue] = useState(null);
        const [modalTitle, setModalTitle] = useState("");
        const [urlChange, setUrlChange] = useState("");

        const fetchChange = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/countries/${urlChange}/${parseInt(countryId)}`,{
                    method: "PATCH",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: changedFieldValue

                });
                if (response.ok) {
                    setSuccessMessage(`${modalTitle.charAt(0).toUpperCase() + modalTitle.slice(1)} has been changed`);
                    fetchCountry();
                }
            } catch (error) {
                setErrorMessage(error.message);
            }
        }

        const fetchCountry = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/countries/details/${parseInt(countryId)}`,{
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
                const data = await response.json();
                setCountryData(data);
            } catch (error) {
                setErrorMessage(error.message);
            }
        }

        useEffect(() => {
            fetchCountry();
        }, [countryId]);


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
                                {countryData.COUNTRY_NAME}
                            </h5>
                            <button
                                className="btn btn-sm btn-primary"
                                onClick={() => {
                                    handleModalShow("change-name");
                                    setModalTitle("country name");
                                }}
                            >
                                Edit Country Name
                            </button>
                        </div>
                        <p className="card-text">Below is the country information:</p>
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item d-flex justify-content-between align-items-center">
                                Region: {countryData.REGION_NAME}
                                <button
                                    className="btn btn-sm btn-primary"
                                    onClick={() => {
                                        handleModalShow("change-region");
                                        setModalTitle("region name");
                                    }}
                                >
                                    Edit Name
                                </button>
                            </li>
                            <li className="list-group-item d-flex justify-content-between align-items-center">
                                Currency: {countryData.CURRENCY_CODE}
                                <button
                                    className="btn btn-sm btn-primary"
                                    onClick={() => {
                                        handleModalShow("change-currency-code");
                                        setModalTitle("currency");
                                    }}
                                >
                                    Edit Currency
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
export default CountryData;
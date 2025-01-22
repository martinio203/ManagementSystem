import {useState} from "react";

const CountryAdd = () => {

    const [request, setRequest] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const fetchAdd = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/countries/add",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(
                    request
                )
            });
            if (response.ok) {
                setSuccessMessage("Country has been added");
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
            [name]: ["countryName", "currencyCode"].includes(name) ?
                value : parseInt(value),
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
                        <h5>Add Country</h5>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Country Id</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"countryId"}
                            value={request.countryId}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Country name</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"countryName"}
                            value={request.countryName}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Region Id</label>
                        <input
                            type="number"
                            className="form-control"
                            name={"regionId"}
                            value={request.regionId}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="currency" className="form-label">Currency code</label>
                        <input
                            type="text"
                            className="form-control"
                            name={"currencyCode"}
                            value={request.currencyCode}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="text-center">
                        <button className="btn btn-primary w-100" onClick={handleSubmit}>Add Country</button>
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
export default CountryAdd;
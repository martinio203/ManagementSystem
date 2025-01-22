import {useEffect, useState} from "react";
import  {useNavigate} from "react-router-dom";

const CountryPage = () => {
    const navigate = useNavigate();
    const [countries, setCountries] = useState([]);

    const fetchCountries = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/countries/all",{
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            setCountries(data);
        } catch (error) {
            console.log(error.message);
        }
    }

    const fetchDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/countries/${id}`,{
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (response.ok) fetchCountries();
        } catch (error) {
            console.log(error.message);
        }
    }

    const detailsId = (countryId) => {
        navigate(`/country-details/${countryId}`)
    }

    const handleDelete = (id) => {
        fetchDelete(id);
    }

    useEffect(() => {
        fetchCountries();
    }, []);

    return (
        <div className="container">
            <table className="table table-bordered text-center">
                <thead className="table-dark align-middle">
                <tr>
                    <th scope="col">Country Id</th>
                    <th scope="col">Country name</th>
                    <th scope="col">Region Name</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                {countries.map((country) => (
                    <tr key={country.COUNTRY_ID}>
                        <td>{country.COUNTRY_ID}</td>
                        <td>{country.COUNTRY_NAME}</td>
                        <td>{country.REGION_NAME}</td>
                        <td>
                            <button type="button" onClick={() => detailsId(country.COUNTRY_ID)}
                                    className="btn btn-info">Country details
                            </button>
                            <button type="button" onClick={() => handleDelete(country.COUNTRY_ID)}
                                    className="btn btn-danger">Delete country
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default CountryPage;
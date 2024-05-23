

function getJsonProducts() {
    return [{ "id": 1, "name": "banana", "price": 30 }, { "id": 2, "name": "manzana", "price": 80 }, { "id": 3, "name": "kiwis", "price": 50 }, { "id": 4, "name": "carne 1", "price": 300 }]
}


function getData() {
    return [
        {
            "username": "Joaquin",
            "json_products": JSON.stringify(getJsonProducts()),
            "total_price": float(row[4])
        }
    ]
}


async function sendDataToAPI() {
    const URL = "http://localhost:5000/save_surtido_data"

    try {
        const response = await fetch(URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(getData())
        })

        if (!response.ok) {
            throw new Error("Network response was not ok " + response.statusText)
        }

        const jsonResponse = await response.json()
        console.log("Success:", jsonResponse)
    } catch (error) {
        console.error(error)
    }

}


sendDataToAPI()

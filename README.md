# RealEstateManager
Spring Boot Project

H2 database is used to store the data.


In order to insert new apartment to the database following url and sample body needed:
  URL: localhost:8080/insert (POST)
  BODY: 
  {
    "property": {
        "area": 90,
        "price": 250000,
        "bedrooms": [
            {
                "area": 12
            },
            {
                "area": 15
            },
            {
                "area": 20
            }
        ],
        "bathrooms": [
            {
                "area": 10
            }
        ]
    }
  }
  
  
  
In order to find a suitable apartment with given filters, following url and sample body needed:
  URL: localhost:8080/find (GET)
  BODY: 
  {
    "price": 255000,
    "totalArea": 90,
    "minNumberOfBedrooms": 2,
    "bedroomAreas": [19, 12],
    "minNumberOfBathrooms": 1,
    "bathroomAreas": [9]
  }

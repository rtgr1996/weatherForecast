This is a project to access the weather forecast API particularly - (RapidApiGetForecastSummaryByLocationName) and (RapidApiGetHourlyForecastByLocationName)

To start the project simply clone this repo and run the **Main Class** file which will simply start the project.
The port used here is **localhost:9090**


Security Keys are required on the headers

clientId - forecast9.p.rapidapi.com
clientSecret - d4a910d418mshc6da816c9a51540p1575f5jsnb083c4e9a8a6


These are the endpoints to access the APIs, remember that both APIs are a GET Request

RapidApiGetForecastSummaryByLocationName - http://localhost:9090/forecastSummary?city=

RapidApiGetHourlyForecastByLocationName - http://localhost:9090/hourlyForecastSummary?cityName=

Also note that RapidApiGetHourlyForecastByLocationName required a paid subscription which I currently don't have, still, I have written the code for understanding purpose.

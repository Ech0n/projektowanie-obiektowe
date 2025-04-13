package controllers

import (
    "air-quality-app/database"
    "air-quality-app/models"
    "air-quality-app/proxy"
    "github.com/labstack/echo/v4"
    "net/http"
)

func GetAirQuality(c echo.Context) error {
    location := c.QueryParams()["location"]
    if len(location) == 0 {
		location = []string{"cracow", "london"}
	}
    var records []models.AirQuality

    for _, loc := range location {
        if loc == "cracow" {
            var airQualityCracow []models.AirQualityCracow
            result := database.DB.Scopes(models.GetFromLastOneHour).Find(&airQualityCracow)
            if result.Error != nil {
                return c.JSON(http.StatusInternalServerError, echo.Map{"error": result.Error.Error()})
            }

            if len(airQualityCracow) > 0 {
                println("Found cached air quality data for Cracow for the last hour.")
                records = append(records, airQualityCracow[0].AirQuality)
                continue
            }

            airQualityData, err := proxy.FetchAirQualityDataCracow()
            if err != nil {
                return c.JSON(http.StatusInternalServerError, echo.Map{"error": err.Error()})
            }

            database.DB.Create(&airQualityData)
            records = append(records, airQualityData.AirQuality)
        }
        if loc == "london" {
            var airQualityLondon []models.AirQualityLondon
            result := database.DB.Scopes(models.GetFromLastOneHour).Find(&airQualityLondon)
            if result.Error != nil {
                return c.JSON(http.StatusInternalServerError, echo.Map{"error": result.Error.Error()})
            }

            if len(airQualityLondon) > 0 {
                println("Found cached air quality data for London for the last hour.")
                records = append(records, airQualityLondon[0].AirQuality)
                continue
            }

            airQualityData, err := proxy.FetchAirQualityDataLondon()
            if err != nil {
                return c.JSON(http.StatusInternalServerError, echo.Map{"error": err.Error()})
            }

            database.DB.Create(&airQualityData)
            records = append(records, airQualityData.AirQuality)
        }
    }
    
    return c.JSON(http.StatusOK, records)

}

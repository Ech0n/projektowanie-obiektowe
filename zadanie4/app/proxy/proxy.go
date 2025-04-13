package proxy

import (
    "errors"
    // "math/rand"
    // "strings"
    "time"    
    "net/http"
    "encoding/json"
    "air-quality-app/models"
)

func buildUrl(loc string) string{
    const cracow = "?latitude=50.0614&longitude=19.9366"
    const london = "?latitude=51.5085&longitude=-0.1257"
    if(loc == "cracow"){
        return "https://air-quality-api.open-meteo.com/v1/air-quality" + cracow + "&current=european_aqi,pm10,pm2_5,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone,uv_index"
    }
    if(loc == "london"){
        return "https://air-quality-api.open-meteo.com/v1/air-quality" + london + "&current=european_aqi,pm10,pm2_5,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone,uv_index"
    }
    return "none"
}

func FetchAirQualityDataCracow()(models.AirQualityCracow, error) {
    city := "cracow"
    url := buildUrl(city)
    if url == "none" {
        return models.AirQualityCracow{}, errors.New("Invalid city name")
    }
    println("Fetching air quality data from URL:", url)
    client := &http.Client{Timeout: 10 * time.Second}
    resp, err := client.Get(url)
    if err != nil {
        return models.AirQualityCracow{}, err
    }
    defer resp.Body.Close()
    print("Response status:", resp.Body)
    if resp.StatusCode != http.StatusOK {
        return models.AirQualityCracow{}, errors.New("Failed to fetch data: " + resp.Status)
    }

    var responseData struct {
        Current models.AirQualityCracow `json:"current"`
    }
    if err := json.NewDecoder(resp.Body).Decode(&responseData); err != nil {
        return models.AirQualityCracow{}, err
    }
    airQualityData := responseData.Current

    return airQualityData, nil
  
}

func FetchAirQualityDataLondon()(models.AirQualityLondon, error) {
    city := "london"
    url := buildUrl(city)
    if url == "none" {
        return models.AirQualityLondon{}, errors.New("Invalid city name")
    }
    println("Fetching air quality data from URL:", url)
    client := &http.Client{Timeout: 10 * time.Second}
    resp, err := client.Get(url)
    if err != nil {
        return models.AirQualityLondon{}, err
    }
    defer resp.Body.Close()
    print("Response status:", resp.Body)
    if resp.StatusCode != http.StatusOK {
        return models.AirQualityLondon{}, errors.New("Failed to fetch data: " + resp.Status)
    }

    var responseData struct {
        Current models.AirQualityLondon `json:"current"`
    }
    if err := json.NewDecoder(resp.Body).Decode(&responseData); err != nil {
        return models.AirQualityLondon{}, err
    }
    airQualityData := responseData.Current

    return airQualityData, nil
  
}
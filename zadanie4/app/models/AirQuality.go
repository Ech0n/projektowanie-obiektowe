package models

import "gorm.io/gorm"
import "time"

type AirQuality struct {
    PM10            float64 `json:"pm10"`
    PM2_5           float64 `json:"pm2_5"`
    CarbonMonoxide  float64 `json:"carbon_monoxide"`
    NitrogenDioxide float64 `json:"nitrogen_dioxide"`
    SulphurDioxide  float64 `json:"sulphur_dioxide"`
    Ozone           float64 `json:"ozone"`
    EUAQI           int     `json:"european_aqi"`
    UVIndex         float64 `json:"uv_index"`
}

type AirQualityCracow struct {
    gorm.Model
    AirQuality
}

type AirQualityLondon struct {
    gorm.Model
    AirQuality
}

func (AirQualityCracow) TableName() string {
    return "air_quality_cracow"
}


func (AirQualityLondon) TableName() string {
    return "air_quality_london"
}

func GetFromLastOneHour(db *gorm.DB) *gorm.DB {
    return db.Where("created_at >= ?", time.Now().Add(-1*time.Hour))
}
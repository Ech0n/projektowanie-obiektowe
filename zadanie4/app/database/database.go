package database

import (
    "air-quality-app/models"
    "gorm.io/driver/sqlite"
    "gorm.io/gorm"
    "log"
)

var DB *gorm.DB

func InitDB() {
    var err error

    DB, err = gorm.Open(sqlite.Open("air-quality.db"), &gorm.Config{})
    if err != nil {
        log.Fatal("Failed to connect to database:", err)
    }

    err = DB.AutoMigrate(&models.AirQualityCracow{}, &models.AirQualityLondon{})
    if err != nil {
        log.Fatal("Failed to migrate database:", err)
    }

    log.Println("Database connection and migration successful.")
}
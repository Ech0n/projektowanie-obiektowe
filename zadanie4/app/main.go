package main

import (
    "air-quality-app/controllers"
    "air-quality-app/database"
    "github.com/labstack/echo/v4"
)

func main() {
    e := echo.New()
	database.InitDB()

    e.GET("/air", controllers.GetAirQuality)

	e.Logger.Fatal(e.Start(":8080"))
}
**ZADANIE 4.** go + echo

:white_check_mark: 3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Pogody, która pozwala na pobieranie danych o pogodzie (lub akcjach giełdowych)  
:white_check_mark: 3.5 Należy stworzyć model Pogoda (lub Giełda) wykorzystując gorm, a dane załadować z listy przy uruchomieniu   
:white_check_mark: 4.0 Należy stworzyć klasę proxy, która pobierze dane z serwisu zewnętrznego podczas zapytania do naszego kontrolera  
:white_check_mark: 4.5 Należy zapisać pobrane dane z zewnątrz do bazy danych  
:white_check_mark: 5.0 Należy rozszerzyć endpoint na więcej niż jedną lokalizację (Pogoda), lub akcje (Giełda) zwracając JSONa  

### Uruchamianie

linux:  
`./runDocker.sh` - jeżeli obraz dockerowy nie istnieje builduje go, a następnie uruchamia. Entrypointem obrazu jest skrypt uruchamiający serwer go. Serwer słucha na porcie 8080.
windows:  
1. `docker buildx build -t <własna_nazwa_obrazu> .` - buduje obraz
2. `docker run -it -p 8080:8080 --rm -v $(pwd)/app:/app <własna_nazwa_obrazu>` - uruchamia obraz docker wraz z serwerem go

### Działanie
Serwer udostępnia endpoint `/air`, na tym endpoincie serwer wysyła dane o jakości powietrza aktualizowane co godzine. Dodatkowo scope location umozliwia ustawienie miasta z którego jakosc powietrza nas interesuje.
##### Dostępne miasta:
- cracow
- london

#### Przykłady zapytan:
- [http://localhost:8080/air?location=london](http://localhost:8080/air?location=london)
- [http://localhost:8080/air?location=cracow](http://localhost:8080/air?location=cracow)
- [http://localhost:8080/air?location=cracow&location=london](http://localhost:8080/air?location=cracow&location=london)
- [http://localhost:8080/air](http://localhost:8080/air)


# Projektowanie obiektowe

## Zadania:
+ [Zadanie 1. Paradygmaty (pascal)](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie1)

*Zadanie 1* Paradygmaty (pascal)

:white_check_mark: 3.0 Procedura do generowania 50 losowych liczb od 0 do 100  
:white_check_mark: 3.5 Procedura do sortowania liczb  
:white_check_mark: 4.0 Dodanie parametrów do procedury losującej określającymi zakres losowania: od, do, ile  
:white_check_mark: 4.5 5 testów jednostkowych testujące procedury   
:white_check_mark: 5.0 Skrypt w bashu do uruchamiania aplikacji w Pascalu via docker  

Kod: [/zadanie1](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie1)

*Zadanie 2* Symfony + PHP

:white_check_mark: 3.0 Należy stworzyć jeden model z kontrolerem z produktami, zgodnie z CRUD  
:white_check_mark: 3.5 Należy stworzyć skrypty do testów endpointów via curl  
:white_check_mark: 4.0 Należy stworzyć dwa dodatkowe kontrolery wraz z modelami  
:white_check_mark: 4.5 Należy stworzyć widoki do wszystkich kontrolerów  
:x: 5.0 Należy stworzyć widoki do wszystkich kontrolerów  

Kod: [/zadanie2](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie2)

*Zadanie 3* Kotlin + Spring Boot

:white_check_mark: 3.0 Należy stworzyć jeden kontroler wraz z danymi wyświetlanymi z listy na endpoint’cie w formacie JSON - Kotlin + Spring Boot  
:white_check_mark: 3.5 Należy stworzyć klasę do autoryzacji (mock) jako Singleton w formie eager  
:white_check_mark: 4.0 Należy obsłużyć dane autoryzacji przekazywane przez użytkownika  
:white_check_mark: 4.5 Należy wstrzyknąć singleton do głównej klasy via @Autowired  
:white_check_mark: 5.0 Obok wersji Eager do wyboru powinna być wersja Singletona w wersji lazy  

Kod: [/zadanie3](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie3)

**ZADANIE 4.** go + echo

:white_check_mark: 3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Pogody, która pozwala na pobieranie danych o pogodzie (lub akcjach giełdowych)  
:white_check_mark: 3.5 Należy stworzyć model Pogoda (lub Giełda) wykorzystując gorm, a dane załadować z listy przy uruchomieniu   
:white_check_mark: 4.0 Należy stworzyć klasę proxy, która pobierze dane z serwisu zewnętrznego podczas zapytania do naszego kontrolera  
:white_check_mark: 4.5 Należy zapisać pobrane dane z zewnątrz do bazy danych  
:white_check_mark: 5.0 Należy rozszerzyć endpoint na więcej niż jedną lokalizację (Pogoda), lub akcje (Giełda) zwracając JSONa  

Kod: [/zadanie4](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie4)


**ZADANIE 5.** React 

:white_check_mark: 3.0 W ramach projektu należy stworzyć dwa komponenty: Uslugi oraz Płatności; Płatności powinny wysyłać do aplikacji serwerowej dane, a w Uslugach powinniśmy pobierać dane o usługach z aplikacji serwerowej  
:white_check_mark: 3.5 Należy dodać komponent Zamówienia wraz z widokiem; należy wykorzystać routing  
:white_check_mark: 4.0 Dane pomiędzy wszystkimi komponentami powinny być przesyłane za pomocą React hooks  
:white_check_mark: 4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz kliencką na dockerze via docker-compose  
:white_check_mark: 5.0 Należy wykorzystać axios'a oraz dodać nagłówki pod CORS  

Kod: [ebiznes/react](https://github.com/Ech0n/ebiznes/tree/main/react)


**ZADANIE 6.** Sonar

:white_check_mark: 3.0 Należy dodać eslint w hookach gita  
:white_check_mark: 3.5 Należy wyeliminować wszystkie bugi w kodzie w Sonarze (kod aplikacji klienckiej)  
:white_check_mark: 4.0 Należy wyeliminować wszystkie zapaszki w kodzie w Sonarze (kod aplikacji klienckiej)  
:white_check_mark: 4.5 Należy wyeliminować wszystkie podatności oraz błędy bezpieczeństwa w kodzie w Sonarze (kod aplikacji klienckiej)  
:white_check_mark: 5.0 Zredukować duplikaty kodu do 0%  

Kod: [/EbiznesReactApp](https://github.com/Ech0n/EbiznesReactApp)


**ZADANIE 7.** Swift


:white_check_mark: 3.0 Należy stworzyć kontroler wraz z modele Produktów zgodny z CRUD w ORM Fluent  
:white_check_mark: 3.5 Należy stworzyć szablony w Leaf  
:white_check_mark: 4.0 Należy stworzyć drugi model oraz kontroler Kategorii wraz z relacją  
:x: 4.5 Należy wykorzystać Redis do przechowywania danych  
:x: 5.0 Wrzucić aplikację na heroku  

Kod: [/swift](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/swift)

Uruchamianie: `swift run`

**ZADANIE 8.** Python testy

:white_check_mark: 3.0 Należy stworzyć 30 przypadków testowych w Pythonie w WebDriverze  
:white_check_mark: 3.5 Należy rozszerzyć testy funkcjonalne, aby zawierały minimum 100 asercji  
:x: 4.0 Należy stworzyć testy jednostkowe do wybranego wcześniejszego projektu z minimum 100 asercjami  
:x: 4.5 Należy dodać testy API, należy pokryć wszystkie endpointy z minimum jednym scenariuszem negatywnym per endpoint  
:x: 5.0 Należy uruchomić testy funkcjonalne na Browserstacku na urządzeniu mobilnym

Kod: [/zadanie8](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie8)

#### How to run:
1. go to app folder and run docker container: `docker buildx build -t <name> .`
2. install selenium and chrome web driver and run `python ./functionalTests/test_svelte_app.py`


#### Site available at: (http://localhost:8000/index.html/#)[http://localhost:8000/index.html/#]

**ZADANIE 9.** Android

:white_check_mark: 3.0 stworzyć listę kategorii oraz produktów  
:white_check_mark: 3.5 dodać widok koszyka  
:white_check_mark: 4.0 stworzyć bazę w Realmie  
:x: 4.5 dodać płatności w Stripe  
:x: 5.0 dodać logowanie i rejestrację via Oauth2

Kod: [/zadanie9](https://github.com/Ech0n/projektowanie-obiektowe/tree/main/zadanie9)

*Zadanie 3* Kotlin + Spring Boot

:white_check_mark: 3.0 Należy stworzyć jeden kontroler wraz z danymi wyświetlanymi z listy na endpoint’cie w formacie JSON - Kotlin + Spring Boot  
:white_check_mark: 3.5 Należy stworzyć klasę do autoryzacji (mock) jako Singleton w formie eager  
:white_check_mark: 4.0 Należy obsłużyć dane autoryzacji przekazywane przez użytkownika  
:white_check_mark: 4.5 Należy wstrzyknąć singleton do głównej klasy via @Autowired  
:white_check_mark: 5.0 Obok wersji Eager do wyboru powinna być wersja Singletona w wersji lazy

### Wymagania:
- gradle
- java 21, <i>można spóbować z innymi wersjami java po zakomentowaniu sekcji java w `build.gradle.kts`

### Uruchamianie
 
#### windows:
`./gradlew.bat bootRun`
#### linux:
`./gradlew bootRun`

Po uruchomieniu, widoczny będzie progress bar który zatrzyma się na 85%, oznacza to, że serwer jest uruchomiony i działa.

------------------

#### testy:
`./testEndpoints.sh` - Wykonuję testy sprawdzające, czy użytkownik z poprawnymi danymi zostanie zalogowany, a z niepoprawnymi nie, dla dwóch usług (lazy oraz eager).
Zadanie 2 Symfony + PHP


:white_check_mark: 3.0 Należy stworzyć jeden model z kontrolerem z produktami, zgodnie z CRUD
:white_check_mark: 3.5 Należy stworzyć skrypty do testów endpointów via curl
:white_check_mark: 4.0 Należy stworzyć dwa dodatkowe kontrolery wraz z modelami
:white_check_mark: 4.5 Należy stworzyć widoki do wszystkich kontrolerów
:x: 5.0 Stworzenie panelu administracyjnego z mockowanym logowaniem
## Uruchamianie

`./run.sh` uruchamia dockera, a w nim server symfony oraz skrypt w bashu testujący endpointy. Serwer wyłącza się kiedy użytkownik wprowadzi jakikolwiek input.

## Działanie serwera:
Serwer udostępnia widoki do kontrolerów na adresach odpowiadającym nazwą kontrolerów:
- http://localhost:8000/products - widok produktów
- http://localhost:8000/categories - widok kategorii
- http://localhost:8000/transactions - widok tranzakcji
# Zadanie 1. Paradygmaty:

## Wykonane polecenia:
3.0 Procedura do generowania 50 losowych liczb od 0 do 100  
3.5 Procedura do sortowania liczb  
4.0 Dodanie parametrów do procedury losującej określającymi zakres  
losowania: od, do, ile  
4.5 5 testów jednostkowych testujące procedury  
5.0 Skrypt w bashu do uruchamiania aplikacji w Pascalu via docker  

## Uruchamianie:
`./start.sh` - Jeżeli obraz dockerowy nie istnieje tworzy go z pliku Dockerfile. Następnie uruchamia kontener a w nim kompiluje i uruchamia program w pascalu.  
`./start.sh <nazwa_obrazu> <tag_obrazu>` - Uruchomienie start.sh z dodatkowymi argumentami specyfikującymi jakiego obrazu użyć. 
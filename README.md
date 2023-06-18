# rankingi_javafx

Program wyliczajacy rankingi godzinowe na podstawie wczytanych arkuszy kalkulacyjnych.\
Program obsluguje pliki w formacie .xls.\
Pliki powinny miec nazwe wg wzoru: "nazwisko"_"imie".xls.\
Pliki nie spelnijace tego wzoru zostaja pominiete.\
Aby program dzialal poprawnie arkusze kalkulacyjne
powinny byc sformatowane wg wzoru podanego w przykladowych danych.\
Program tworzy rankingi

a) pracownikow wg przepracowanych godzin we wszystkich projektach

b) miesiecy (np. 1. styczen 2015 - 43 godzin, 2. luty 2014 - 42 godziny) wg przepracowanych godzin we wszystkich projektach przez wszystkich pracownikow

c) 10-ciu najbardziej\najmniej pracowitych dni (np. 1. 5 wrzesien 2015 - 43 godziny, 2. 4 wrzesien - 42 godziny) wg przepracowanych godzin we wszystkich projektach przez wszystkich pracownikow. Jesli kilka ostatnich pozycji bedzie mialo rowna liczbe godzin, a liczba pozycji w rankingu bedzie przekraczac limit to zostaja one wyswietlone

### Obsluga programu
Program otwieramy dwukrotnym kliknieciem na plik JAR\
lub z terminalu wpisujac ponizsza komende
```
java -jar <plikjar>
```
Po lewej stronie znajduja sie przyciski, przekierowujace do poszczegolnych rankingow.
Na stronie glownej znajduje sie przycisk "Wczytaj dane". Nalezy go kliknac, a nastepnie
wskazac katalog z plikami .xls. Jesli we wskazanym katalogu nie ma plikow .xls lub ich nazwa nie jest zgodna
ze wzorem wspomnianym wyzej, pod przyciskiem pojawi sie komunikat "Brak plikow XLS".
Jesli uda nam sie wczystac dane pojawi sie komunikat "Wczytano dane".

Nastepnie klikajac na przyciski po lewej stronie, mozemy zobaczyc poszczegolne rankingi.

Kazdy z widokow posiada dodatkowe przyciski:\
a) przycisk ROSNACO/MALEJACO sortuje i podaje ranking 10 pozycji z najwieksza/najmniejsza liczba godzin.\
b) przycisk XLS, ktory zapisuje ranking do pliku .xls w miejscu w ktorym uruchamiany jest plik jar. Ponowne klikniecie przycisku powoduje nadpisanie pliku. Kazdy widok ma z gory ustalona nazwe pliku.\
c) przycisk PDF, ktory zapisuje aktulany widok do pliku PDF.

W prawym gornym rogu programu znajduje sie przycisk Exit, ktory konczy dzialanie programu.

Plik [JAR](https://github.com/dol568/rankings_javafx/blob/main/target/rankings_javafx-1.0-SNAPSHOT-shaded.jar)

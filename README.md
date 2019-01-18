# projekt-obiektowe
Pod args[0] jest zawsze ścieżka do pliku więc linię zaczynamy zawsze od ścieżki do pliku

np. /txt/konstytucja.txt

albo /txt/uokik.txt

jeśli chcemy wyświetlić spis treści wpisujemy po nazwie pliku tableofcontent

np. /txt/konstytucja.txt tableofcontent

jeśli chcemy wyświetlić spis treści określonego działu wpisujemy na końcu dz.* (gdzie * jest dowolonym numerem działu)

np. /txt/konstytucja.txt tableofcontent dz.3

jeśli chcemy wyświetlić konkretny rozdział wpisujemy po ścieżce do pliku rdz.* (gdzie * jest dowolonym numerem rozdziału)

np. /txt/konstytucja.txt rdz.6

jeśli chcemy wyświetlić zakres artykułów po ścieżce do pliku wpisujemy art.*-^ (gdzie * numerem artykułu startowego, a ^ 

numerem artykułu końcowego)

np. /txt/konstytucja.txt art.67a-113j

jeśli chcemy wyświetlić konkretny element, np artykuł 5, ustęp 3, punkt 2, litera c wpiszemy

np. /txt/konstytucja.txt art.5 ust.5 pkt.2 lit.c

czasami artykuł nie ma ustępów tylko od razu punkty, wtedy po prostu pomijamy ustep, piszemy

np. /txt/konstytucja.txt art.5 pkt.2 lit.d

mozemy tez wyswietlic okreslony ustep,punkt lub artykuł piszac po prostu

np. /txt/konstytucja.txt art.5 ust.2 pkt.4

lub

/txt/konstytucja.txt art.5 ust.2

lub

/txt/konstytucja.txt art.5

JESLI NUMER SZUKANEGO ELELEMNTU JEST RZYMSKI WPISUJEMY RZYMSKI, JESLI ARABSKI TO ARABSKI.

Nazewnictwo które przyjąłem:

Document - cały dokument (przechowuje treść wstępu)

Section - dział

Chapter - rozdział

Article - artykuł

Point - ustęp

Subpoint - punkt

letter - litera

a więc jest: Document (przechowuje listę sections) -> Section(posiada liste chapterów) -> chapter (posiada listę articles)

-> article (posiada listę points) -> point (posiada listę subpoints) -> subpoint (posiada listę letters)

to zaimplementowałem dzięki klasie abstrakcyjnej AbstractComponent z typem generycznym który motywuje co będzie
przechowywane w liście.

DocumentParser ma w sobie główną metodę parseDocument która bierze za argument ścieżkę do pliku i parsuje go do
formy obiektowej wykorzystując inne prywatne metody i klase scanner. Wtedy też usuwane są z pliku wszystkie rzeczy, które
miały być usuwane, w tym przejścia do nowej linii włącznie z przenieniem słowa linikę niżej.
Wszystkie metody wykorzystywane do obsługi pliku, tj. wyświetlania konkretnych jego fragmentów znajdują się w klasie Document.

N-am fost foarte consistent cand vine vorba de error handling, nici nu stiu care e varianta corecta (sau daca exista in proiect)
Nu stiu (nici macar cum sa caut) cum sa pun restrictii cand vine vorba de request-urile mele:


La rentals poti apela request-ul de:
 startRental - ar trebui sa fie folosit cand am o data de start la rent, dar n-am una de final (inca este rented)
 endRental - ar trebui sa fie folosit cand exista un rental care are endDate null; face put pe Rental si ii pune endDate data de azi
 addOldRental - doar un record de rental - are toti parametrii
 endRentalForItemWithId/{id} - daca o carte a fost adusa la librarie, sa i se dea end cu asta fara sa fie cautata manual (doar voiam sa vad daca se poate)

- TBD : daca se sterge un element din baza de date, tot ce e corelat cu el se sterge (mai putin daca se sterge un rental) (ex. daca e sters un client, si toate rental-urile lui sunt sterse) (am incercat dar nu mergea cum trebuie, codul e simplu dar nu i-am dat de capat; inca e comentat)

Good Points:
-Am incercat sa mentin MVC-ul si sa ma joc cu colectii,e rror handling, REST, spring boot in general etc.Totul e destul de basic, sunt multe de imbunatatit aici, dar mai am alte cateva proiecte de la facultate :(

Bad points:
 
Request-urile astea pot fi folosite nu cum au fost ele gandite de mine si clar e o problema, nu stiu care e best practice aici, sper sa primesc de la voi un feedback

Error handling - inconsistent, stiu, sper sa primesc de la voi un feedback legat de unde ar trebui puse exceptiile/care e best practice pentru ce behavior voiam eu sa implementez; Exceptia default ( new Exception() ) n-ar trebui folosita, din pacate din lipsa de timp am recurs la asa ceva


Asta e tot din perspectiva mea, astept cu nerabdare si feedback-ul vostru







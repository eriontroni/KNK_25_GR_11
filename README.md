KNK_25_GR_11
Aplikacion për Menaxhimin e Hotelit
Projekt në JavaFX + PostgreSQL
Autor: Erion Troni

Përshkrim
Menaxhon rezervimet, dhomat, klientët, ofertat dhe përdoruesit e hotelit.
UI me JavaFX dhe databazë PostgreSQL. Arkitekturë MVC.

Rrjedha Kryesore
Kyçja/Regjistrimi sipas rolit (admin, staf, klient)

Dashboard me opsione të personalizuara për secilin rol

Rezervimi i dhomës: zgjedhja e datave, llojit, pagesës, kodi promocional (opsional)

Menaxhimi i dhomave & llojeve (admin)

Ofertat & zbritjet (admin)

Teknologji:

Java 17+
JavaFX (UI)
PostgreSQL (databaza)
FXML (views)
Maven

Instalim Kërkesat:
Java 17+
PostgreSQL
Maven

Databaza:
Krijo një databazë të re (hotel_db)
Bej Run skriptat SQL në /sql/

BUILD AND RUN:
git clone https://github.com/eriontroni/KNK_25_GR_11.git
cd KNK_25_GR_11
mvn clean install
java -jar target/KNK_25_GR_11-1.0-SNAPSHOT.jar
Ose hap projektin me IntelliJ dhe starto MainApp.

Struktura:
/controllers — Kontrollerët e UI-së
/models — Modelet dhe DTO-të
/repository — Komunikimi me databazën
/views — Fajllat FXML
/utils — Funksione ndihmëse
/sql — Skriptat për DB

Funksionalitete:
  Regjistrim & login për role të ndryshme
  Krijim & menaxhim rezervimesh
  Menaxhim dhomash/llojeve/çmimesh
  Ofertat & kodet promocionale
  UI miqësore dhe intuitive
Kontributi:
Pull requests janë të mirëseardhura!

Për çdo problem, hap një issue ose kontakto eriontroni.

Screenshot:
![image](https://github.com/user-attachments/assets/1d43e0b1-26c5-4061-8ae3-96c48733d471)


Done by:

 Erion Troni
 Leon  Troni
 Natyra Bajgora
 Vesa Hadergjonaj
 Era Berisha
 Elona Kuqi
Universiteti i Prishtinës, Inxhinieri Kompjuterike dhe Softuerike

## 🔐 Połączenie z MariaDB przez tunel SSH w DataGrip (Szuflandia)

Ta instrukcja pomoże Ci skonfigurować połączenie z bazą danych MariaDB na serwerze **suflandia.pjwstk.edu.pl** z użyciem tunelowania SSH w **DataGrip**.

---

### 🧾 Dane logowania SSH

| Parametr     | Wartość                        |
|--------------|---------------------------------|
| **Host**     | `suflandia.pjwstk.edu.pl`       |
| **Port**     | `22`                            |
| **User**     | `Twój numer konta studenta (s0001)`     |
| **Password** | `Hasło do konta studenta`       |

---

### 🗄️ Dane połączenia z bazą MariaDB (na zdalnym serwerze)

| Parametr     | Wartość                                                              |
|--------------|-----------------------------------------------------------------------|
| **Host**     | `localhost`                                                           |
| **Port**     | `3306`                                                                |
| **User**     | `Twój numer konta studenta (s0001)`                                           |
| **Password** | `Imię.Nazw` → np. `Jan.Kowa`                                          |

ℹ️ Hasło ma format: **pierwsze 3 litery imienia**, kropka, **pierwsze 4 litery nazwiska**.  
Np. dla `Jan Kowalski` → **Jan.Kowa**

---

### 🧩 Konfiguracja w DataGrip – krok po kroku

#### 1️⃣ Utwórz nowe połączenie

- Kliknij `+` → **Data Source** → **MariaDB**

#### 2️⃣ Uzupełnij dane połączenia

- **Host:** `localhost`  
- **Port:** `3306`  
- **User:** `Twój numer konta studenta`  
- **Password:** `Hasło do bazy`  
- **Database:** (opcjonalnie)

---

#### 3️⃣ Skonfiguruj tunel SSH

Przejdź do zakładki **SSH/SSL** i zaznacz **Use SSH tunnel**. Wprowadź dane:

| Pole                | Wartość                        |
|---------------------|---------------------------------|
| **Host**            | `suflandia.pjwstk.edu.pl`       |
| **Port**            | `22`                            |
| **User**            | `Twój numer konta studenta (s0001)`     |
| **Authentication**  | `Password`                      |
| **Password**        | `Hasło do konta studenta`       |
<!-- 
| **Local port**      | (pozostaw domyślnie `0`)        | 
| **Remote port**     | `3306`                          |
| **Remote host**     | `localhost`                     |
-->
---

#### 4️⃣ Pobierz sterownik MariaDB

Jeśli DataGrip jeszcze nie ma sterownika, kliknij **Download missing driver files**.

---

#### 5️⃣ Przetestuj połączenie

Kliknij **Test Connection** – jeśli wszystko działa, pojawi się komunikat **Successful** ✅

---

### ✅ Gotowe!

Jesteś połączony z bazą danych na Szuflandii za pomocą tunelowania SSH – możesz wykonywać zapytania i zarządzać danymi prosto z DataGrip.

---

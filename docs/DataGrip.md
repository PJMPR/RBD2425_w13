## 🧩 Konfiguracja połączenia z MariaDB w DataGrip

Poniżej znajdziesz instrukcję, jak połączyć się z lokalnym kontenerem **MariaDB** przy użyciu **DataGrip**.

---

### 1️⃣ Otwórz DataGrip i dodaj nowe połączenie

1. Kliknij `+` → **Data Source** → **MariaDB**
2. Wybierz **MariaDB** z listy dostępnych baz danych

---

### 2️⃣ Uzupełnij dane połączenia

| Pole                 | Wartość               |
|----------------------|------------------------|
| **Host**             | `localhost`            |
| **Port**             | `3306`                 |
| **User**             | `pjwstk` (lub `root`)  |
| **Password**         | `password`             |
| **Database**         | `workdb`               |

🔐 Jeśli używasz `root`, pole `Database` może pozostać puste.

---

### 3️⃣ Załaduj sterownik MariaDB

Jeśli DataGrip nie ma jeszcze zainstalowanego sterownika MariaDB, kliknij przycisk **Download missing driver files**. Po pobraniu wszystko będzie gotowe do połączenia.

---

### 4️⃣ Przetestuj połączenie

Kliknij przycisk **Test Connection** – jeśli wszystko jest skonfigurowane poprawnie, zobaczysz komunikat **Successful**.

---

### ✅ Gotowe!

Możesz teraz przeglądać strukturę bazy, wykonywać zapytania SQL i zarządzać danymi bezpośrednio z DataGrip.

---

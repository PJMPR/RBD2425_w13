# JDBC + MariaDB – Projekt edukacyjny

To repozytorium zawiera materiały do nauki **programowania baz danych w Javie** z użyciem **JDBC** i **MariaDB**, z naciskiem na praktyczne wzorce projektowe.

---

## 📚 Zawartość projektu

W repozytorium znajdują się:

* Dokumentacja i przykładowy kod implementacji wzorca **Repository** (z CRUD)
* Narzędzie do **budowania zapytań SQL** (`SELECT`) przy pomocy wzorca **Builder**
* Obsługa transakcji z użyciem wzorca **Unit of Work** (z `commit`/`rollback`)
* Skrypty SQL do utworzenia struktury i danych w bazie MariaDB

---

## 🧭 Przewodnik po materiałach

### 1. `jdbc.md`

Opis implementacji wzorca **Repository** do obsługi danych klientów z wykorzystaniem czystego JDBC – bez Hibernate, bez Springa.

### 2. `SqlQueryBuilder.md`

Dokumentacja narzędzia umożliwiającego dynamiczne budowanie zapytań SQL (`SELECT`, `JOIN`, `GROUP BY`, `HAVING`, `ORDER BY`) zgodnie z wzorcem **Builder** i interfejsem fluent API.

### 3. `UnitOfWork.md`

Przykład implementacji transakcyjności na bazie danych z użyciem wzorca **Unit of Work**, bez użycia lambd – dostosowany do poziomu studentów rozpoczynających pracę z JDBC.

---

## 🔧 Skrypty SQL

Dołączone skrypty:

* `01_create_tables.sql` – tworzenie tabel
* `02_add_foreign_keys.sql` – dodanie kluczy obcych
* `sample-inserts.sql` – przykładowe dane do testowania repozytoriów

---


## 🎓 Cel dydaktyczny

Projekt został przygotowany jako materiał pomocniczy do zajęć i ma za zadanie:

* Pokazać jak korzystać z JDBC do obsługi relacyjnej bazy danych
* Przedstawić wzorce projektowe przydatne w organizacji kodu warstwy dostępu do danych
* Ułatwić zrozumienie i ćwiczenie transakcji oraz zapytań SQL

---


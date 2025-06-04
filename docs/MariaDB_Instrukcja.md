# 📘 MariaDB – Instrukcja Uruchomienia

Ten projekt zawiera instrukcje dotyczące uruchomienia kontenera **MariaDB** za pomocą **Dockera**. Znajdziesz tu wszystkie niezbędne kroki do uruchomienia bazy danych z własnymi ustawieniami użytkownika, hasła oraz nazwy bazy danych.

---

## ⚙️ Wymagania wstępne

- Zainstalowany **Docker** - Na komputerach szkolnych już jest, wystarczy go uruchomić.  
  👉 [Instrukcja instalacji Dockera](https://www.docker.com/)

---

## 🚀 Uruchomienie MariaDB

### 1️⃣ Pobierz obraz MariaDB

```bash
docker pull mariadb
```

---

### 2️⃣ Uruchom kontener MariaDB

Dostosuj poniższą komendę do swoich potrzeb:

```bash
docker run -d \
  --name mariadb-container \
  -e MARIADB_ROOT_PASSWORD=password \
  -e MARIADB_DATABASE=workdb \
  -e MARIADB_USER=pjwstk \
  -e MARIADB_PASSWORD=password \
  -p 3306:3306 \
  mariadb
```

---

### 3️⃣ Sprawdź działanie kontenera

```bash
docker ps
```

---

## ⛔ Zatrzymywanie i usuwanie kontenera

### 🛑 Zatrzymanie kontenera

```bash
docker stop mariadb-container
```

### 🗑️ Usunięcie kontenera

```bash
docker rm mariadb-container
```

---

## 🔌 Połączenie z bazą danych

Możesz użyć dowolnego klienta MySQL/MariaDB (np. **MySQL Workbench**, **phpMyAdmin**, CLI `mysql`) lub połączyć się ręcznie:

### 🔐 Połączenie jako `root`:

- **Host:** `localhost`  
- **Port:** `3306`  
- **Użytkownik:** `root`  
- **Hasło:** `password`

---

### 👤 Połączenie jako nowy użytkownik:

- **Host:** `localhost`  
- **Port:** `3306`  
- **Użytkownik:** `pjwstk`  
- **Hasło:** `password`  
- **Baza danych:** `workdb`

---

## 📝 Uwagi

- 🔒 **Zadbaj o bezpieczeństwo danych i haseł.**
- ✅ Upewnij się, że port `3306` jest dostępny i nieużywany przez inne aplikacje.
- 📚 Możesz uruchomić wiele instancji MariaDB — pamiętaj wtedy o zmianie portów i nazw kontenerów.

---

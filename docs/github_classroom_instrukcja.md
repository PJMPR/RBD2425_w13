
# 🎓 Instrukcja dla Studentów: Praca z **GitHub Classroom**

---

## 📝 1. Akceptacja Assignmentu

Po otrzymaniu linku do assignmentu z **GitHub Classroom**:

1. Kliknij link otrzymany od prowadzącego (np. przez Teams, e-mail).
2. Zaloguj się na swoje konto **GitHub** (lub załóż konto, jeśli jeszcze go nie masz).
3. Kliknij **✅ "Accept the assignment"**.
4. Po zaakceptowaniu zadania, GitHub Classroom **automatycznie utworzy prywatne repozytorium** z gotowym szablonem projektu.
5. Po chwili pojawi się link do Twojego repozytorium – zapisz go lub kliknij, by przejść do projektu.

---

## 💻 2. Klonowanie repozytorium na komputer

1. Otwórz stronę swojego repozytorium na GitHubie.
2. Kliknij zielony przycisk **"<> Code"** i skopiuj link (HTTPS).
3. W terminalu/IDE wpisz:

```bash
git clone <skopiowany_link>
```

📌 *Przykład:*

```bash
git clone https://github.com/pjwstk-2024-programowanie/lab1-jan-kowalski.git
```

4. Przejdź do folderu z projektem:

```bash
cd lab1-jan-kowalski
```

---

## 🛠 3. Rozwiązanie zadania

1. Otwórz projekt w swoim ulubionym edytorze (np. VS Code, IntelliJ, DataGrip).
2. Zaimplementuj swoje rozwiązanie.
3. Upewnij się, że projekt działa lokalnie i spełnia wymagania.

---

## 📤 4. Commitowanie i pushowanie zmian

Po zakończeniu pracy:

```bash
git add .
git commit -m "Rozwiązanie zadania"
git push
```

✅ *Twoje zmiany zostaną zapisane w repozytorium na GitHubie.*

---
<!--
## ⚙️ 5. (Opcjonalnie) GitHub Actions

Jeśli prowadzący skonfigurował testy automatyczne:

1. Przejdź do zakładki **"Actions"** w repozytorium.
2. Sprawdź status workflow:
   - 🟢 **zielony** – wszystko działa poprawnie,
   - 🔴 **czerwony** – wystąpił błąd.
3. Kliknij w nieudany krok, aby zobaczyć szczegóły i naprawić błędy.

---
-->
## 📬 6. Oddanie zadania

Twoje zadanie jest uznawane za **oddane**, gdy:

- ✅ Repozytorium zawiera Twoje rozwiązanie,
- ✅ (Jeśli dotyczy) Testy z GitHub Actions zakończyły się sukcesem,
- ✅ Prowadzący ma dostęp do Twojego repozytorium.

📎 *Czasem może być potrzebne przesłanie linku – zależy od wymagań prowadzącego.*

---

## ❗️Ważne

- Nie usuwaj repozytorium!
- Nie zmieniaj jego prywatności – prowadzący musi mieć dostęp!
- Pracuj w repozytorium przypisanym Tobie – nie kopiuj cudzych prac.

---


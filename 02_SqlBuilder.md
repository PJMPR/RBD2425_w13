# Budowanie SQL SELECT z wykorzystaniem Wzorca Builder w Javie

W tym dokumencie pokazujemy, jak zbudować narzędzie do dynamicznego tworzenia zapytań SQL SELECT w języku Java. Narzędzie to będzie korzystać z wzorca projektowego **Builder** oraz **Fluent Interface**, umożliwiając wygodne budowanie zapytań z `JOIN`, `WHERE`, `GROUP BY`, `HAVING` i innymi.

---

## 🎯 Wzorce projektowe użyte w rozwiązaniu

* **Builder Pattern** – oddziela budowę zapytania od jego reprezentacji końcowej
* **Fluent Interface** – pozwala budować zapytania w stylu łańcuchowym (chaining)
* (Opcjonalnie) **Strategy Pattern** – dla różnych typów zapytań (nie będzie stosowany w podstawowej wersji)

---

## 📦 Struktura klasy `SqlQueryBuilder`

**`util/SqlQueryBuilder.java`**

```java
package util;

public class SqlQueryBuilder {
    private StringBuilder select = new StringBuilder();
    private StringBuilder from = new StringBuilder();
    private StringBuilder join = new StringBuilder();
    private StringBuilder where = new StringBuilder();
    private StringBuilder groupBy = new StringBuilder();
    private StringBuilder having = new StringBuilder();
    private StringBuilder orderBy = new StringBuilder();

    public SqlQueryBuilder select(String columns) {
        select.append("SELECT ").append(columns);
        return this;
    }

    public SqlQueryBuilder from(String table) {
        from.append(" FROM ").append(table);
        return this;
    }

    public SqlQueryBuilder join(String table, String condition) {
        join.append(" JOIN ").append(table).append(" ON ").append(condition);
        return this;
    }

    public SqlQueryBuilder where(String condition) {
        if (where.length() == 0) {
            where.append(" WHERE ").append(condition);
        } else {
            where.append(" AND ").append(condition);
        }
        return this;
    }

    public SqlQueryBuilder groupBy(String columns) {
        groupBy.append(" GROUP BY ").append(columns);
        return this;
    }

    public SqlQueryBuilder having(String condition) {
        having.append(" HAVING ").append(condition);
        return this;
    }

    public SqlQueryBuilder orderBy(String columns) {
        orderBy.append(" ORDER BY ").append(columns);
        return this;
    }

    public String build() {
        return select.toString()
            + from.toString()
            + join.toString()
            + where.toString()
            + groupBy.toString()
            + having.toString()
            + orderBy.toString();
    }
}
```

---

## ✅ Przykład użycia buildera

```java
SqlQueryBuilder builder = new SqlQueryBuilder();
String query = builder
    .select("c.customer_id, c.first_name, COUNT(o.order_id) AS total_orders")
    .from("customers c")
    .join("orders o", "c.customer_id = o.customer_id")
    .where("o.status = 'delivered'")
    .groupBy("c.customer_id")
    .having("COUNT(o.order_id) > 2")
    .orderBy("total_orders DESC")
    .build();

System.out.println(query);
```

Wynik:

```sql
SELECT c.customer_id, c.first_name, COUNT(o.order_id) AS total_orders
 FROM customers c
 JOIN orders o ON c.customer_id = o.customer_id
 WHERE o.status = 'delivered'
 GROUP BY c.customer_id
 HAVING COUNT(o.order_id) > 2
 ORDER BY total_orders DESC
```

---

## 🧪 Integracja z repozytorium

Builder może być użyty do wygenerowania zapytania, które zostanie przekazane do metody JDBC jako parametr:

```java
String query = new SqlQueryBuilder()
    .select("*")
    .from("products")
    .where("price > 100")
    .build();

PreparedStatement stmt = connection.prepareStatement(query);
ResultSet rs = stmt.executeQuery();
```

---

## 📌 Uwagi końcowe

* Można rozszerzyć builder o aliasy, podzapytania, `LEFT JOIN`, `LIMIT`, itp.
* Należy uważać na wstrzykiwanie SQL – najlepiej budować zapytanie ze znakami zapytania `?` i przekazywać parametry osobno.

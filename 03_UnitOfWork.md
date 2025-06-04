# Obsługa transakcji w JDBC z użyciem wzorca Unit of Work

W tym dokumencie przedstawiamy, jak zaimplementować zarządzanie transakcjami w bazie danych MariaDB przy użyciu czystego JDBC oraz wzorca projektowego **Unit of Work**, używając przy tym jedynie podstawowych konstrukcji języka Java (bez lambd ani interfejsu `Runnable`).

---

## 🧠 Czym jest Unit of Work?

**Unit of Work** to wzorzec projektowy, który:

* Śledzi zmiany w obiektach (dodanie, aktualizacja, usunięcie)
* Grupuje je w jedną transakcję
* Umożliwia zatwierdzenie (`commit`) lub wycofanie (`rollback`) wszystkich zmian za jednym razem

---

## 📦 Struktura klas

### 1. Interfejs `UnitOfWork`

```java
public interface UnitOfWork {
    void registerNew(SqlOperation operation);
    void registerDirty(SqlOperation operation);
    void registerDeleted(SqlOperation operation);

    void commit();
    void rollback();
}
```

### 2. Interfejs `SqlOperation`

```java
public interface SqlOperation {
    void execute();
}
```

### 3. Implementacja `JdbcUnitOfWork`

```java
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUnitOfWork implements UnitOfWork {
    private final Connection connection;
    private final List<SqlOperation> newObjects = new ArrayList<>();
    private final List<SqlOperation> dirtyObjects = new ArrayList<>();
    private final List<SqlOperation> deletedObjects = new ArrayList<>();

    public JdbcUnitOfWork(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    @Override
    public void registerNew(SqlOperation operation) {
        newObjects.add(operation);
    }

    @Override
    public void registerDirty(SqlOperation operation) {
        dirtyObjects.add(operation);
    }

    @Override
    public void registerDeleted(SqlOperation operation) {
        deletedObjects.add(operation);
    }

    @Override
    public void commit() {
        try {
            for (SqlOperation op : newObjects) {
                op.execute();
            }
            for (SqlOperation op : dirtyObjects) {
                op.execute();
            }
            for (SqlOperation op : deletedObjects) {
                op.execute();
            }
            connection.commit();
        } catch (Exception e) {
            rollback();
            throw new RuntimeException("Transaction failed and was rolled back", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Rollback failed", e);
        }
    }
}
```

---

## 🧪 Przykład użycia

```java
class SaveCustomerOperation implements SqlOperation {
    private CustomerRepository repository;
    private Customer customer;

    public SaveCustomerOperation(CustomerRepository repository, Customer customer) {
        this.repository = repository;
        this.customer = customer;
    }

    @Override
    public void execute() {
        repository.save(customer);
    }
}

class DeleteCustomerOperation implements SqlOperation {
    private CustomerRepository repository;
    private int customerId;

    public DeleteCustomerOperation(CustomerRepository repository, int customerId) {
        this.repository = repository;
        this.customerId = customerId;
    }

    @Override
    public void execute() {
        repository.delete(customerId);
    }
}

// Użycie:
try (Connection conn = DBConnection.getConnection()) {
    JdbcUnitOfWork uow = new JdbcUnitOfWork(conn);
    CustomerRepository repo = new CustomerRepositoryImpl(conn);

    Customer newCustomer = new Customer("Jan", "Kowalski", "jan.kowalski@example.com");
    uow.registerNew(new SaveCustomerOperation(repo, newCustomer));
    uow.registerDeleted(new DeleteCustomerOperation(repo, 10));

    uow.commit();

} catch (SQLException e) {
    e.printStackTrace();
}
```

---

## 📌 Uwagi

* Klasy operacji (`SaveCustomerOperation`, `DeleteCustomerOperation`) można tworzyć dla każdej potrzebnej akcji.
* Dzięki temu kod jest bardziej zrozumiały i odpowiedni dla początkujących studentów.

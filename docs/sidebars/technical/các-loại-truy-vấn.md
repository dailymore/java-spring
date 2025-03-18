# Spring Data JPA - Các Cách Truy Vấn (Từ Cơ Bản Đến Nâng Cao)

## 1. Derived Query Methods (`findByXXX()`)

✅ **Tốt cho truy vấn đơn giản**  
❌ **Không phù hợp với truy vấn phức tạp hoặc dynamic**

### Ví dụ:

```java
List<Student> findByName(String name);
List<Student> findByNameAndAge(String name, Integer age);
List<Student> findByAgeGreaterThan(int age);
```

📌 **Tương đương SQL**:

```sql
SELECT * FROM student WHERE name = 'John';
SELECT * FROM student WHERE name = 'John' AND age = 20;
SELECT * FROM student WHERE age > 18;
```

### Khi nào nên dùng?

- Khi bạn chỉ cần truy vấn đơn giản.
- Khi không có nhiều điều kiện động.

---

## 2. Query by Example (QBE) - `Example<T>`

✅ **Linh hoạt, bỏ qua điều kiện `null`**  
❌ **Không hỗ trợ truy vấn phức tạp như `JOIN`, `GROUP BY`**

### Ví dụ:

```java
Student studentExample = new Student();
studentExample.setName("John");

Example<Student> example = Example.of(studentExample);
List<Student> students = studentRepository.findAll(example);
```

📌 **Tự động bỏ qua điều kiện `null`** → Không cần viết `if-else`

### Khi nào nên dùng?

- Khi có **truy vấn động**, nhưng không quá phức tạp.
- Khi cần **bỏ qua điều kiện `null`** tự động.

---

## 3. `@Query` với JPQL (Java Persistence Query Language)

✅ **Tốt cho truy vấn phức tạp, JOIN, GROUP BY**  
❌ **Không hỗ trợ nhiều logic động như QueryBuilder**

### Ví dụ:

```java
@Query("SELECT s FROM Student s WHERE s.age > :age ORDER BY s.name")
List<Student> findStudentsOlderThan(@Param("age") int age);
```

📌 **SQL tương đương**:

```sql
SELECT * FROM student WHERE age > 18 ORDER BY name;
```

### Khi nào nên dùng?

- Khi cần **JOIN**, `GROUP BY`, hoặc logic phức tạp.
- Khi cần tối ưu hiệu suất.

---

## 4. `@Query` với Native SQL

✅ **Truy vấn nhanh, có thể tối ưu hiệu suất tốt nhất**  
❌ **Cần viết SQL thủ công**

### Ví dụ:

```java
@Query(value = "SELECT * FROM student WHERE age > :age ORDER BY name", nativeQuery = true)
List<Student> findNativeStudentsOlderThan(@Param("age") int age);
```

📌 **SQL chạy trực tiếp trên database**

### Khi nào nên dùng?

- Khi cần **hiệu suất tối đa**.
- Khi cần **truy vấn đặc thù của từng database**.

---

## 5. Criteria API (Tương đương QueryBuilder trong NestJS)

✅ **Tốt nhất cho truy vấn động, phức tạp**  
✅ **Hỗ trợ JOIN, FILTER, PAGINATION, GROUP BY, SORTING**  
❌ **Code dài hơn, khó đọc hơn `@Query`**

### Ví dụ (Dynamic Query):

```java
public List<Student> searchStudents(String name, Integer minAge, Integer maxAge) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> query = cb.createQuery(Student.class);
    Root<Student> root = query.from(Student.class);

    List<Predicate> predicates = new ArrayList<>();

    if (name != null) {
        predicates.add(cb.like(root.get("name"), "%" + name + "%"));
    }
    if (minAge != null) {
        predicates.add(cb.greaterThanOrEqualTo(root.get("age"), minAge));
    }
    if (maxAge != null) {
        predicates.add(cb.lessThanOrEqualTo(root.get("age"), maxAge));
    }

    query.select(root).where(predicates.toArray(new Predicate[0]));
    return entityManager.createQuery(query).getResultList();
}
```

📌 **Tương đương SQL**:

```sql
SELECT * FROM student WHERE name LIKE '%John%' AND age BETWEEN 18 AND 25;
```

### Khi nào nên dùng?

- Khi bạn cần **truy vấn động** giống **QueryBuilder trong NestJS**.
- Khi có nhiều điều kiện **filter, sort, pagination**.

---

## 6. Specification API (Spring Data JPA)

✅ **Giống Criteria API nhưng có thể tái sử dụng**  
❌ **Cần code nhiều hơn, nhưng dễ bảo trì hơn**

### Ví dụ:

```java
public class StudentSpecification {
    public static Specification<Student> hasName(String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }

    public static Specification<Student> hasAgeGreaterThan(int age) {
        return (root, query, cb) -> cb.greaterThan(root.get("age"), age);
    }
}
```

Dùng trong repository:

```java
List<Student> students = studentRepository.findAll(
    Specification.where(StudentSpecification.hasName("John"))
                .and(StudentSpecification.hasAgeGreaterThan(18))
);
```

### Khi nào nên dùng?

- Khi muốn **tái sử dụng các điều kiện truy vấn**.
- Khi có nhiều điều kiện **filter động**.

---

## 🔥 Tóm tắt: Nên dùng cái nào?

| **Cách truy vấn**      | **Khi nào nên dùng?**                    | **Tương đương QueryBuilder (NestJS)?**           |
| ---------------------- | ---------------------------------------- | ------------------------------------------------ |
| `findByField()`        | Truy vấn đơn giản                        | ❌                                               |
| Query by Example (QBE) | Truy vấn động, bỏ qua điều kiện `null`   | ❌                                               |
| `@Query` (JPQL)        | Truy vấn phức tạp (JOIN, GROUP BY)       | ❌                                               |
| `@Query` (Native SQL)  | Truy vấn tối ưu hiệu suất                | ❌                                               |
| **Criteria API**       | **Query động, nhiều filter, pagination** | ✅ **(Tương đương QueryBuilder trong NestJS)**   |
| **Specification API**  | **Tái sử dụng điều kiện truy vấn**       | ✅ **(Giống Criteria API nhưng dễ tái sử dụng)** |

👉 Nếu bạn **muốn viết query động giống QueryBuilder trong NestJS**, thì **Criteria API hoặc Specification API là lựa chọn tốt nhất!** 🚀

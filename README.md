# Recipe Management System - Backend

---

## 1. Project Overview
This is a **Spring Boot** application designed to manage and explore a large dataset of recipes. It features an automated data ingestion system and a robust search API optimized for high-volume data handling.

---

## 2. Technical Logic & Implementation

### **Data Ingestion Logic**
* **Automated Setup**: On startup, the `DataIngestionService` automatically parses the `recipes.json` file located in the resources folder.
* **NaN Handling**: I implemented specific logic to check for **"NaN"** (Not a Number) strings in the JSON data, specifically for fields like `rating` and `total_time`. These are converted to `NULL` values in the database to ensure data integrity and prevent application crashes.
* **Capacity**: The application successfully stores and manages all **8,451 records** in an H2 in-memory database.



### **API Features**
* **Pagination**: To handle the high volume of records efficiently, all endpoints return data in pages (defaulting to 10 results per page).
* **Sorting**: The primary recipe list is automatically sorted by **rating in descending order** to showcase high-quality content first.
* **Search Logic**: Implemented a custom **JPQL query** in the repository layer to allow simultaneous, optional filtering by `title`, `cuisine`, and `rating`.

---

## 3. Setup & Execution

* **Backend Port**: `8081`
* **Run Command**: `./mvnw spring-boot:run`
* **Database Console**: Reachable at [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
    * **JDBC URL**: `jdbc:h2:mem:recipedb`
    * **User Name**: `sa`
    * **Password**: (leave blank)



---

## 4. API Testing Examples

| Feature | Description | Example URL |
| :--- | :--- | :--- |
| **Get All** | Paginated & Sorted by Rating | `GET /api/recipes?page=1&limit=10` |
| **Search** | Filtered by title, cuisine, or rating | `GET /api/recipes/search?title=pie&rating=4.5` |

---

## 5. Submission Details
* **Source Code**: Hosted on GitHub.
* **Database**: H2 In-Memory (No external setup required).
* **Data Source**: `recipes.json` (Included in resources).
  
## 6. PDF Drive Link

https://drive.google.com/file/d/1mT5IleJLEgfPxpUuzM2nE-NvkzdHE7fD/view?usp=sharing

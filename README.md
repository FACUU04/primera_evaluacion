# Medical Appointment Manager API 

This is a RESTful API built with **Spring Boot** to manage medical appointments, patients, and healthcare professionals. 
This project was developed as part of the **Moby Digital Academy 2026** technical evaluation.

## Technologies Used
* **Java 17**
* **Spring Boot 4.0.6**
* **Spring MVC** (REST Controllers)
* **In-memory storage** (ConcurrentHashMap)
* **Maven** (Dependency management)
* **Log4j** (Informative and error logging)

## Features
* **Patient Management**: Create, retrieve, list, and delete patients.
* **Professional Management**: Create and filter professionals by specialty (e.g., Clínica, Odontología).
* **Appointment Scheduling**: 
    * Register appointments between existing patients and professionals.
    * Business logic to prevent duplicate appointments (same patient, professional, and date).
    * Filter appointments by specific date.
* **Global Exception Handling**: Custom error responses for `NoEncontradoException` and `FechaInvalidaException` using `@ControllerAdvice`.

## Project Structure
The project follows the standard MVC architecture:
* `controller`: REST endpoints definition.
* `service`: Business logic and validations.
* `repository`: In-memory data access.
* `model`: Domain entities and DTOs.
* `exception`: Custom exceptions and Global Exception Handler.

## API Endpoints

### Patients
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/pacientes` | Create a new patient |
| GET | `/pacientes/{id}` | Get patient details by ID |
| GET | `/pacientes` | List all registered patients |
| DELETE | `/pacientes/{id}` | Remove a patient |

### Professionals
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/profesionales` | Register a new professional |
| GET | `/profesionales?especialidad=...` | Filter professionals by specialty |

### Appointments
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/turnos` | Schedule a new appointment |
| GET | `/turnos` | List all scheduled appointments |
| GET | `/turnos/fecha/{yyyy-MM-dd}` | List appointments by a specific date |
| DELETE | `/turnos/{id}` | Cancel/Remove an appointment |

## ⚙️ Setup and Execution

1. **Clone the repository:**

   ```bash
   git clone https://github.com/FACUU04/primera_evaluacion.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd primera_evaluacion
   ```

3. **Run the application:**
   Using the Maven Wrapper included in the project:

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API:**
   The server will start at `http://localhost:8080`.

> **Note:** The project includes a `TestCargaInicial` class that pre-loads 2 patients, 2 professionals, and 3 appointments upon startup,
>  allowing for immediate testing of the endpoints.

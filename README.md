# Java Spring Batch Import CSV to Database

This is a Java Spring Batch project that allows you to import CSV files into a database. It provides two different approaches for importing: multi-threaded and single-threaded.

## Prerequisites

Before running this project, make sure you have the following prerequisites installed:

- Java JDK (version 17 or higher)
- Maven (for building and managing dependencies)
- Docker

## Setup

1. Clone the repository:

```bash
git clone https://github.com/uberlannunes/spring-batch-csv-to-db.git
```

2. Navigate to the project directory:
```bash
cd spring-batch-csv-to-db
```

3. Execute docker compose command to setup the Database
```bash
docker compose up -d
```

4. Run the Application
```bash
./mvnw spring-boot:run
```
> **_NOTE:_**  By default the project runs with Multi Thread approach, to switch to Single Thread follow the steps:
> 1. Comment the @Configuration on ImportCsvToDbMultiThreadJob.java 
> 2. Uncomment the @Configuration on ImportCsvToDbSingleThreadJob.java
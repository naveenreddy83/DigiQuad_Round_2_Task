# DigiQuad_Round_2_Task
# Excel File Processor (Spring Boot + Apache POI)

This project allows you to upload an Excel file and extract specific cell values using **Spring Boot** and **Apache POI**.

## Features
- Upload an Excel file (`.xlsx` or `.xls`).
- Extract a specific cell value from a given row and column.
- Handle multiple data types (String, Numeric, Date, Boolean).
- REST API for processing Excel files.

## Tech Stack
- **Spring Boot** (Java)
- **Apache POI** (Excel Processing)
- **Maven** (Dependency Management)


📌 API Endpoint
____________________________
🔹 Upload Excel & Extract Cell Value
URL:POST http://localhost:8080/api/excel/upload

Example Request in Postman:
____________________________
Set method to POST.
Select "Body" → "form-data" in Postman.
Add these form fields:
file → Choose an Excel file (e.g., sample.xlsx).
sheetIndex → Enter 0 (for the first sheet).
rowIndex → Enter 2 (for row 3).
colIndex → Enter 1 (for column B).




🎯 Contributing
___________________
Fork the repo, make changes, and submit a pull request.
Open an issue for bug reports or feature requests.
📜 License
______________
MIT License – Feel free to use and modify!


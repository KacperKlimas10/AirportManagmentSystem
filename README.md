# Airport Management System

A full-stack microservices-based system for managing airport operations, including flights, user authentication, administration panel, and infrastructure provisioning.

---

## Table of Contents
- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Architecture & Modules](#architecture--modules)
- [Features](#features)
- [Getting Started](#getting-started)

---

## Overview
This project simulates an airport management ecosystem with modular services, scalable deployment via Docker and Kubernetes, infrastructure provisioning using Terraform, and a responsive frontend UI.

---

## Tech Stack
- **Backend**: Java Spring Boot 
- **Frontend**: React.js  
- **DevOps & Infrastructure**: Docker Compose, Kubernetes, Terraform, GitHub Actions  
- **Database**: MySQL

---

## Architecture & Modules
- `serwis_logowania`: handles authentication and user sessions  
- `serwis_panel`: administration panel with managerial functionalities  
- `frontend`: user interface for interacting with system modules  
- `k8s/environments/dev`: Kubernetes manifests for development  
- `terraform/environments/dev`: Terraform scripts for infrastructure provisioning  
- `db`: database schemas or seed data  
- `docker-compose.yml`: local orchestration for rapid testing

---

## Features
- Modular microservices for authentication and airport management  
- Containerized local execution with Docker Compose  
- Production-ready deployment using Kubernetes  
- Infrastructure-as-Code provisioning with Terraform  
- Frontend UI for user and admin operations

---

## Getting Started

### Prerequisites
- Docker & Docker Compose  
- Terraform  
- kubectl (for K8s deployment)  
- Java 17+ and Node.js (if building services manually)

### Running Locally with Docker Compose
```bash
git clone https://github.com/KacperKlimas10/AirportManagmentSystem.git
cd AirportManagmentSystem
docker compose up -d --build
```
### Deploying on Kubernetes
```bash
kubectl apply -f k8s/environments/dev/
```
### Provisioning Infrastructure with Terraform
```bash
cd terraform/environments/dev
terraform init
terraform apply
```

---

## License

This project is licensed under the [MIT License](LICENSE).

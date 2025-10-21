# 📦 QR Code Generator API

Uma API simples e eficiente para **gerar QR Codes** a partir de URLs e **armazená-los automaticamente na AWS S3**.  
Desenvolvida com **Spring Boot**, **ZXing** e **AWS SDK**.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **AWS SDK (S3)**
- **ZXing** (para geração de QR Codes)
- **Maven**

---

## 🧠 Arquitetura

O projeto segue uma arquitetura em camadas e baseada em portas e adaptadores (Hexagonal):
---

## ⚙️ Configuração

### 1. Variáveis de Ambiente ou `application.properties`

No seu `Dockerfile` altere para sua REGION e BUCKET NAME do seu Amazon S3.
No seu `.ENV` adicione:

```properties
AWS_ACCESS_KEY_ID=SEU_ACCESS_KEY
AWS_SECRET_ACCESS_KEY=SUA_SECRET_KEY

FROM node:18-alpine AS frontend-build

WORKDIR /client

COPY ./client/package*.json ./
RUN npm ci

COPY ./client .
RUN npm run build

FROM python:3.11-slim

WORKDIR /app

COPY ./server/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY ./server .

COPY --from=frontend-build /client/dist ./static

EXPOSE 8000

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]

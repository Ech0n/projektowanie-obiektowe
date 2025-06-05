from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from fastapi.staticfiles import StaticFiles

app = FastAPI()

#for debug
origins = ["http://localhost:8000"]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

products = [
    {"id": 1, "name": "Laptop", "price": 999.99},
    {"id": 2, "name": "Smartphone", "price": 599.99},
    {"id": 3, "name": "Headphones", "price": 199.99}
]


@app.get("/products")
def get_products():
    return products

@app.get("/button")
def get_button_text():
    return "button text"

app.mount("/", StaticFiles(directory="static"), name="static")

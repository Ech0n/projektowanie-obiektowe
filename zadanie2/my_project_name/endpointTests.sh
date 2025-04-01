#!/bin/bash
last_product_id=0
print_products() {
    response=$(curl -s -X GET http://localhost:8000/api/products)
    

    IFS=',' read -ra values <<< "$response"  

    count=0 
    for item in "${values[@]}"; do
        echo -n "$item "
        ((count++))
        if (( count % 4 == 0 )); then
            echo
        fi
        if (( count % 4 == 1 )); then
            last_product_id=$(echo $item | sed 's/[^0-9]*\([0-9]\+\).*/\1/')
        fi
    done
    echo "last id '$last_product_id'"
}

echo "Tabela produktow przed testami"
print_products
firstId=$last_product_id

echo "Tabela produktow po dodaniu nowego produktu"
add_response=$(curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8000/api/products/add \
    -H "Content-Type: application/json" \
    -d '{"name": "PRODUKT TESTOWY", "price": 29.99,"category_id": 1}')
if [ "$add_response" -eq 201 ]; then
    echo "Produkt dodany pomyślnie!"
else
    echo "Błąd dodawania produktu! Kod HTTP: $add_response"
    exit 0;
fi
print_products

echo "Tabela produktow po usunieciu produktu testowego"
delete_response=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE http://localhost:8000/api/products/delete/$last_product_id )
echo $delete_response
if [ "$delete_response" -eq 200 ]; then
    echo "Produkt usunięty pomyślnie!"
else
    echo "Błąd usuwania produktu! Kod HTTP: $delete_response"
    exit 0;
fi

print_products

if [ $firstId -ne $last_product_id ]; then
    echo "Usunieto zly produkt"
    echo "Usunieto przed usunięciem ostatnie Id było $firstId a po usunięciu $last_product_id"
    exit 0;
fi

echo "Wszystkie testy zakończone pomyślnie!"
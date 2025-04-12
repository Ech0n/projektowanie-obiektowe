#!/bin/bash

BASE_URL=http://localhost:8080/users

# Test 1: EAGER - correct credentials
echo "===> Test EAGER (correct credentials):"
curl -X POST "$BASE_URL/auth/eager" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin"
  }'
echo -e "\n"

# Test 2: EAGER - incorrect credentials
echo "===> Test EAGER (wrong credentials):"
curl -X POST "$BASE_URL/auth/eager" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "badpassword"
  }'
echo -e "\n"


# Test 4: LAZY - correct credentials
echo "===> Test LAZY (correct credentials):"
curl -X POST "$BASE_URL/auth/lazy" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user1",
    "password": "password"
  }'
echo -e "\n"

# Test 5: LAZY - wrong credentials
echo "===> Test LAZY (wrong credentials):"
curl -X POST "$BASE_URL/auth/lazy" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "badpassword"
  }'
echo -e "\n"

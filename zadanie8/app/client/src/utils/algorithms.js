export function factorial(n) {
    if (n < 0) return null;
    if (n === 0 || n === 1) return 1;
    return n * factorial(n - 1);
}

export function fibonacci(n) {
    if (n < 0) return null;
    if (n === 0) return 0;
    if (n === 1) return 1;
    let a = 0, b = 1, temp;
    for (let i = 2; i <= n; i++) {
        temp = a + b;
        a = b;
        b = temp;
    }
    return b;
  }

export function isPrime(num) {
    if (num <= 1) return false;
    if (num <= 3) return true;
    if (num % 2 === 0 || num % 3 === 0) return false;
    for (let i = 5; i * i <= num; i += 6) {
        if (num % i === 0 || num % (i + 2) === 0) return false;
    }
    return true;
}

export function reverseString(str) {
    return str.split('').reverse().join('');
}

export function sumOfDigits(n) {
    if (n < 0) n = -n;
    return n.toString().split('').reduce((acc, digit) => acc + Number(digit), 0);
  }
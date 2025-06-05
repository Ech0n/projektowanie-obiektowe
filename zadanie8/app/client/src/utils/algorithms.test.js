import { describe, it, expect } from 'vitest';
import { factorial, fibonacci, isPrime, reverseString, sumOfDigits } from './utils';

describe('factorial', () => {
    it('should return 1 for 0 and 1', () => {
        expect(factorial(0)).toBe(1);
        expect(factorial(1)).toBe(1);
    });

    it('should return correct factorial for positive numbers', () => {
        expect(factorial(5)).toBe(120);
        expect(factorial(3)).toBe(6);
    });

    it('should return null for negative input', () => {
        expect(factorial(-2)).toBeNull();
    });
});

describe('fibonacci', () => {
    it('should return correct fibonacci number', () => {
        expect(fibonacci(0)).toBe(0);
        expect(fibonacci(1)).toBe(1);
        expect(fibonacci(5)).toBe(5);
        expect(fibonacci(10)).toBe(55);
    });

    it('should return null for negative input', () => {
        expect(fibonacci(-1)).toBeNull();
    });
});

describe('isPrime', () => {
    it('should return false for non-prime numbers', () => {
        expect(isPrime(-1)).toBe(false);
        expect(isPrime(0)).toBe(false);
        expect(isPrime(1)).toBe(false);
        expect(isPrime(4)).toBe(false);
        expect(isPrime(100)).toBe(false);
    });

    it('should return true for prime numbers', () => {
        expect(isPrime(2)).toBe(true);
        expect(isPrime(3)).toBe(true);
        expect(isPrime(5)).toBe(true);
        expect(isPrime(97)).toBe(true);
    });
});

describe('reverseString', () => {
    it('should revers strings correctly', () => {
        expect(reverseString('hello')).toBe('olleh');
        expect(reverseString('')).toBe('');
        expect(reverseString('a')).toBe('a');
        expect(reverseString('12345')).toBe('54321');
    });
});

describe('sumOfDigits', () => {
    it('should return correct sum for positive numbers', () => {
        expect(sumOfDigits(123)).toBe(6);
        expect(sumOfDigits(0)).toBe(0);
        expect(sumOfDigits(999)).toBe(27);
    });

    it('should return correct sum for negative numbers', () => {
        expect(sumOfDigits(-123)).toBe(6);
    });
});

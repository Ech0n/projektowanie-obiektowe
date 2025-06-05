<script>
  import { factorial, fibonacci, isPrime, reverseString, sumOfDigits } from '../utils/algorithms.js';

  let factInput = 0;
  let fibInput = 0;
  let primeInput = 0;
  let reverseInput = '';
  let sumInput = 0;

  $: factResult = factorial(Number(factInput));
  $: fibResult = fibonacci(Number(fibInput));
  $: primeResult = isPrime(Number(primeInput));
  $: reversed = reverseString(reverseInput);
  $: sumDigits = sumOfDigits(Number(sumInput));

  
</script>

<h2>Algorithms Page</h2>
<p>Welcome to the page with algorithms! Purpose of this page is so that it can be tested.</p>

<section style="margin-top: 2rem;">
  <h3>Factorial Calculator</h3>
  <input id="factorial-input" type="number" min="0" bind:value={factInput} placeholder="Enter a non-negative integer" />
  <p>Factorial of {factInput} is: {factResult !== null ? factResult : 'Invalid input'}</p>
</section>

<section style="margin-top: 2rem;">
  <h3>Fibonacci Calculator</h3>
  <input id="fibo-input" type="number" min="0" bind:value={fibInput} placeholder="Enter a non-negative integer" />
  <p>Fibonacci number at position {fibInput} is: {fibResult !== null ? fibResult : 'Invalid input'}</p>
</section>

<section style="margin-top: 2rem;">
  <h3>Prime Checker</h3>
  <input id="prime-input" type="number" min="0" bind:value={primeInput} placeholder="Enter an integer" />
  <p>{primeInput} is {primeResult ? 'a prime number' : 'not a prime number'}</p>
</section>

<section style="margin-top: 2rem;">
  <h3>String Reverser</h3>
  <input id="reverse-input" type="text" bind:value={reverseInput} placeholder="Enter text to reverse" />
  <p>Reversed string: "{reversed}"</p>
</section>

<section style="margin-top: 2rem;">
  <h3>Sum of Digits</h3>
  <input id="sum-input" type="number" bind:value={sumInput} placeholder="Enter an integer" />
  <p>Sum of digits of {sumInput} is: {sumDigits}</p>
</section>

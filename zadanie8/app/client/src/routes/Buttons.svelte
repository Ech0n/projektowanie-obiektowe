<script>
  import { onMount } from 'svelte';

  // For color change
  let color = 'gray';

  function changeColor() {
    color = color === 'gray' ? 'purple' : 'gray';
  }

  // For toggle text
  let showText = false;
  function toggleText() {
    showText = !showText;
  }

  // For fetch data
  let data = null;
  let loading = false;
  let error = null;

  async function fetchData() {
    loading = true;
    error = null;
    data = null;

    try {
      const res = await fetch('http://localhost:8000/button');
      if (!res.ok) throw new Error('Fetch failed');
      data = await res.json();
    } catch (err) {
      error = err.message;
    } finally {
      loading = false;
    }
  }

  let count = 0;
  function increment() {
    count += 1;
  }
  function decrement() {
    count -= 1;
  }

  let showImage = false;
  function toggleImage() {
    showImage = !showImage;
  }
</script>

<h2>Button Page</h2>
<h3>Welcome to the button test page!</h3>
<p>Purpose of this page is to test button behaviour for functional testing</p>

<section style="margin-bottom: 2rem;">
  <button on:click={changeColor} style="background-color: {color}; color: white; padding: 0.5rem 1rem; border: none;">
    Click me to change color
  </button>
</section>

<section style="margin-bottom: 2rem;">
  <button on:click={toggleText} style="padding: 0.5rem 1rem;">
    Toggle text display
  </button>
  {#if showText}
    <p style="margin-top: 1rem;">Here is some toggled text!</p>
  {/if}
</section>

<section>
  <button on:click={fetchData} style="padding: 0.5rem 1rem;">
    Fetch data from server
  </button>

  {#if loading}
    <p>Loading data...</p>
  {/if}

  {#if error}
    <p style="color: red;">Error: {error}</p>
  {/if}

  {#if data}
    <pre style="background:hsl(0, 3.60%, 11.00%); padding: 1rem; margin-top: 1rem;">{JSON.stringify(data, null, 2)}</pre>
  {/if}
</section>



<section style="margin-bottom: 2rem;">
  <button on:click={increment} style="padding: 0.5rem 1rem;">
    Increment counter
  </button>
  <p>Current count: {count}</p>
  <button on:click={decrement} style="padding: 0.5rem 1rem;">
    Decrement counter
  </button>
</section>


<section style="margin-bottom: 2rem;">
  <button on:click={toggleImage} style="padding: 0.5rem 1rem;">
    {showImage ? 'Hide' : 'Show'} image
  </button>
  {#if showImage}
    <img src="./vite.svg" alt="Placeholder" style="display: block; margin-top: 1rem;" />
  {/if}
</section>
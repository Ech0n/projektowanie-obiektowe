<script>
  import { onMount } from 'svelte';

  let products = [];
  let loading = true;
  let error = null;

  onMount(async () => {
    loading = true;
    error = null;

    try {
      const res = await fetch('http://0.0.0.0:8000/products');
      if (!res.ok) throw new Error(`Error: ${res.status} ${res.statusText}`);
      products = await res.json();
    } catch (err) {
      error = err.message;
    } finally {
      loading = false;
    }
  });
</script>

<h2>Products</h2>

{#if loading}
  <p>Loading products...</p>
{:else if error}
  <p style="color: red;">{error}</p>
{:else if products.length === 0}
  <p>No products found.</p>
{:else}
  <ul>
    {#each products as product}
      <li style="margin-bottom: 1rem; border-bottom: 1px solid #ddd; padding-bottom: 0.5rem;">
        <strong>{product.name}</strong><br />
        Price: ${product.price.toFixed(2)}<br />
        {#if product.description}
          <em>{product.description}</em>
        {/if}
      </li>
    {/each}
  </ul>
{/if}

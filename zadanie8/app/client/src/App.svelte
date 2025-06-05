<script>
  import Router from 'svelte-spa-router';
  import Home from './routes/Home.svelte';
  import Buttons from './routes/Buttons.svelte';
  import Products from './routes/Products.svelte';
  import Algorithms from './routes/Algorithms.svelte';

  const routes = {
    '/': Home,
    '/products': Products,
    '/algos': Algorithms,
    '/buttons': Buttons
  };
</script>

<nav style="margin-bottom: 1rem;">
  <a href="#/" style="margin-right: 1rem;">Home</a>
  <a href="#/products" style="margin-right: 1rem;">Products</a>
  <a href="#/algos" style="margin-right: 1rem;">Algorithms</a>
  <a href="#/buttons">Buttons</a>
</nav>

<main>
  <Router {routes} />
</main>
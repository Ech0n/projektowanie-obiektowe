<?php

namespace App\Controller;

use App\Entity\Products;
use App\Entity\Categories;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

final class ProductsController extends AbstractController
{
    private EntityManagerInterface $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }
    

    #[Route('/products', name: 'productsView', methods: ['GET'])]
    public function index(): Response
    {
        $htmlFilePath = $this->getParameter('kernel.project_dir') . '/src/views/products.html';
        
        return new Response(file_get_contents($htmlFilePath), Response::HTTP_OK, [
            'Content-Type' => 'text/html'
        ]);
    }

    #[Route('/api/products', name: 'products', methods: ['GET'])]
    public function getAll(): JsonResponse
    {
        $products = $this->entityManager->getRepository(Products::class)->findAllProducts();
        $productData = [];
        foreach ($products as $product) {
            $productData[] = [
                'id' => $product->getId(),
                'name' => $product->getName(),
                'price' => $product->getPrice(),
                'category_id' => $product->getCategory()->getId(),
            ];
        }
        return $this->json($productData);
    }

    #[Route('/api/products/add', name: 'product_add', methods: ['POST'])]
    public function add(Request $request): JsonResponse  
    {
        $data = json_decode($request->getContent(), true);
        if (!isset($data['name']) || !isset($data['price']) || !isset($data['category_id'])) {
            return new JsonResponse(['error' => 'Invalid data'], JsonResponse::HTTP_BAD_REQUEST);
        }

        $category = $this->entityManager->getRepository(Categories::class)->find($data['category_id']);
        
        if (!$category) {
            return new JsonResponse(['error' => 'Category not found'], 404);
        }

        $product = new Products();
        $product->setName($data['name']);
        $product->setPrice($data['price']);
        $product->setCategory($category);

        $this->entityManager->persist($product);
        $this->entityManager->flush();

        return new JsonResponse([
            'message' => 'Product created successfully'
        ], JsonResponse::HTTP_CREATED);
    }

    #[Route('/api/products/delete/{id}', name: 'product_delete', methods: ["DELETE"])]
    public function delete(string $id): JsonResponse
    {
        
        $decodedId = urldecode($id);
        $product = $this->entityManager->getRepository(Products::class)->find($decodedId);

        if (!$product) {
            return new JsonResponse(['error' => 'Product not found'], JsonResponse::HTTP_NOT_FOUND);
        }

        $this->entityManager->remove($product);
        $this->entityManager->flush();

        return new JsonResponse(['message' => 'Product deleted successfully']);
    }

}

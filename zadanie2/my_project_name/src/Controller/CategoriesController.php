<?php

namespace App\Controller;

use App\Entity\Categories;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

final class CategoriesController extends AbstractController
{
    private EntityManagerInterface $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }

    #[Route('/categories', name: 'categoriesView', methods: ['GET'])]
    public function index(): Response
    {
        $htmlFilePath = $this->getParameter('kernel.project_dir') . '/src/views/categories.html';
        
        return new Response(file_get_contents($htmlFilePath), Response::HTTP_OK, [
            'Content-Type' => 'text/html'
        ]);
    }

    #[Route('/api/categories', name: 'categories', methods: ['GET'])]
    public function getAll(): JsonResponse
    {
        $categories = $this->entityManager->getRepository(Categories::class)->findAllCategories();
        $categoryData = [];
        foreach ($categories as $category) {
            $categoryData[] = [
                'id' => $category->getId(),
                'name' => $category->getName(),
            ];
        }
        return $this->json($categoryData);
    }

    #[Route('/api/categories/add', name: 'categories_add', methods: ['POST'])]
    public function add(Request $request): JsonResponse  
    {
        $data = json_decode($request->getContent(), true);
        if (!isset($data['name']) ) {
            return new JsonResponse(['error' => 'Invalid data'], JsonResponse::HTTP_BAD_REQUEST);
        }
        
        $category = new Categories();
        $category->setName($data['name']);

        $this->entityManager->persist($category);
        $this->entityManager->flush();

        return new JsonResponse([
            'message' => 'category created successfully'
        ], JsonResponse::HTTP_CREATED);
    }

    #[Route('/api/categories/delete/{id}', name: 'categories_delete', methods: ["DELETE"])]
    public function delete(string $id): JsonResponse
    {
        
        $decodedId = urldecode($id);
        $category = $this->entityManager->getRepository(Categories::class)->find($decodedId);

        if (!$category) {
            return new JsonResponse(['error' => 'category not found'], JsonResponse::HTTP_NOT_FOUND);
        }

        $this->entityManager->remove($category);
        $this->entityManager->flush();

        return new JsonResponse(['message' => 'category deleted successfully']);
    }

}

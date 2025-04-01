<?php

namespace App\Controller;

use App\Entity\Transactions;
use App\Entity\Products;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

final class TransactionsController extends AbstractController
{
    private EntityManagerInterface $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }
    #[Route('/transactions', name: 'transactionsView', methods: ['GET'])]
    public function index(): Response
    {
        $htmlFilePath = $this->getParameter('kernel.project_dir') . '/src/views/transactions.html';
        
        return new Response(file_get_contents($htmlFilePath), Response::HTTP_OK, [
            'Content-Type' => 'text/html'
        ]);
    }
    #[Route('/api/transactions', name: 'transactions', methods: ['GET'])]
    public function get(): JsonResponse
    {
        $transactions = $this->entityManager->getRepository(Transactions::class)->findAllTransactions();
        $transactionsData = [];
        foreach ($transactions as $transaction) {
            $transactionData[] = [
                'transaction_id' => $transaction->getTransactionId(),
                'product' => [
                    'id' => $transaction->getProduct()->getId(),
                    'name' => $transaction->getProduct()->getName(),
                    'price' => $transaction->getProduct()->getPrice(),
                ],
                'quantity' => $transaction->getQuantity(),
                'date' => $transaction->getDate()->format('Y-m-d H:i:s'),
            ];
        }
        return $this->json($transactionData);
        
    }

    #[Route('/api/transactions/add', name: 'transactions_add', methods: ['POST'])]
    public function add(Request $request): JsonResponse  
    {
        $data = json_decode($request->getContent(), true);

        if (!isset($data['product_id'], $data['quantity'])) {
            return new JsonResponse(['error' => 'Invalid data'], JsonResponse::HTTP_BAD_REQUEST);
        }

        $product = $this->entityManager->getRepository(Products::class)->find($data['product_id']);
        
        if (!$product) {
            return new JsonResponse(['error' => 'Category not found'], 404);
        }

        $transaction = new Transactions();
        $transaction->setProduct($product);
        $transaction->setQuantity($data['quantity']);

        $date = new \DateTime();
        $transaction->setDate($date);
        
        $this->entityManager->persist($transaction);
        $this->entityManager->flush();

        return new JsonResponse([
            'message' => 'Transaction created successfully',
            'transaction_id' => $transaction->getTransactionId()
        ], JsonResponse::HTTP_CREATED);
    }

    #[Route('/api/transactions/delete/{id}', name: 'transactions_delete', methods: ["DELETE"])]
    public function delete(string $id): JsonResponse
    {
        $transaction = $this->entityManager->getRepository(Transaction::class)->find($id);

        if (!$transaction) {
            return new JsonResponse(['error' => 'Transaction not found'], JsonResponse::HTTP_NOT_FOUND);
        }

        // Remove the transaction from the database
        $this->entityManager->remove($transaction);
        $this->entityManager->flush();

        return new JsonResponse(['message' => 'Transaction deleted successfully']);
    }

}

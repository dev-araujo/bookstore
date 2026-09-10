package com.decoder.bookstore.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private static final int MAX_REVIEW_LENGTH = 500;

    private final ChatClient chatClient;

    public ReviewService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateReview(String title) {
        try {
            String prompt = """
                    Escreva um resumo objetivo e direto do livro "%s".
                    Responda em português.
                    Não inclua opiniões, julgamentos ou recomendações.
                    Não repita o título do livro na resposta.
                    Use no máximo 500 caracteres.
                    """.formatted(title);

            String review = this.chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            if (review == null) {
                return null;
            }

            String trimmedReview = review.trim();

            if (trimmedReview.length() > MAX_REVIEW_LENGTH) {
                return trimmedReview.substring(0, MAX_REVIEW_LENGTH);
            }

            return trimmedReview;
        } catch (Exception exception) {
            System.out.println("Falha ao gerar o review do livro. Este erro precisa ser tratado: " + exception.getMessage());
            // Aqui caberia um tratamento adequado: envio para fila de erro, retentativa ou circuit breaker.
            return null;
        }
    }
}

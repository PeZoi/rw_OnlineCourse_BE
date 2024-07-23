package com.example.backend_rw.utils;

import com.example.backend_rw.entity.Quiz;
import com.example.backend_rw.entity.QuizType;
import com.example.backend_rw.entity.dto.quiz.AnswerDto;
import com.example.backend_rw.entity.dto.quiz.QuizRequest;
import com.example.backend_rw.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.time.Duration;

public class Utils {
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return seconds + " giây trước";
        } else if (seconds < 3600) {
            return (seconds / 60) + " phút trước";
        } else if (seconds < 86400) {
            return (seconds / 3600) + " giờ trước";
        } else if (seconds < 2592000) {
            return (seconds / 86400) + " ngày trước";
        } else if (seconds < 31536000) {
            return (seconds / 2592000) + " tháng trước";
        } else {
            return (seconds / 31536000) + " năm trước";
        }
    }

    public static String removeVietnameseAccents(String input) {
        String result = input;

        // Remove Vietnamese accents
        result = result.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        result = result.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        result = result.replaceAll("[ìíịỉĩ]", "i");
        result = result.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        result = result.replaceAll("[ùúụủũưừứựửữ]", "u");
        result = result.replaceAll("[ỳýỵỷỹ]", "y");
        result = result.replaceAll("đ", "d");

        // Replace non-alphanumeric characters with empty string
        result = result.replaceAll("[^a-zA-Z0-9\\s]", "");

        // Trim and convert spaces to dashes
        String slug = result.trim().toLowerCase();
        slug = slug.replaceAll("\\s+", "-");

        return slug;
    }

    public static Quiz convertToQuizEntity(QuizRequest quizRequest) {
        Quiz quiz = new Quiz();
        quiz.setQuestion(quizRequest.getQuestion());
        quiz.setQuizType(QuizType.valueOf(quizRequest.getQuizType()));

        boolean flag = false;

        for (AnswerDto answerDto : quizRequest.getAnswerList()) {
            if (answerDto.isCorrect()) {
                flag = true;
            }
            quiz.add(answerDto.getContent(), answerDto.isCorrect());
        }

        if (!flag) {
            throw new CustomException("Không có câu trả lời đúng trong danh sách câu trả lời!", HttpStatus.BAD_REQUEST);
        }
        return quiz;
    }
}

package com.lgcms.core.dto.request;

public record FaqRequest(String question, String answer, String imageUrl, String url) {
}

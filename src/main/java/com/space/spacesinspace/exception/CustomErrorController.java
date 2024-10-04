package com.space.spacesinspace.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Map<String, Object> errorDetails = errorAttributes.getErrorAttributes((WebRequest) request, ErrorAttributeOptions.defaults());
        Integer statusCode = (Integer) errorDetails.get("status");

        if (statusCode != null) {
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";  // 404 페이지로 이동
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";  // 500 페이지로 이동
            }
        }

        // 그 외 에러 처리
        return "error-500";
    }
}

package com.space.spacesinspace.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @GetMapping("/error-404")
    public String notFound() {
        return "error-404";
    }

    @GetMapping("/error-500")
    public String serverError() {
        return "error-500";
    }

    @GetMapping("/error-400")
    public String badRequest() {
        return "error-500";
    }

    @GetMapping("/error-403")
    public String forbidden() {
        return "error-500";
    }

    // 405 에러 처리 (허용되지 않은 메서드)
    @GetMapping("/error-405")
    public String methodNotAllowedError() {
        return "error-500";
    }

}


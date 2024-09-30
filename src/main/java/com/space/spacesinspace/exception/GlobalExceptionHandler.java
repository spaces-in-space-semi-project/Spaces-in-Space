//package com.space.spacesinspace.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // 404 Not Found 오류 처리
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleNotFoundError(Model model) {
////        model.addAttribute("errorMessage", "The page you're looking for could not be found.");
//        return "error-404";  // 404 오류 페이지로 이동
//    }
//
//    // 기타 예외 처리 (500 등)
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleServerError(Model model, Exception ex) {
////        model.addAttribute("errorMessage", "An unexpected error occurred.");
////        model.addAttribute("errorDetails", ex.getMessage());
//        return "error-500";  // 500 오류 페이지로 이동
//    }
//}

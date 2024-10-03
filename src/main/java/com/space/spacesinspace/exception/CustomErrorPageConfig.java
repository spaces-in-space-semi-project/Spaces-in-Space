//package com.space.spacesinspace.exception;
//
//import org.springframework.boot.web.server.ConfigurableWebServerFactory;
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomErrorPageConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
//
//    @Override
//    public void customize(ConfigurableWebServerFactory factory) {
//        // 404 에러 시 error-404.html로 이동
//        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error-404");
//        // 500 에러 시 error-500.html로 이동
//        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-500");
//
//        // 400 에러 (잘못된 요청) 시 error-400.html로 이동
//        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error-400");
//
//        // 403 에러 (접근 금지) 시 error-403.html로 이동
//        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error-403");
//
//        // 405 에러 처리 (허용되지 않은 메서드)
//        ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error-405");
//
//        factory.addErrorPages(error404Page, error500Page, error400Page, error403Page, error405Page);
//    }
//}
//

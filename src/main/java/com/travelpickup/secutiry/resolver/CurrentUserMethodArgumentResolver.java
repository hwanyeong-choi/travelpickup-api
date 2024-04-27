package com.travelpickup.secutiry.resolver;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.secutiry.dto.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.expression.AccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.nio.file.AccessDeniedException;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        CurrentUser currentUser = parameter.getParameterAnnotation(CurrentUser.class);

        boolean required = currentUser.isRequired();

        if (ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication()) && required) {
            throw new AccessDeniedException("인증정보가 존재하지 않습니다.");
        }

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

}

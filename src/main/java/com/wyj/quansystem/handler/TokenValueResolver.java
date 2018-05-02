package com.wyj.quansystem.handler;

import com.wyj.quansystem.annotation.TokenValue;
import com.wyj.quansystem.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wyj
 * @date 2018/4/23 13:26
 */
@Slf4j
public class TokenValueResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TokenValue.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        TokenValue tokenValue = parameter.getParameterAnnotation(TokenValue.class);
        String value = tokenValue.value();
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return CookieUtils.getCookieValue(request, value);
    }
}

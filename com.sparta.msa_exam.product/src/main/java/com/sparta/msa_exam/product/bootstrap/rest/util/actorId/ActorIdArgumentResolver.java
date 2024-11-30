package com.sparta.msa_exam.product.bootstrap.rest.util.actorId;

import static com.sparta.msa_exam.product.bootstrap.rest.config.webConfig.HEADER_ACTOR_ID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
public class ActorIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ActorId.class) &&
            parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Long resolveArgument(
        MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
    {
        String header = webRequest.getHeader(HEADER_ACTOR_ID);
        return Long.parseLong(header);
    }
}

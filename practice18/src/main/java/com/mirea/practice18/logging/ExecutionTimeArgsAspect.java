package com.mirea.practice18.logging;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mirea.practice18.utils.L11n;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ExecutionTimeArgsAspect {
  @Value("${use_l11n}")
  private boolean useL11n;

  @Around("execution(* com.mirea.practice18.service.*.*(..))")
  public Object profile(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.currentTimeMillis();
    Object output = pjp.proceed();
    long elapsedTime = System.currentTimeMillis() - start;

    String[] paramsNames = ((CodeSignature) pjp.getSignature()).getParameterNames();
    Object[] args = pjp.getArgs();

    String params = Stream.iterate(0, i -> i + 1).limit(paramsNames.length)
        .collect(Collectors.toMap(i -> paramsNames[i], i -> {
          if (args[i] == null)
            return "null";
          if (useL11n)
            return L11n.transliterate(args[i].toString());
          return args[i].toString();
        }))
        .entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining(", "));

    log.info("{}({}) in {} ms.", pjp.getSignature().getName(), params, elapsedTime);
    return output;
  }
}
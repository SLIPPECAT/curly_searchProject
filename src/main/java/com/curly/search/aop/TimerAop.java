package com.curly.search.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class TimerAop {

	@Pointcut("@annotation(com.curly.search.aop.Timer)")
	public void loggableMethods() {}

	@Around("loggableMethods()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object result = joinPoint.proceed(); //메서드가 실행되는 부분

		stopWatch.stop();

		String joinPointNm = joinPoint.getTarget().getClass().getName();

		// double 범위 초과로 인한 지수 표현 방지
		String totalTime = String.format("%.6f",stopWatch.getTotalTimeSeconds());


		log.info("[{}s] in [{}]",totalTime,joinPointNm);

		return result;
	}
}
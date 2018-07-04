package com.aop.exceptionManage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("controllerExceptionAspect")
public class ControllerExceptionAspect extends ExceptionAspect{
	
	@Pointcut(value="(execution(* com.websocket..*.*(..)))")
	private void exceptionOperate(){};
	
	@Around(value="exceptionOperate()")
	public Object around(ProceedingJoinPoint pjd) {
		
		Object result=null;
		
		
		try {
			//前置通知
			result=pjd.proceed();
			//后置通知
		} 
		catch (Throwable e) {
			// 异常通知
			/*获取全方法名(包含方法名和类路径)*/
			String methodName=pjd.getSignature().getDeclaringTypeName()+"."+pjd.getSignature().getName();
			logger.error(methodName+".."+e.toString());
		}
		//后置通知
		return result;
	}
}

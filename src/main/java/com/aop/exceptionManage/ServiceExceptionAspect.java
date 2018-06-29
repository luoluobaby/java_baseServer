package com.aop.exceptionManage;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.exception.util.ServiceLayerException;

@Aspect
@Component("serviceExceptionAspect")
public class ServiceExceptionAspect extends ExceptionAspect {
	
	
	@Pointcut(value= "(execution(* com.webservice..*.*(..))) ")
	private void exceptionOperate(){};
	
	//@Before( value="exceptionOperate()")
	public void before(JoinPoint joinPoint) {
		System.out.println("...................");
	}
	//@AfterThrowing( pointcut="exceptionOperate()",throwing="exception")
	public void afterThrowing(JoinPoint joinPoint,Exception exception) {
		
		String methodName=joinPoint.getSignature().getName();
		
		if(exception instanceof RuntimeException)
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		logger.error("the method: "+methodName+" occurs exception: "+exception.getMessage());
	
	}
	
	@Around(value="exceptionOperate()")
	public Object around(ProceedingJoinPoint pjd) {
//		InitResponse();
		
		Object result=null;
		
		
		try {
			//前置通知
			result=pjd.proceed();
			//后置通知
		} 
		catch (RuntimeException e)
		{
			/*获取全方法名(包含方法名和类路径)*/
			String methodName=pjd.getSignature().getDeclaringTypeName()+"."+pjd.getSignature().getName();
			logger.error(methodName+"..."+e.toString());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw new ServiceLayerException(e.getMessage());
		}
		catch (Throwable e) {
			// 异常通知
			//logger.error(e.getMessage());
			throw new ServiceLayerException(e.getMessage());
			//throw new ServiceLayerException("服务控制层出现异常");
		}
		//后置通知
		return result;
	}
}

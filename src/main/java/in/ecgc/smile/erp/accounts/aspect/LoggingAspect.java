package in.ecgc.smile.erp.accounts.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Mohasin Sutar
 * Logging Aspect for the Application
 */
@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* in.ecgc.smile.erp.accounts.controller.*.*(..))")
	public void logBeforeControllerMethods(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Inside Controller Method: {}.{}()",className,methodName);
	}
	
	@After("execution(* in.ecgc.smile.erp.accounts.controller.*.*(..))")
	public void logAfterControllerMethods(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Exiting Controller Method: {}.{}()",className,methodName);
	}
	
	@Before("execution(* in.ecgc.smile.erp.accounts.service.*.*(..))")
	public void logBeforeServiceMethods(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Inside Service Method: {}.{}()",className,methodName);
	}
	
	@After("execution(* in.ecgc.smile.erp.accounts.service.*.*(..))")
	public void logAfterServiceMethods(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Exiting Service Method: {}.{}()",className,methodName);
	}
	
	@Before("execution(* in.ecgc.smile.erp.accounts.repository.*.*(..))")
	public void logBeforeRepositoryMethods(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Inside DAO Method: {}.{}()",className,methodName);
	}
	
	@After("execution(* in.ecgc.smile.erp.accounts.repository.*.*(..))")
	public void logAfterRepositoryMethods(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Exiting DAO Method: {}.{}()",className,methodName);
	}
}

package mutu.sprest.logging;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import mutu.sprest.logging.exception.DAOException;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        It is a intercepter class. Which is used to translate SQLExceptoin to
 *        custom DAOException.<br/>
 *        When DAO get SQLExceptoin or error, this class
 *        automatically intercept to translate.<br/>
 */

@Aspect
@Component
public class ExceptionHandlerAspect extends PointCutConfig {
	private static final Logger logger = LogManager.getLogger(ExceptionHandlerAspect.class);
	
	@AfterThrowing(pointcut = "publicMethodInsideComponentBean()", throwing = "e")
	public void componentThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		logger.error("Unexpected error in componentBean.", e);
		handel(joinPoint, e);
	}

	@AfterThrowing(pointcut = "publicMethodInsideServiceBean()", throwing = "e")
	public void serviceThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		logger.error("Unexpected error in serviceBean.", e);
		handel(joinPoint, e);
	}
	
	@AfterThrowing(pointcut = "publicMethodInsideDaoBean()", throwing = "e")
	public void daoThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
		logger.error("Unexpected error in daoBean", e);
		handel(joinPoint, e);
	}

	private void handel(JoinPoint joinPoint, Exception e) throws Throwable {
		DAOException dae = null;
		Throwable throwable = e;
		while (throwable != null && !(throwable instanceof SQLException)) {
			throwable = throwable.getCause();
		}
		if (throwable instanceof SQLException) {
			dae = new DAOException("SERVICE_ERROR_IN_DB_PROCESS", e.getMessage(),
					(SQLException) throwable);
		}
		if (dae != null) {
			throw dae;
		} else {
			throw e;
		}
	}
}

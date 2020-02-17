package mutu.sprest.logging;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        It is a point-cut configuration for
 *        <code>ExceptionHandlerAspect</code> and <code>LoggingAspect</code>
 */
public class PointCutConfig {
	@Pointcut("within(@org.springframework.stereotype.Component *)")
	protected void componentBean() {
	}

	@Pointcut("within(@org.springframework.stereotype.Service *)")
	protected void serviceBean() {
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	protected void daoBean() {
	}
	
	@Pointcut("execution(public * *(..))")
	protected void publicMethod() {
	}
	
	@Pointcut("publicMethod() && componentBean()")
	protected void publicMethodInsideComponentBean() {
	}

	@Pointcut("publicMethod() && serviceBean()")
	protected void publicMethodInsideServiceBean() {
	}

	@Pointcut("publicMethod() && daoBean()")
	protected void publicMethodInsideDaoBean() {
	}
}

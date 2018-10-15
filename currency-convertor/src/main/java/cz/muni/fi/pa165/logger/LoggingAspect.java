/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.logger;

import javax.inject.Named;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author kmini
 */
@Named
@Aspect
public class LoggingAspect {
    
    @Around("execution(public * *(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        System.err.println("Calling method: "
                + joinPoint.getSignature());
        
        long before = System.nanoTime();
        Object result = joinPoint.proceed();
        long after = System.nanoTime();
        
        System.err.println("Method finished: "
                + joinPoint.getSignature() + "Time in seconds:" + (after - before));

        return result;
    }
}

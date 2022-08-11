package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

// @EnableAspectJAutoProxy : there is no need to include this annotation as we have already defined this feature in the xml file
@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
     private Logger myLogger = Logger.getLogger(getClass().getName());

     //setup pointcut declarations
    @Pointcut("execution(* com.controller.*.*(..))") //match on any number of arguments
    private void forControllerPackage(){}

    //do the same of the service and dao packages
    @Pointcut("execution(* com.service.*.*(..))") //any return type
    private void forServicePackage(){}

    //do the same of the service and dao packages
    @Pointcut("execution(* com.dao.*.*(..))")
    private void forDaoPackage(){}

    //combine these pointcuts in one pointcut method
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()") //or operation is done
    private void forAppFlow(){}

     //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        //display the method we are calling
        String methodName = joinPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method: " + methodName);

        //display the arguments of the method called
        Object[] args = joinPoint.getArgs();
        for (Object arg : args){
            myLogger.info("=====> argument: "+arg.toString());
        }
    }

     //add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        //display the method we are returning from
        String methodName = joinPoint.getSignature().toShortString();
        myLogger.info("in @AfterReturning advice: from method: " + methodName);

        //display data returned
        myLogger.info("result is ====> " + result);
    }

}

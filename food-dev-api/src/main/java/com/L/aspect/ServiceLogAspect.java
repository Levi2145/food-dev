package com.L.aspect;

@Aspect
@Component
public class ServiceLogAspect {
    public static final Logger log =
            LoggerFactory.getLogger(ServiceLogAspect.class);
    /*
    * AOP通知:
1.
2.
3.
4.
5.
前置通知:
后置通知:
环绕通知:
异常通知:
在方法调用之前执行
在方法正常调用之后执行
在方法调用之前和之后，都分别可以执行的通知
如果在方法调用过程中发生异常，则通知
最终通知:在方法调用之后执行
    * */

/*
* 切面表达式:
execution代表所要执行的表达式主体
第一处* 代表方法返回类型 *代表所有类型
第二处 包名代表aop监控的类所在的包
第三处
..代表该包以及其子包下的所有类方法
*代表类名，*代表所有类
第四处
第五处*(..)*代表类中的方法名，(..)表示方法中的任何参数
* */
    @Around("execution()")
    public Object recordTimeLog (ProceedingJoinPoint joinPoint){
        log.info("====== 开始执行 {}.{} =====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());)
        //    记录开始时间
//    执行目标 Service (result)
//    记录结束时间
        if takeTime(){

        }
        return result;
    }

}

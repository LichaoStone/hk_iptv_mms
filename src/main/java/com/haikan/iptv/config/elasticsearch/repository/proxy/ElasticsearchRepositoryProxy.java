package com.haikan.iptv.config.elasticsearch.repository.proxy;

import com.haikan.iptv.common.util.SpringContextUtil;
import com.haikan.iptv.config.elasticsearch.repository.CustomSimpleElasticsearchRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public class ElasticsearchRepositoryProxy implements MethodInterceptor {

    private Class<?> targetClz;

    public  Object getInstance(Class<?> targetClz){
        this.targetClz = targetClz;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClz);
        enhancer.setCallback(this);
        return  enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {
      CustomSimpleElasticsearchRepository customSimpleElasticsearchRepository = SpringContextUtil.getBean(CustomSimpleElasticsearchRepository.class);
      customSimpleElasticsearchRepository.setEntityClass(this.getEntityClz());
      String mehodName = method.getName();
      log.info("entityClz:{},methodName:{},args:{}",customSimpleElasticsearchRepository.getEntityClass(),mehodName,args);

      return MethodUtils.invokeMethod(customSimpleElasticsearchRepository,true,mehodName,args);

    }

  /**
   * 获取泛型类，比如USER<T>
   * @return 返回T
   */
  private Class<?>  getEntityClz(){

    Type t = targetClz.getGenericInterfaces()[0];
    if(t instanceof ParameterizedType) {
      Type[] p = ((ParameterizedType) t).getActualTypeArguments();
      return (Class<?>) p[0];// 获取第一个类型参数的真实类型
    }

    return null;
  }
}

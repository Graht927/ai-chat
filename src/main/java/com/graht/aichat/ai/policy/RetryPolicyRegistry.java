package com.graht.aichat.ai.policy;

import com.graht.aichat.ai.retry.RetryPolicyType;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author GRAHT
 */
@Component
public class RetryPolicyRegistry implements BeanPostProcessor {
    private final Map<RetryPolicyType, RetryPolicy> retryPolicies = new EnumMap<>(RetryPolicyType.class);
    /*@Resource
    public void setPolicies (List<RetryPolicy> retryPolicies){
        for (RetryPolicy retryPolicy : retryPolicies) {
            RetryPolicy put = this.retryPolicies.put(retryPolicy.type(), retryPolicy);
            if (put != null) {
                throw new IllegalArgumentException("Duplicate retry policy for type: " + retryPolicy.type());
            }
        }
    }*/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof RetryPolicy retryPolicy) {
            RetryPolicy put = this.retryPolicies.put(retryPolicy.type(), retryPolicy);
            if (put != null) {
                throw new IllegalStateException("Duplicate retry policy for type: " + retryPolicy.type());
            }
        }
        return bean;
    }
    public RetryPolicy getPolicy(RetryPolicyType retryPolicyType){
        RetryPolicy retryPolicy = retryPolicies.get(retryPolicyType);
        if (retryPolicy == null) {
            throw new IllegalArgumentException("No retry policy found for type: " + retryPolicyType);
        }
        return retryPolicy;
    }
}

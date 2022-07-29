package cn.nigel.boot.framework.apilog.config;

import cn.nigel.boot.framework.apilog.core.filter.ApiAccessLogFilter;
import cn.nigel.boot.framework.apilog.core.service.ApiAccessLogFrameworkService;
import cn.nigel.boot.framework.common.enums.WebFilterOrderEnum;
import cn.nigel.boot.framework.web.config.VideoWebAutoConfiguration;
import cn.nigel.boot.framework.web.config.WebProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@AutoConfigureAfter(VideoWebAutoConfiguration.class)
public class VideoApiLogAutoConfiguration {

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
//    @Bean
//    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
//                                                                         @Value("${spring.application.name}") String applicationName,
//                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
//        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
//        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
//    }
//
//    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
//        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
//        bean.setOrder(order);
//        return bean;
//    }

}

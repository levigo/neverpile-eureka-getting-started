package com.neverpile.eureka.myplugin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.neverpile.eureka.myplugin.MockMyCRMImpl;
import com.neverpile.eureka.myplugin.MyCRMFacet;

@Configuration
@ComponentScan(basePackageClasses = {MyCRMFacet.class, MockMyCRMImpl.class})
public class MyPluginAutoConfiguration {
}

package io.egensolutions;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
   // @Qualifier()
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("111111111111");
           return new Class[]{Application.class,ReadingsJPAConfiguration.class, VehicleJPAConfiguration.class };
    }


    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }
}

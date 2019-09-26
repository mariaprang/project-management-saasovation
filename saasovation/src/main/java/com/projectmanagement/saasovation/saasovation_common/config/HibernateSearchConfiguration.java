//package com.projectmanagement.saasovation.saasovation_common.config;
//
//import com.projectmanagement.saasovation.project.application.service.ProjectSearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//@EnableAutoConfiguration
//@Configuration
//public class HibernateSearchConfiguration {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Bean
//    ProjectSearchService hibernateSearchService() {
//        ProjectSearchService hibernateSearchService = new ProjectSearchService(entityManager);
//        hibernateSearchService.initializeHibernateSearch();
//        return hibernateSearchService;
//    }
//}
package com.projectmanagement.saasovation.project.application.service;
import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.team.domain.Member;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PostRemove;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectSearchService {

    @Autowired
    private final EntityManager entityManager;
    @Autowired
    public ProjectSearchService(EntityManager entityManager){
        super();
        this.entityManager = entityManager;
    }

    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager
                    = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Project> fuzzySearchProjects(String searchTerm) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Project.class)
                .get();


        org.apache.lucene.search.Query query = queryBuilder
                .keyword().fuzzy()
                .onFields("projectName", "projectType")
                .matching(searchTerm)
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, Project.class);
        //execute search

        List<Project> projectsMatchinSearchQuery = null;
        try {
            projectsMatchinSearchQuery = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;//do nothing

        }

        return projectsMatchinSearchQuery;


    }


}

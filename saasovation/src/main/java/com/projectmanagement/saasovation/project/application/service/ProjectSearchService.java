package com.projectmanagement.saasovation.project.application.service;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectSearchService {

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectSearchService(EntityManager entityManager, ProjectRepository projectRepository) {
        super();
        this.projectRepository = projectRepository;
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


    private List <Project> handleEmptySearchTerm(String searchTerm) {
        return projectRepository.findAllProjects();
    }

    @Transactional
    public List <Project> fuzzySearchProjects(String searchTerm) {


        if (searchTerm.isEmpty()) {
            return handleEmptySearchTerm(searchTerm);
        } else {

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

            List <Project> projectsMatchinSearchQuery = null;
            try {
                projectsMatchinSearchQuery = jpaQuery.getResultList();
            } catch (NoResultException nre) {
                //TODO: implement the noresult message
            }
            return projectsMatchinSearchQuery;
        }


    }


}

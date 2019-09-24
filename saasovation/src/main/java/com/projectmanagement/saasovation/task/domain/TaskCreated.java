package com.projectmanagement.saasovation.task.domain;

import java.util.Date;

public class TaskCreated /*implements DomainEvent*/ {

    private String description;
    private int eventVersion;
    private String name;
    private Date occurredOn;
   // private ProductId productId;
   // private ProductOwnerId productOwnerId;
    private boolean requestingDiscussion;
  //  private TenantId tenantId;

    public TaskCreated(
//            TenantId aTenantId,
//            ProductId aProductId,
//            ProductOwnerId aProductOwnerId,
            String aName,
            String aDescription,
            boolean aRequestingDiscussion) {

        super();

        this.description = aDescription;
        this.eventVersion = 1;
        this.name = aName;
        this.occurredOn = new Date();
//        this.productId = aProductId;
//        this.productOwnerId = aProductOwnerId;
        this.requestingDiscussion = aRequestingDiscussion;
      //  this.tenantId = aTenantId;
    }

    public String description() {
        return this.description;
    }

//    @Override
//    public int eventVersion() {
//        return this.eventVersion;
//    }

    public String name() {
        return this.name;
    }

//    @Override
//    public Date occurredOn() {
//        return this.occurredOn;
//    }


}

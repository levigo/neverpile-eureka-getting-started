package com.neverpile.eureka.myplugin;

public interface MyCRM {
  public CustomerInfoRecord getCustomerInfo(String documentId);

  boolean isCustomerIdValid(String customerId);

  void associateCustomerDocument(String customerId, String documentId);

  void removeDocumentAssociation(String documentId);
}

package com.neverpile.eureka.myplugin;

import java.security.InvalidParameterException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.neverpile.eureka.model.Document;
import com.neverpile.eureka.rest.api.document.DocumentDto;
import com.neverpile.eureka.rest.api.document.DocumentFacet;

@Component
public class MyCRMFacet implements DocumentFacet<CustomerInfoRecord> {
  @Autowired
  private MyCRM crm;

  @Override
  public String getName() {
    return "myCRM";
  }

  @Override
  public JavaType getValueType(final TypeFactory f) {
    return f.constructType(CustomerInfoRecord.class);
  }

  @Override
  public Set<ConstraintViolation> validateCreate(DocumentDto requestDto) {
    String customerId = getCustomerIdFromDto(requestDto);
    if (!crm.isCustomerIdValid(customerId)) {
      return ConstraintViolation.fail(this, "Invalid customer ID: " + customerId);
    }
    return ConstraintViolation.none();
  }

  @Override
  public void beforeCreate(Document toBeCreated, DocumentDto requestDto) {
    String customerId = getCustomerIdFromDto(requestDto);
    crm.associateCustomerDocument(customerId, toBeCreated.getDocumentId());
  }

  @Override
  public void afterCreate(Document persisted, DocumentDto responseDto) {
    onRetrieve(persisted, responseDto);
  }

  @Override
  public void onRetrieve(final Document document, final DocumentDto dto) {
    dto.setFacet(getName(), crm.getCustomerInfo(document.getDocumentId()));
  }

  @Override
  public void onDelete(Document currentDocument){
    crm.removeDocumentAssociation(currentDocument.getDocumentId());
  }

  private String getCustomerIdFromDto(DocumentDto dto) {
    return dto.getFacetData(this).orElseThrow(
        () -> new InvalidParameterException("DTO must have customer-ID!")).getCustomerId();
  }

  @Override
  public String toString() {
    return "DocumentFacet{" + "name=" + getName() + '}';
  }
}

package com.neverpile.eureka.myplugin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MockMyCRMImpl implements MyCRM {

  Map<String, CustomerInfoRecord> mockCustomerInfo;
  Map<String, String> mockDocumentInfo;

  public MockMyCRMImpl() {
    this.mockCustomerInfo = new HashMap<>();
    mockCustomerInfo.put("0", generateCustomerInfo("0"));
    mockCustomerInfo.put("1", generateCustomerInfo("1"));
    this.mockDocumentInfo = new HashMap<>();
  }

  @Override
  public CustomerInfoRecord getCustomerInfo(String documentId) {
    return mockCustomerInfo.get(mockDocumentInfo.get(documentId));
  }

  @Override
  public boolean isCustomerIdValid(String customerId) {
    return mockCustomerInfo.containsKey(customerId);
  }

  @Override
  public void associateCustomerDocument(String customerId, String documentId) {
    mockDocumentInfo.put(documentId, customerId);
  }

  @Override
  public void removeDocumentAssociation(String documentId) {
    mockDocumentInfo.remove(documentId);
  }

  private CustomerInfoRecord generateCustomerInfo(String id) {
    CustomerInfoRecord record = new CustomerInfoRecord();
    record.setCustomerId(id);
    record.setAddress(id.equals("0") ? "Bebelsbergstraße 31 - 71088 Holzgerlingen" : "Haupstraße 1 - 10827 Berlin");
    record.setFirstName(id.equals("0") ? "Luigi" : "Max");
    record.setLastName(id.equals("0") ? "Levigo" : "Mustermann");
    return record;
  }
}

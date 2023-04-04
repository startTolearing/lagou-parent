package com.example;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AutoDeliverApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoDeliverApplicationTest {

  @Autowired
  private DiscoveryClient discoveryClient;

  public void testMetaData() {

  }

}

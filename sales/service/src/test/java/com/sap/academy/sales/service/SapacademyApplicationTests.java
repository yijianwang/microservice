package com.sap.academy.sales.service;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"VCAP_SERVICES = { \"managed-hana\": [ { \"binding_name\": null, \"credentials\": { \"user\": \"2YoNkVt44fZIpI7SvBFM_Q5ornMPtD1y\", \"password\": \"EHabIo_15iY0G1kf8F3B7nNv0Ai62khJ\", \"post_managed_instance_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704/{tenant_id}\", \"delete_managed_instance_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704/{tenant_id}\", \"get_managed_instance_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704/{tenant_id}\", \"get_all_managed_instances_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704\" }, \"instance_name\": \"com.sap.academy.sales\", \"label\": \"managed-hana\", \"name\": \"com.sap.academy.sales\", \"plan\": \"hdi-shared\", \"provider\": null, \"syslog_drain_url\": null, \"tags\": [ \"managed-hana\", \"managed-database\", \"managed-relational\", \"xsa-instancemanager\" ], \"volume_mounts\": [] } ] }"})
public class SapacademyApplicationTests {

	static {
		System.setProperty("VCAP_SERVICES", "{ \"managed-hana\": [ { \"binding_name\": null, \"credentials\": { \"user\": \"2YoNkVt44fZIpI7SvBFM_Q5ornMPtD1y\", \"password\": \"EHabIo_15iY0G1kf8F3B7nNv0Ai62khJ\", \"post_managed_instance_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704/{tenant_id}\", \"delete_managed_instance_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704/{tenant_id}\", \"get_managed_instance_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704/{tenant_id}\", \"get_all_managed_instances_url\": \"https://instance-manager.cfapps.eu10.hana.ondemand.com/managed/managed_instances/f1128515-0c22-467a-ae59-e4e6ec0a3704\" }, \"instance_name\": \"com.sap.academy.sales\", \"label\": \"managed-hana\", \"name\": \"com.sap.academy.sales\", \"plan\": \"hdi-shared\", \"provider\": null, \"syslog_drain_url\": null, \"tags\": [ \"managed-hana\", \"managed-database\", \"managed-relational\", \"xsa-instancemanager\" ], \"volume_mounts\": [] } ] }");
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRestEndPoint() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+8080+"/salesorder/order";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(baseUrl,String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

}

package com.returnorder.returnorderportal.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.returnorder.returnorderportal.model.ProcessResponse;


class ProcessResponseTest {

	ProcessResponse processResponse = new ProcessResponse();

	@Test
	void testGetRequestId() {
		processResponse.setRequestId("REQ1ASID143");
		assertEquals("REQ1ASID143", processResponse.getRequestId());
	}

	@Test
	void testGetProcessingCharge() {
		processResponse.setProcessingCharge(300L);
		assertEquals(300L, processResponse.getProcessingCharge());
	}

	@Test
	void testGetPackagingAndDeliveryCharge() {
		processResponse.setPackagingAndDeliveryCharge(1000L);
		assertEquals(1000L, processResponse.getPackagingAndDeliveryCharge());
	}

	@Test
	void testGetDateOfDelivery() {
		processResponse.setDateOfDelivery("09/04/2021");
		assertEquals("09/04/2021", processResponse.getDateOfDelivery());
	}

	@Test
	void testGetUserId() {
		processResponse.setUserId("UID04D");
		assertEquals("UID04D", 	processResponse.getUserId());
	}

	@Test
	void testGetTotal() {
		processResponse.setTotal(1300L);
		assertEquals(1300L, processResponse.getTotal());
	}

	@Test
	void testSetRequestId() {
		processResponse.setRequestId("REQ1ASID143");
		assertEquals("REQ1ASID143", processResponse.getRequestId());
	}

	@Test
	void testSetProcessingCharge() {
		processResponse.setProcessingCharge(300L);
		assertEquals(300L, processResponse.getProcessingCharge());
	}

	@Test
	void testSetPackagingAndDeliveryCharge() {
		processResponse.setPackagingAndDeliveryCharge(1000L);
		assertEquals(1000L, processResponse.getPackagingAndDeliveryCharge());
	}

	@Test
	void testSetDateOfDelivery() {
		processResponse.setDateOfDelivery("09/04/2021");
		assertEquals("09/04/2021", processResponse.getDateOfDelivery());
	}

	@Test
	void testSetUserId() {
		processResponse.setUserId("UID04D");
		assertEquals("UID04D", 	processResponse.getUserId());
	}

	@Test
	void testSetTotal() {
		processResponse.setTotal(1300L);
		assertEquals(1300L, processResponse.getTotal());	}

	@Test
	void testToString() {
		ProcessResponse process = new ProcessResponse();
		String expectedToString = "ProcessResponse(requestId=null, processingCharge=0, packagingAndDeliveryCharge=0, total=0, dateOfDelivery=null, userId=null)";
		assertEquals(expectedToString, process.toString());
	}

	@Test
	void testProcessResponse() {
		assertNotNull(processResponse);
	}

	@Test
	void testProcessResponseStringLongLongStringStringLong() {
		ProcessResponse processResponse = new ProcessResponse("REQ1ASID143", 300L, 1000L, 1300L, "09/04/2021", "UID04D");
		assertEquals("REQ1ASID143", processResponse.getRequestId());
		assertEquals(300L, processResponse.getProcessingCharge());
		assertEquals(1000L, processResponse.getPackagingAndDeliveryCharge());
		assertEquals("09/04/2021", processResponse.getDateOfDelivery());
		assertEquals("UID04D", processResponse.getUserId());
		assertEquals(1300L, processResponse.getTotal());
	}

}

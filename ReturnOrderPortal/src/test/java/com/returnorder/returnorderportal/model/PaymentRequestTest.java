package com.returnorder.returnorderportal.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.returnorder.returnorderportal.model.PaymentRequest;

class PaymentRequestTest {

	PaymentRequest paymentRequest = new PaymentRequest();
	
	@Test
	void testGetCreditCardNumber() {
		paymentRequest .setCreditCardNumber("123478451245");
		assertEquals("123478451245", paymentRequest .getCreditCardNumber());
	}

	@Test
	void testGetRequestId() {
		paymentRequest .setRequestId("REQ1ASID143");
		assertEquals("REQ1ASID143", paymentRequest .getRequestId());
	}

	@Test
	void testGetProcessingCharge() {
		paymentRequest .setProcessingCharge(300L);
		assertEquals(300L, paymentRequest .getProcessingCharge());
	}

	@Test
	void testSetCreditCardNumber() {
		paymentRequest .setCreditCardNumber("123478451245");
		assertEquals("123478451245", paymentRequest .getCreditCardNumber());
	}

	@Test
	void testSetRequestId() {
		paymentRequest .setRequestId("REQ1ASID143");
		assertEquals("REQ1ASID143", paymentRequest .getRequestId());
	}

	@Test
	void testSetProcessingCharge() {
		paymentRequest .setProcessingCharge(300L);
		assertEquals(300L, paymentRequest .getProcessingCharge());
	}

	@Test
	void testToString() {
		PaymentRequest pay = new PaymentRequest("123478451245", "REQ1ASID143", 300L);
		String expected = "PaymentRequest(creditCardNumber=123478451245, requestId=REQ1ASID143, processingCharge=300)";
		assertEquals(expected, pay.toString());
	}

	@Test
	void testPaymentRequest() {
		assertNotNull(paymentRequest);
	}

	@Test
	void testPaymentRequestStringStringLong() {
		PaymentRequest pay = new PaymentRequest("123478451245", "REQ1ASID143", 300L);
		assertEquals("123478451245",pay.getCreditCardNumber());
		assertEquals("REQ1ASID143",pay.getRequestId());
		assertEquals(300L,pay.getProcessingCharge());
	}

}

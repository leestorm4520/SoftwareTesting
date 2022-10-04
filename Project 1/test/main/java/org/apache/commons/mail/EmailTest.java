package org.apache.commons.mail;

import java.util.Properties;
import java.util.Date;

import javax.mail.Session;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import junit.framework.TestCase;

/* Name: John Le
 * tgy670
 * CS4723 - Project 1
 * 
 * Test Methods for Email Class - Testing 12 methods assigned
 */
public class EmailTest extends TestCase{
	EmailMock testEmail;
	
	/* initialize */
	public void setUp() {
		testEmail = new EmailMock();
	}
	
	/////////////////////////////////////////////////
	
	/* addBcc - coverage: 100% */
	
	/* Email addBcc(String email) */
	public void testAddBcc() throws EmailException {
		testEmail.addBcc("a@b.com");
		assertEquals("a@b.com", testEmail.getBccAddresses().get(0).toString());
	}
	
	/* Email addBcc(String email, String charset) */
	public void testAddBccWithCharSet() throws EmailException{
		testEmail.addBcc("a@b.com", "UTF-16");
		assertEquals("UTF-16 <a@b.com>", testEmail.getBccAddresses().get(0).toString());
	}
	
	/* Email addBcc(String ... emails) - An array of emails */
	public void testAddBccWithArrayOfEmails() throws EmailException{
		String[] emails= new String[] {"a@b.com", "c@b.com", "d@b.com"}; 
		testEmail.addBcc(emails);
		assertEquals("d@b.com", testEmail.getBccAddresses().get(2).toString());
	}
     
	/* Email addBcc(String ... emails) - An array of emails */
	public void testAddBccWithNullEmail() throws EmailException{
		try {
			String[] emails=null; 
			testEmail.addBcc(emails); fail("Should throw EmailException");
		}
		catch (EmailException e){
			assertEquals("Address List provided was invalid", e.getMessage());
		}
	}
	/////////////////////////////////////////////////
	
	/* addCc - coverage: 100% */
	
	/* Email addCc(String email) */
	public void testAddCC() throws EmailException{
		testEmail.addCc("a@b.com");
		assertEquals("a@b.com", testEmail.getCcAddresses().get(0).toString());
	}
	
	/* Email addCc(String email, String name, String charset) */
	public void testAddCCWithCharSet() throws EmailException{
		testEmail.addCc("a@b.com", "John", "UTF-16");
		assertEquals("John <a@b.com>", testEmail.getCcAddresses().get(0).toString());
	}
	
	/////////////////////////////////////////////////
	
	/* addHeader - coverage: 100% */
	
	/*
	 * void addHeader(String name, String value)
	 * 	name: sender's name
	 * 	value: level of priority
	 *    1( highest )or  2( high ) 3( normal ) 4( low ) and 5( lowest )
	 */
	public void testAddHeaderWithBothArguments() throws EmailException{
		testEmail.addHeader("John", "1");
		assertEquals("{John=1}", testEmail.headers.toString());
	}
	
	/* void addHeader(String name, String value) */
	public void testAddHeaderWithNoName() throws EmailException{
		try {
		testEmail.addHeader("", "2"); fail("Should throw IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("name can not be null or empty", e.getMessage());
		}
	}
	
	/* void addHeader(String name, String value) */
	public void testAddHeaderWithNoValue() throws EmailException{
		try {
			testEmail.addHeader("John", ""); fail("Should throw IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("value can not be null or empty", e.getMessage());
		}
	}
	
	/////////////////////////////////////////////////

	/* addReplyTo - coverage: 100% */
	
	/* Email addReplyTo(String email) */
	public void testAddReplyTo() throws EmailException{
		testEmail.addReplyTo("a@b.com");
		assertEquals("a@b.com", testEmail.getReplyToAddresses().get(0).toString());
	}
	
	/* Email addReplyTo(String email, String name) */
	public void testAddReplyWithName() throws EmailException{
		testEmail.addReplyTo("a@b.com", "John");
		assertEquals("John <a@b.com>", testEmail.getReplyToAddresses().get(0).toString());
	}
	
	/* Email addReplyTo(String email, String name, String charset) */
	public void testAddReplyWithCharSet() throws EmailException{
		testEmail.addReplyTo("a@b.com", "John", "UTF-16");
		assertEquals("John <a@b.com>", testEmail.getReplyToAddresses().get(0).toString());
	}
	
	/////////////////////////////////////////////////

	/*
	 * buildMimeMessage - coverage: 73.8%
	 * -A MimeMessage includes: host, from, toList, ccList, bccList, replyList, headers, subject, content, emailbody, 
	 */
	
	/* void buildMineMessage() */
	public void testBuildMimeMessageWithAllComponents() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("a subject");
		testEmail.updateContentType("test/plain; charset=utf-8");
		
		testEmail.addCc("d@b.com");
		testEmail.addBcc("e@b.com");
		testEmail.addReplyTo("f@b.com");
		testEmail.addHeader("Testing case", "1");
		testEmail.buildMimeMessage();
	}
	
	/* void buildMineMessage() */
	public void testBuildMimeMessageNoHost() throws EmailException{
		try {
			testEmail.buildMimeMessage(); fail("Should throw EmailException");
		} 
		catch (EmailException e){
			assertEquals("Cannot find valid hostname for mail session", e.getMessage());
		}
	}
	
	/* void buildMineMessage() */
	public void testBuildMimeMessageNoFrom() throws EmailException{
		testEmail.setHostName("b.com");
		try {
			testEmail.buildMimeMessage(); fail("Should throw EmailException!");
		}
		catch(EmailException e) {
			assertEquals("From address required", e.getMessage());
		}
	}
	
	/* void buildMineMessage() */
	public void testBuildMimeMessageNoTo() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		try {
			testEmail.buildMimeMessage(); fail("Should throw EmailException!");
		}
		catch(EmailException e) {
			assertEquals("At least one receiver address required", e.getMessage());
		}
	}
	
	/* void buildMineMessage() */
	public void testBuildMimeMessageExisting() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.buildMimeMessage();
		try {
			testEmail.buildMimeMessage(); fail("Should throw illegal state exception");
		}
		catch(IllegalStateException e) {
			assertEquals("The MimeMessage is already built.", e.getMessage());
		}
	}
	
	public void testBuildMimeMessageWithMessage() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("a subject");
		testEmail.updateContentType("test/plain; charset=utf-8");
		
		testEmail.addCc("d@b.com");
		testEmail.addBcc("e@b.com");
		testEmail.addReplyTo("f@b.com");
		testEmail.addHeader("Testing case", "1");
		testEmail.setContent(new Object(), "text/plain");
		testEmail.buildMimeMessage();

	}
	
	/////////////////////////////////////////////////

	/* getHostName - coverage: 88.2% */
	
	/* String getHostName() */
	public void testGetHostName() throws EmailException{
		testEmail.setHostName("b.com");
		assertEquals("b.com", testEmail.getHostName().toString());
	}
	
	/* String getHostName() */
	public void testGetHostNameWithSession() throws EmailException{
		testEmail.setHostName("b.com");
		Session session=testEmail.getMailSession();
		assertEquals("b.com", testEmail.getHostName().toString());
	}
	
	/////////////////////////////////////////////////
	
	/* getMailSession - coverage: 86.3% */
	
	/* Session getMailSession() */
	public void testGetMailSessionWithSSL() throws EmailException{
		testEmail.setSSLOnConnect(true);
		testEmail.setHostName("Host A");
		Session session=testEmail.getMailSession();
	}
	
	/////////////////////////////////////////////////

	/* getSentDate - coverage: 100% */
	
	/* Date getSentDate() */
	public void testGetSentDate() throws EmailException{
		Date dateSent=new Date();
		testEmail.setSentDate(dateSent);
		assertEquals(dateSent.toString(), testEmail.getSentDate().toString());
	}
	
	/////////////////////////////////////////////////

	/* getSocketConnectionTimeout - coverage: 100% */
	
	/* int getSocketConnectionTimeout() */
	public void testGetSocketConnectionTimeout() throws EmailException{
		testEmail.setSocketConnectionTimeout(60);
		assertEquals(60, testEmail.getSocketConnectionTimeout());
	}
	
	/////////////////////////////////////////////////

	/* send - coverage: 100% */
	
	/* String send() */
	public void testSend() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("a subject");
		testEmail.send();
		assertEquals("a subject", testEmail.getSent());
	}
	
	/////////////////////////////////////////////////

	/* setFrom - coverage: 100% */
	
	/* Email setFrom(String email) */
	//setFrom was already tested 100% in testSend() but redo it for test purpose
	public void testSetFrom() throws EmailException{
		testEmail.setFrom("a@b.com");
		assertEquals("a@b.com", testEmail.getFromAddress().toString());
	}
		
	/////////////////////////////////////////////////

	/* updateContentType - 90.8% */
	
	/* void updateContentType(String aContentType) */
	public void testUpdateContentNull() throws EmailException{
		testEmail.updateContentType("");
		assertEquals(null, testEmail.contentType);
	}
	
	/* void updateContentType(String aContentType) */
	public void testUpdateContentTypeWithCharSet() throws EmailException{
		testEmail.updateContentType("text/plain; charset=utf-8");
		assertEquals("utf-8", testEmail.getCharset());
	}
	
	/* void updateContentType(String aContentType) */
	public void testUpdateContentTypeWithoutCharSet() throws EmailException{
		testEmail.setCharset("utf-8");
		testEmail.updateContentType("text/plain");
		assertEquals("text/plain; charset=UTF-8", testEmail.contentType);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

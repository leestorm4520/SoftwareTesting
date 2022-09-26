package org.apache.commons.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import junit.framework.TestCase;

public class EmailTest extends TestCase{
	EmailMock testEmail;
	public void setUp() {
		testEmail = new EmailMock();
	}
	public void testAddBcc() throws EmailException {
		testEmail.addBcc("a@b.com");
		assertEquals("a@b.com", testEmail.getBccAddresses().get(0).toString());
	}

	/*
	 * getBccAddress return bccList (ArrayList of InternetAddress) - for addBcc(String... email){}
	 * buildMimeMessage
	 * getBccAddresses/getCCAddresses ->bbcList/ccList -> createInternetAddress
	 * ->InternetAddress(email).setPersonal(name) or InternetAddress(email).setPersonal(name, Charset.forname(charsetName))
	 *
	 */
	public void testAddBccWithCharSet() throws EmailException{
		testEmail.addBcc("a@b.com", "UTF-16");
		assertEquals("UTF-16 <a@b.com>", testEmail.getBccAddresses().get(0).toString());
	}

	public void testAddCC() throws EmailException{
		testEmail.addCc("a@b.com");
		assertEquals("a@b.com", testEmail.getCcAddresses().get(0).toString());
	}
	public void testAddCCWithCharSet() throws EmailException{
		testEmail.addCc("a@b.com", "John", "UTF-16");
		assertEquals("John <a@b.com>", testEmail.getCcAddresses().get(0).toString());
	}
	/*
	 * X-Mailer: Sendmail, X-Priority: 1( highest )
     * or  2( high ) 3( normal ) 4( low ) and 5( lowest )
     * Disposition-Notification-To: user@domain.net
     * String Name - String Value
	 */
	public void testAddHeader() throws EmailException{
		testEmail.addHeader("John", "1");
		assertEquals("{John=1}", testEmail.headers.toString());
	}
	public void testAddReplyTo() throws EmailException{
		testEmail.addReplyTo("a@b.com");
		assertEquals("a@b.com", testEmail.getReplyToAddresses().get(0).toString());
	}
	public void testAddReplyWithCharSet() throws EmailException{
		testEmail.addReplyTo("a@b.com", "John", "UTF-16");
		assertEquals("John <a@b.com>", testEmail.getReplyToAddresses().get(0).toString());
	}
	
	public void testUpdateContentNull() throws EmailException{
		testEmail.updateContentType("");
		assertEquals(null, testEmail.contentType);
	}
	public void testUpdateContentTypeWithCharSet() throws EmailException{
		testEmail.updateContentType("text/plain; charset=utf-8");
		assertEquals("utf-8", testEmail.getCharset());
	}
	
	public void testUpdateContentTypeWithoutCharSet() throws EmailException{
		testEmail.updateContentType("text/plain");
		assertEquals("text/plain", testEmail.contentType);
	}
	
	public void testSend() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("a subject");
		testEmail.send();
		assertEquals("a subject", testEmail.getSent());
	}
	
	public void testBuildMimeMessageNoHost() throws EmailException{
		try {
			testEmail.buildMimeMessage(); fail("Should throw EmailException");
		} 
		catch (EmailException e){
			assertEquals("Cannot find valid hostname for mail session", e.getMessage());
		}
	}
	
	public void testBuildMimeMessageNoFrom() throws EmailException{
		testEmail.setHostName("b.com");
		try {
			testEmail.buildMimeMessage(); fail("Should throw EmailException!");
		}
		catch(EmailException e) {
			assertEquals("From address required", e.getMessage());
		}
	}
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

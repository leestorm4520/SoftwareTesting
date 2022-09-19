package org.apache.commons.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import junit.framework.TestCase;

public class EmailTest extends TestCase{
	Email testEmail;
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
	 *
	 */
	public void testAddBccWithCharSet() throws EmailException{
		testEmail.addBcc("a@b.com", "UTF-16");
		assertEquals("UTF-16 <a@b.com>", testEmail.getBccAddresses().get(0).toString());
	}

	public void testAddCC() throws EmailException{
		testEmail.addCc()
	}
}

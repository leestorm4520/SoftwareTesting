package org.apache.commons.mail;

//send email message without attachments
public class EmailMock extends Email{
	
	@Override
	public Email setMsg(String msg) throws EmailException{
		if(EmailUtils.isEmpty(msg)) {
			throw new EmailException("Invalid message received");
		}
		setContent(msg, EmailConstants.TEXT_PLAIN);
		return this;
	}
	
}

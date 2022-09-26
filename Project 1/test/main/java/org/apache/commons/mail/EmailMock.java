package org.apache.commons.mail;

//send email message without attachments
public class EmailMock extends Email{
	private String sent;
	
	
	@Override
	public Email setMsg(String msg) throws EmailException{
		if(EmailUtils.isEmpty(msg)) {
			throw new EmailException("Invalid message received");
		}
		setContent(msg, EmailConstants.TEXT_PLAIN);
		return this;
	}
	
	public String getSent() {
		return sent;
	}
	
	public String sendMimeMessage() throws EmailException{
		EmailUtils.notNull(this.message, "MimeMessage has not been created yet");
		try {
			this.sent=this.message.getSubject();
			return this.message.getMessageID();
		}
		catch(Throwable t) {
			String msg="Sending the email to the following server failed : " + this.getHostName() + ":"+ this.getSmtpPort();
			throw new EmailException(msg, t);
		}
	}
	
	public String getCharset() {
		return charset;
	}
	
	
	
}

package service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import dto.MailSetting;
 
public class MailTransfer {
	
	private static String CharCode = "ISO-2022-JP";
	private static String DefPort = "25";
	private static String TAuthFlg = "true";
	private static String FAuthFlg = "false";
	private static String StrEmpty = "";
	
	//���M��A�h���X
	private String toMailAddress;
	public void setToMailAddress(String iAddress){
		toMailAddress = iAddress;
	}
	public String getToMailAddress() {
		return toMailAddress;
	}
	
	//���M���A�h���X
	private String fromMailAddress;
	public void setFromMailAddress(String iFromMailAddress){
		fromMailAddress = iFromMailAddress;
	}
	public String getFromMailAddress() {
		return fromMailAddress;
	}
	
	//���M�Җ�
	private String fromName;
	public void setFromName(String iFromName){
		fromName = iFromName;
	}
	public String getFromName() {
		return fromName;
	}
	
	//SMTP�z�X�g��
	private String hostName;
	public void setHostName(String iHostName){
		hostName = iHostName;
	}
	public String getHostName() {
		return hostName;
	}

	//�|�[�g�ԍ�
	private String PortNumber;
	public void setPortNumber(String iPortNumber){
		PortNumber = iPortNumber;
	}
	public String getPortNumber() {
		return PortNumber;
	}
	
	//���[�U�[ID
	private String id;
	public void setId(String iId){
		id = iId;
	}
	public String getId() {
		return id;
	}
	
	//�p�X���[�h
	private String pas;
	public void setPas(String iPas){
		pas = iPas;
	}
	public String getPas() {
		return pas;
	}
	
	//�F�؃t���O
	private String authFlg;
	public void setAuthFlg(boolean iAuthFlg){
		if (iAuthFlg){
			authFlg = TAuthFlg;
		} else {
			authFlg = FAuthFlg;
		}
		
	}
	public boolean getAuthFlg() {
		if (TAuthFlg.equals(authFlg)) {
			return true;
		} else {
			return false;
		}
	}
	
	//SSL�ʐM�t���O
	private boolean sslFlg;
	public void setsslFlg(boolean iSslFlg){
		sslFlg = iSslFlg;
	}
	public boolean getSslFlg() {
		return sslFlg;
	}
	
	//���[���^�C�g��
	private String subject;
	public void setSubject(String iSubject){
		subject = iSubject;
	}
	public String getSubject() {
		return subject;
	}
	
	//���[���{��
	private String messege;
	public void setMessege(String iMessege){
		messege = iMessege;
	}
	public String getMessege() {
		return messege;
	}
	
	//�Y�t�t�@�C���p�X
	private String AttachmentPath;
	public void setAttachmentPath(String iAttachmentPath){
		AttachmentPath = iAttachmentPath;
	}
	public String getAttachmentPath(){
		return AttachmentPath;
	}
	
	public MailTransfer() {
		
		hostName = StrEmpty;
		toMailAddress = StrEmpty;
		fromMailAddress = StrEmpty;
		id = StrEmpty;
		pas = StrEmpty;
		fromName = StrEmpty;
		PortNumber = "25";
		authFlg = FAuthFlg;
		sslFlg = false;
		AttachmentPath = StrEmpty;
	}
	
	public MailTransfer(MailSetting iSetting) {
		
		hostName = iSetting.getHostName();
		toMailAddress = iSetting.getToMailAddress();
		fromMailAddress = iSetting.getFromMailAddress();
		fromName = iSetting.getFromName();
		
		id = iSetting.getId();
		pas = iSetting.getPas();
		
		PortNumber = iSetting.getPortNumber();
		
		if (iSetting.getAuthFlg()) {
			authFlg = TAuthFlg;
		} else {
			authFlg = FAuthFlg;
		}
		
		sslFlg = iSetting.getSSlFlg();
		
		subject = iSetting.getSubject();
		messege = iSetting.getMessege();
		
		AttachmentPath = iSetting.getAttachmentPath();
		
	}
		
	//���[�����M���\�b�h
	public void sendMail() {
		
		if (StrEmpty.equals(hostName)) {
			return;
		}
		
		if (StrEmpty.equals(toMailAddress)){
			return;
		}
		
		  
		Properties objPrp = new Properties();
	    
	    objPrp.put("mail.smtp.host", hostName);	// SMTP�T�[�o��
	    objPrp.put("mail.host", hostName);		// �ڑ�����z�X�g��
	    objPrp.put("mail.smtp.auth", authFlg );			// �����F�p�R�[�h�@

        // SSL�֘A�ݒ�
	    if (sslFlg) {
		    objPrp.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    objPrp.setProperty("mail.smtp.socketFactory.fallback", "false");
		    // �|�[�g�ݒ�
		    if (!DefPort.equals(PortNumber)){
		    	objPrp.setProperty("mail.smtp.socketFactory.port", PortNumber);
		    }
	    } else {
	    	
		    // �|�[�g�ݒ�
		    if (!DefPort.equals(PortNumber)){
		    	objPrp.setProperty("mail.smtp.port", PortNumber);
		    }
	    }
	    
	    Session session;
	    
	    // ���[���Z�b�V�������m�� �F�؂��K�v�ȏꍇ�AID�ƃp�X���[�h��ݒ�
	    if (TAuthFlg.equals(authFlg)) {
	    	session = Session.getInstance( objPrp, new MyAuth(id, pas) );
	    } else {
	    	session = Session.getInstance( objPrp, null );  
	    }
	    
	    session.setDebug( true );  // �f�o�b�O���[�h�ɂ���
	    
	    // ���M���b�Z�[�W�𐶐�
	    MimeMessage objMsg = new MimeMessage(session);	    
	    
	    try {
	    	
			// ���M��iTO�̂ق��ACC��BCC���ݒ�\�j
			objMsg.setRecipients(Message.RecipientType.TO, toMailAddress);
			
			// From�w�b�_
			InternetAddress objFrm=new InternetAddress(fromMailAddress, fromName);
			
			objMsg.setFrom(objFrm);
			
			// ����
			objMsg.setSubject(subject, CharCode);
			
			/* �{�� */
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(messege, CharCode);

		    Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    
		    /* �Y�t�t�@�C���Z�b�g�@*/
		    if (AttachmentPath != null && !AttachmentPath.equals(StrEmpty)) {
		    	
		    	File file = new File(AttachmentPath);
		    	if (file.exists()){
		    		
			        MimeBodyPart mbp2 = new MimeBodyPart();
			        FileDataSource fds = new FileDataSource(AttachmentPath);
			        mbp2.setDataHandler(new DataHandler(fds));
			        mbp2.setFileName(MimeUtility.encodeWord(fds.getName()));

			        mp.addBodyPart(mbp2);
		    	}
		        
		    }
		    
		    objMsg.setContent(mp);
					  
			// ���[�����M
			Transport.send(objMsg); 
			
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	      
	    } catch (MessagingException e) {
	    	e.printStackTrace();

	    } 

	}
	
	private class MyAuth extends Authenticator {
		
		private String id;
		private String Pas;
		
		public MyAuth(String iId, String iPas){
			
			id  = iId;
			Pas = iPas;
		}
		
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication( id, Pas );
		}
	} 

} 


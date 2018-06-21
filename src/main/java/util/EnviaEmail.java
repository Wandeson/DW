package util;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EnviaEmail {

	private final JavaMailSender mailSender;
	private final JavaMailSenderImpl emailConfig;
	private final Properties mailProps;

	private static final String EMAIL = "dwcryptosoftware@gmail.com";
	private static final String SENHA = "desenvolvimento";

	public EnviaEmail() {

		this.emailConfig = new JavaMailSenderImpl();
		emailConfig.setHost("smtp.gmail.com"); // smtp do google
		emailConfig.setPort(587);// porta do servidor, vai aprender em Redes2

		mailProps = new Properties(); // configuração do .properties de forma direta.
		mailProps.put("mail.smtps.auth", "true"); // parametros properties
		mailProps.put("mail.smtp.starttls.enable", "true");// parametros properties
		mailProps.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		emailConfig.setJavaMailProperties(mailProps);// atribui ele ao emailConfig.

		emailConfig.setUsername(EMAIL);
		emailConfig.setPassword(SENHA);
		this.mailSender = emailConfig; // recebe todas as configurações.
	}

	public void enviaEmail(String emailTo, String emailAssunto, String emailMensagem) throws Exception{

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailTo); // remetente
		message.setFrom(EMAIL);// destinatario
		message.setSubject(emailAssunto); // assunto
		message.setText(emailMensagem); // mensagem
		mailSender.send(message); // usa o mailSender pra enviar a msg.

	}
}

package services;

import java.io.Serializable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import dao.UsuarioDAO;
import model.Usuario;
import util.EnviaEmail;

public class UsuarioServices implements Serializable {

	private static final long serialVersionUID = 4804413120212098968L;
	
	private static final String ASSUNTO = "Confirmação de Email - DW-Crypto";

	private static final String MENSAGEM = "Digite o seu código de verificação na caixa de texto Código de Verificação de Email na aba de Atualização de cadastro do sistema. Seu Código de Verificação é: ";
	
	private EnviaEmail sender;
	private Random gerador;
	
	@Inject
	private UsuarioDAO usuarioDAO;

	public void save(Usuario usuario) throws Exception {
		usuarioDAO.save(usuario);
	}

	public void delete(Usuario usuario) throws Exception {
		usuarioDAO.delete(usuario);
	}

	public void alterarCadastro(Usuario usuario, String email, String senha, String repitaSenha) throws Exception {
		// Confere email
		if (StringUtils.isNotBlank(email)) {
			if (emailValido(email)) {
				if (!(usuario.getEmail().equals(email))) {
					if (findEmailJaUsado(email)) {
						throw new Exception("Email já usado!");
					} else {
						usuario.setEmail(email);
					}
				}
			} else {
				throw new Exception("Email inválido!");
			}
		}
		// Confere senha
		if (StringUtils.isNotBlank(senha)) {
			if (StringUtils.isNotBlank(repitaSenha)) {
				if (senha.equals(repitaSenha)) {
					usuario.setSenha(senha);
				} else {
					throw new Exception("Senhas não coincidem!");
				}
			} else {
				throw new Exception("Digite a confirmação de senha!");
			}
		} else {
			if (StringUtils.isNotBlank(repitaSenha))
				throw new Exception("Digite a senha!");
		}
		// Salva usuario
		save(usuario);

	}

	public void cadastro(Usuario usuario, String login, String email, String senha, String repitaSenha) throws Exception {
		if (StringUtils.isNotBlank(login)) {
			if (!findLoginJaUsado(login)) {
				usuario.setLogin(login);
			} else {
				throw new Exception("Login já usado!");
			}
		}
		if (StringUtils.isNotBlank(email)) {
			if (emailValido(email)) {
				if (findEmailJaUsado(email)) {
					throw new Exception("Email já usado!");
				} else {
					usuario.setEmail(email);
				}
			} else {
				throw new Exception("Email inválido!");
			}
		} else {
			throw new Exception("Email inválido!");
		}
		// Confere senha
		if (StringUtils.isNotBlank(senha)) {
			if (StringUtils.isNotBlank(repitaSenha)) {
				if (senha.equals(repitaSenha)) {
					usuario.setSenha(senha);
				} else {
					throw new Exception("Senhas não coincidem!");
				}
			} else {
				throw new Exception("Digite a confirmação de senha!");
			}
		} else {
			throw new Exception("Digite a senha!");
		}
		// Salva usuario
		gerador = new Random();
		int codigoVerificacao = gerador.nextInt(999999);
		usuario.setCodigoVerificacao(codigoVerificacao);
		usuario.setContaVerificada(false);
		save(usuario);
		// enviar email
		new Thread(){
			public void run(){
				sender = new EnviaEmail();
				sendEmail(usuario, ASSUNTO, MENSAGEM + usuario.getCodigoVerificacao());
			}
		}.start();
		
	}

	public Boolean emailValido(String email) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		return matchFound;
	}

	public Usuario findUsuario(Usuario usuario) throws Exception {
		if (StringUtils.isBlank(usuario.getLogin()))
			throw new Exception("O nome de usuário é obrigatório.");
		if (StringUtils.isBlank(usuario.getSenha()))
			throw new Exception("O campo de senha é obrigatório.");

		usuario = usuarioDAO.findUsuario(usuario.getLogin(), usuario.getSenha());

		if (usuario == null)
			throw new Exception("O nome de usuário ou senha incorretos.");
		else
			return usuario;
	}

	public void verificarEmail(Usuario usuario, String codigoVerificacao) throws Exception {
		if (!usuario.getContaVerificada()) {
			if(usuario.getCodigoVerificacao().toString().equals(codigoVerificacao)) {
				usuario.setContaVerificada(true);
			}else {
				throw new Exception("Código inválido.");
			}
		}else {
			throw new Exception("Email já confirmado");
		}
		save(usuario);

	}
	
	public void sendEmail(Usuario usuario, String assunto, String mensagem) {
		try {
			sender.enviaEmail(usuario.getEmail(), assunto, mensagem);
		} catch (Exception e) {
			
		}
	}

	public boolean findLoginJaUsado(String login) {
		return usuarioDAO.findLoginJaUsado(login);
	}

	public boolean findEmailJaUsado(String email) {
		return usuarioDAO.findEmailJaUsado(email);
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}

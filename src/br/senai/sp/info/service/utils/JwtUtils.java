package br.senai.sp.info.service.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.senai.sp.info.service.models.Usuario;

public class JwtUtils {

	public static final String TOKEN_AUTH_ASSINANTE = "ServiceDesk";
	public static final String TOKEN_AUTH_CHAVE_PRIVADA = "KASKLJjklasdkljHJSDalhjkasd789A897SD98AS89SIHUasuiyaskjhS987as89";

	public static String gerarTokenAutenticacao(Usuario usuario)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {

		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, 1);

		Date dataExpiracao = calendario.getTime();
		return JWT.create().withExpiresAt(dataExpiracao).withIssuedAt(new Date()).withIssuer(TOKEN_AUTH_ASSINANTE)
				.withClaim("id", usuario.getId())
				.withClaim("email", usuario.getEmail())
				.withClaim("tokenBRQ", "Basic YWRtaW5TZW5haTpzZW5haUAxMjM=")
				.withClaim("tipo", usuario.getTipo().toString())
				.withClaim("nome", usuario.getNome())
				.withClaim("sobrenome", usuario.getSobrenome())
				.withClaim("projetoVinculado", usuario.getProjetoVinculado())
				.sign(Algorithm.HMAC512(TOKEN_AUTH_CHAVE_PRIVADA));

	}

	public static Usuario obterUsuarioDoTokenAutenticacao(String token)
			throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		validarToken(token);

		Usuario usuario = new Usuario();
		DecodedJWT decodedJwt = JWT.decode(token);

		// Aplicando os dados do token no usuário
		usuario.setId(decodedJwt.getClaim("id").asLong());
		usuario.setEmail(decodedJwt.getClaim("email").asString());
		usuario.setNome(decodedJwt.getClaim("nome").asString());

		return usuario;
	}

	public static boolean validarToken(String token)
			throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {

		try {
			JWT.require(Algorithm.HMAC512(TOKEN_AUTH_CHAVE_PRIVADA)).build().verify(token);
			return true;
		} catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			return false;
		}
	}

}
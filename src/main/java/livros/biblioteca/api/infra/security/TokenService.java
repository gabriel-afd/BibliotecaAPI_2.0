package livros.biblioteca.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.Valid;
import livros.biblioteca.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Para ler o aplicattion.propreties
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API bibliotecas.livros")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("erro ao gerar token jwt");
        }

    }

    //Metodo para recuperar o subject(login do usuario)
    public String getSubject(String tokenJKT){

        var algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("API bibliotecas.livros")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJKT) //Verfica se o token está valido de acordo com algoritmo e issuer
                    .getSubject(); //Seleciona o subject


        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw new RuntimeException("Token invalido ou expirado");
        }

    }

    private Instant dataExpiracao() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

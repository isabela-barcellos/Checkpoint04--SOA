package br.com.fiap3espb.auto_escola_3espb;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptadorDeSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("$2a$10$CW59NgzKm073nkMQLed1..nQKFNgova8j7UHOfwLD3zJFKlJfbCr6");
        System.out.println(hash);
    }
}
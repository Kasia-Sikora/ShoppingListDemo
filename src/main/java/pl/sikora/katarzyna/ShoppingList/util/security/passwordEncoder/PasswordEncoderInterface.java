package pl.sikora.katarzyna.ShoppingList.util.security.passwordEncoder;

public interface PasswordEncoderInterface {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

}

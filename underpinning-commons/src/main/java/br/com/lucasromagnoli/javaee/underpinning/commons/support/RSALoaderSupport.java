package br.com.lucasromagnoli.javaee.underpinning.commons.support;


import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public class RSALoaderSupport {
    public static final String ALGORITHM = "RSA";
    private RSALoaderSupport() {}

    public static RSAPrivateKey loadPrivateKey(String keyPath) throws UnderpinningException {
        try {
            byte[] privateBytes = getKey(keyPath);
            PKCS8EncodedKeySpec pkcsKey = new PKCS8EncodedKeySpec(privateBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(pkcsKey);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new UnderpinningException("Não foi possível carregar a Private Key", e);
        }
    }

    public static RSAPublicKey loadPublicKey(String keyPath) throws UnderpinningException {
        try {
            byte[] publicBytes = getKey(keyPath);
            X509EncodedKeySpec pkcsKey = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return (RSAPublicKey) keyFactory.generatePublic(pkcsKey);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new UnderpinningException("Não foi possível carregar a Public Key", e);
        }
    }

    private static byte[] getKey(String keyPath) throws IOException {
        InputStream inputStream = null;
        byte[] arrayBytes = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyPath);
            arrayBytes = IOUtils.toByteArray(inputStream);
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return arrayBytes;
    }
}

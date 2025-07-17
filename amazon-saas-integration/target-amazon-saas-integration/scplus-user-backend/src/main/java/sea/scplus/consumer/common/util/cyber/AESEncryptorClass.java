package sea.scplus.consumer.common.util.cyber;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

public class AESEncryptorClass { 

    public static String encrypt(String psid, String secKey, String salt) {
        try {
               IvParameterSpec iv = new IvParameterSpec(salt.getBytes("UTF-8"));
               SecretKeySpec skeySpec = new SecretKeySpec(secKey.getBytes("UTF-8"), "AES");
               Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
               cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
               byte[] encrypted = cipher.doFinal(psid.getBytes());
               //return Base64.encodeBase64String(encrypted);
               return Base64Utils.encodeToString(encrypted);
            } catch (Exception ex) {
                   System.out.println("Some exception occurred while trying to ecrypt : " + ex.getMessage());
            }
      return null;
    }
 
}

package Spring.Security;

import org.springframework.stereotype.Component;

@Component
public class HeaderEncryption {
    public HeaderEncryption() {
    }
    public String decodeLoginFromHeaderBasic64(String header){
        header = header.replace("Basic ", "");
        header = new String(java.util.Base64.getDecoder().decode(header));
        return header.split(":")[0];
    }
    public String decodePasswordFromHeaderBasic64(String header){
        header = header.replace("Basic ", "");
        header = new String(java.util.Base64.getDecoder().decode(header));
        return header.split(":")[1];
    }
}
//finished
package malucismanagement.util;

public class ManipularSenha {
    
    public static String criptoSenha(String senha) {
        
        int tam, cod;
        String senhaCripto = "";
        
        tam = senha.length();
        senha = senha.toUpperCase();
        for(int cont = 0 ; cont < tam ; cont++) {
            
            cod = senha.charAt(cont) + 123;
            senhaCripto = senhaCripto + (char)cod;
        }

        return senhaCripto;
    }

    public static String decriptoSenha(String senha) {
        
        int tam, cod;
        String senhaDesripto = "";
        
        tam = senha.length();
        senha = senha.toUpperCase();
        for(int cont = 0 ; cont < tam ; cont++) {
            
            cod = senha.charAt(cont) - 123;
            senhaDesripto = senhaDesripto + (char)cod;
        }

        return senhaDesripto;
    }
}
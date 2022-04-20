package ibm.itau.projetobancocentral.configs;

public class EnviromentConfig {
    String frase;
    public EnviromentConfig(String frase){

        this.frase = frase;
    }

    public void getfrase(){
        System.out.println(this.frase);

    }

}

public class Usuario {
    private String name;
    private String email;
    private int idade;
    private String altura;

    public Usuario(String name, String email, int idade, String altura) {
        this.name = name;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public String getAltura() {
        return altura;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\nEmail: " + email + "\nIdade: " + idade + "\nAltura: " + altura +"\n";
    }


}

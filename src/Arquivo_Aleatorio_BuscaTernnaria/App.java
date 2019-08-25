package Arquivo_Aleatorio_BuscaTernnaria;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Arquivo arquivo = new Arquivo("Alunos.txt");
        Aluno aluno1 = new Aluno("Angelo", 35);
        Aluno aluno2 = new Aluno("Mariana", 14);
        Aluno aluno3 = new Aluno("Andre", 40);
        Aluno aluno4 = new Aluno("Lucimar", 36);
        Aluno aluno5 = new Aluno("Daniel", 28);
        Aluno aluno6 = new Aluno("Lyncon", 29);
        Aluno aluno7 = new Aluno("Ana", 33);
        Aluno aluno8 = new Aluno("Cristina", 34);

        salvarAlunos(arquivo, aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8);


        arquivo.listarArquivo();
        System.out.println(arquivo.pesquisaTernario(25).getString());


    }

    private static void salvarAlunos(Arquivo arquivo, Aluno aluno9, Aluno aluno0, Aluno aluno10, Aluno aluno11, Aluno aluno12, Aluno aluno13, Aluno aluno14, Aluno aluno15) throws IOException {
        arquivo.salvarRegistro(aluno9);
        arquivo.salvarRegistro(aluno0);
        arquivo.salvarRegistro(aluno10);
        arquivo.salvarRegistro(aluno11);
        arquivo.salvarRegistro(aluno12);
        arquivo.salvarRegistro(aluno13);
        arquivo.salvarRegistro(aluno14);
        arquivo.salvarRegistro(aluno15);
    }
}

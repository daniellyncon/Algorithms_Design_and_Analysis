package Arquivo_Aleatorio_BuscaTernnaria;

import java.io.*;

import static Arquivo_Aleatorio_BuscaTernnaria.Aluno.TAM_NOME;

public class Arquivo {

    String nomeArquivo;

    Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    Arquivo() {
        this.nomeArquivo = "";
    }


    public void salvarRegistro(Registro r) throws IOException {
        RandomAccessFile file = new RandomAccessFile(nomeArquivo, "rw");
        file.seek(file.length());
        r.setCodigo(proximoCodigo());
        file.writeInt(r.getByteArray().length);
        file.write(r.getByteArray());
        file.close();
    }

    public int proximoCodigo() {
        byte[] b;
        Aluno a = new Aluno();
        try (RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r")) {
            int tam_registro_bytes = 4 + 4 + TAM_NOME + 4;
            long pos_final = file.length() - tam_registro_bytes;
            file.seek(pos_final);
            int size = file.readInt();
            b = new byte[size];
            file.read(b);
            a.setByteArray(b);
        } catch (IOException e) {
            System.out.println("Primeiro registro!");
        }
        return a.getCodigo() + 1;
    }

    public void listarArquivo() throws IOException {
        RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");

        int cont = 0;
        System.out.println("# " + "Nome\t\t\t\t   "+"Idade");
        while (cont < file.length()) {

            int size = file.readInt();
            byte[] b = new byte[size];
            file.read(b);

            Aluno a = new Aluno();
            a.setByteArray(b);
            a.print();

            cont = cont + 4 + 4 + size;

        }
        file.close();
    }

//    public Aluno pesquisaArquivo(String nome) throws IOException {
//        RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");
//
//        int cont = 0;
//        while (cont < file.length()) {
//
//            //Obtém o tamanho do registro (primeiros 4 bytes)
//            int size = file.readInt();
//
//            //Obtem os demais bytes (4 do código + restantes ref ao Nome)
//            byte[] b = new byte[size];
//            file.read(b); //Armazena em b
//
//            Aluno a = new Aluno();
//            a.setByteArray(b); //carregaDados
//            //a.print();
//
//            if (a.getNome().equals(nome)) {
//                file.close();
//                return a;
//            }
//            cont = cont + 4 + size + 4 + 4;
//        }
//        file.close();
//        return null;
//    }


    public Aluno pesquisaTernario(int codigo) throws IOException {

        RandomAccessFile file = new RandomAccessFile(nomeArquivo, "rw");

        file.seek(0);
        int tam_registro_bytes = 4 + 4 + TAM_NOME + 4;
        long pos_inicial = 0;
        long pos_final = file.length() - tam_registro_bytes;
        if (codigo*tam_registro_bytes > pos_final) {
            System.out.println("Registro ainda nao cadastrado");
            return null;
        }
        return pesquisaTernario(file, codigo, (int) pos_inicial, (int) pos_final, tam_registro_bytes);
    }

    public Aluno pesquisaTernario(RandomAccessFile file, int codigo, int inicio, int fim, int tam) throws IOException {
        long pos_meio1;
        long pos_meio2;
        int registrosRestantes = (fim - inicio) / tam;
        if (registrosRestantes%3 == 0){
            pos_meio1 = inicio + (registrosRestantes/3)*tam;
            pos_meio2 = inicio + ((registrosRestantes/3)*tam)*2;
        } else {
            int i = (registrosRestantes / 3) * tam;
            pos_meio1 = inicio + i;
            pos_meio2 = inicio + (i *2);
        }

        file.seek(pos_meio1);

        //Ler o registro na posição definida
        int size = file.readInt();
        byte[] b = new byte[size];
        file.read(b);

        //Carrega o objeto da classe aluno e verifica o código
        Aluno a1 = new Aluno();
        a1.setByteArray(b);
        if (a1.getCodigo() == codigo) {
            file.close();
            return a1;
        } else if (codigo < a1.getCodigo()) {
            return pesquisaTernario(file, codigo, inicio, (int) pos_meio1, tam);
        } else {
            file.seek(pos_meio2);

            size = file.readInt();
            b = new byte[size];
            file.read(b);
            Aluno a2 = new Aluno();
            a2.setByteArray(b);
            if (codigo == a2.getCodigo()) {
                file.close();
                return a2;
            } else if (codigo < a2.getCodigo()) {

                return pesquisaTernario(file, codigo, (int) pos_meio1, (int) pos_meio2, tam);

            } else {

                return pesquisaTernario(file, codigo, (int) pos_meio2, fim, tam);
            }

        }
    }
}

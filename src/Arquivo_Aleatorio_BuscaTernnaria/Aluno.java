package Arquivo_Aleatorio_BuscaTernnaria;

import java.io.*;

public class Aluno implements Registro {
	protected int codigo;
	protected String nome;
	protected int idade;

	Aluno(String nome, int codigo, int idade){
		this.nome = nome;
		this.codigo = codigo;
		this.idade = idade;
	}

	Aluno(){}

	Aluno(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public final static int TAM_NOME = 20;

	public void setCodigo(int codigo)  {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getString() {
		return "Codigo: " + codigo + " Nome: " + nome + " Idade: " + idade;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "Codigo: " + codigo + "Idade: " + idade;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public static String getStringTamanhoFixo(String texto, int tamanho) {
		StringBuffer s1 = new StringBuffer(texto);
		s1.setLength(tamanho);
		return s1.toString();
	}

	public byte[] getByteArray() throws IOException {
		ByteArrayOutputStream registro = new ByteArrayOutputStream();
		DataOutputStream saida = new DataOutputStream(registro);
		saida.writeInt(codigo);

		saida.writeInt(idade);
		//Escreve Tamanho Fixo
		saida.write((getStringTamanhoFixo(nome,TAM_NOME)).getBytes());

		return registro.toByteArray();
	}

	public void setByteArray(byte[] b) throws IOException {
		ByteArrayInputStream registro = new ByteArrayInputStream(b);
		DataInputStream entrada = new DataInputStream(registro);
		codigo = entrada.readInt();
		idade = entrada.readInt();
		//nome = entrada.readUTF();
		nome = entrada.readLine();

	}

	public int compareTo(Object b) {
		return codigo - ((Aluno) b).getCodigo();
	}

	public Aluno clone() throws CloneNotSupportedException {
		return ((Aluno) super.clone());
	}

	public void print() {
		System.out.println(this.codigo + " " + this.nome + " " + this.idade);
	}
}

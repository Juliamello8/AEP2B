package com.github.faeldn.aep.console;

import java.util.List;
import java.util.Scanner;

import com.github.faeldn.aep.models.Patient;
import com.github.faeldn.aep.repository.RepositoryPatientDB;

public class ControllerPacient {
	
	private RepositoryPatientDB repositoryPatients = new RepositoryPatientDB();
	private Scanner scan = new Scanner(System.in);
	
	public void listAll() {
		List<Patient> list = this.repositoryPatients.all();
		
		if (list.isEmpty()) {
			System.out.println("Nenhum paciente cadastrado"); 
		}
		
		list.forEach (p -> {
			System.out.println("Nome: " + p.getName());
			System.out.println("Data de Nascimento: " + p.getBirthdate());
			System.out.println("Endere�o: " + p.getAdress());
			System.out.println("CEP: " + p.getCep());
			System.out.println("Cidade: " + p.getCity());
			System.out.println("Estado: " + p.getState());
			System.out.println("Carteirinha do SUS: " + p.getSuscode());
			System.out.println("Nome da M�e: " + p.getNamemom());
			System.out.println("_________________");
		});
	}
	
	public void listOne() {
		System.out.println("Digite o CPF a ser consultado: ");
		long cpf = scan.nextLong();
		scan.nextLine();
		Patient patient = repositoryPatients.one(cpf);
		
		if (patient!=null) {
			System.out.println("CPF: " + patient.getCpf());
			System.out.println("Nome do(a) paciente: " + patient.getName());
			System.out.println("N�mero de celular: " + patient.getCel());
			System.out.println("Data de nascimento: " + patient.getBirthdate());
			System.out.println("Endere�o: " + patient.getAdress());
			System.out.println("CEP: " + patient.getCep());
			System.out.println("Cidade: " + patient.getCity());
			System.out.println("Estado: " + patient.getState());
			System.out.println("N�mero de carteirinha SUS: " + patient.getSuscode());
			System.out.println("Nome da m�e: " + patient.getNamemom());
			System.out.println("Informa��es complementares (Sintomas, exames realizados, etc...): " + patient.getInfcompl());
			System.out.println("__________________________");
		} else {
		System.out.println("Paciente n�o encontrado!\n");
		}
	}
	
	public void add() {
		String cpf;
		String name;
		Long cel;
		String birthdate;
		String adress;
		String cep;
		String city;
		String state;
		String suscode;
		String namemom;
		String infcompl;
		
		System.out.println("Digite o CPF (Sem pontos e tra�o): ");
		cpf = scan.nextLine();
		System.out.println("Informe o nome completo do(a) paciente: ");
		name = scan.nextLine();
		System.out.println("Informe o n�mero de celular: ");
		cel = scan.nextLong();
		scan.nextLine();
		System.out.println("Informe a data de nascimento: ");
		birthdate = scan.nextLine();
		System.out.println("Informe o endere�o com n�mero e complemento: ");
		adress = scan.nextLine();
		System.out.println("CEP: ");
		cep = scan.nextLine();
		System.out.println("Cidade: ");
		city = scan.nextLine();
		System.out.println("Estado: ");
		state = scan.nextLine();
		System.out.println("Digite o c�digo do Cart�o SUS do(a) paciente: ");
		suscode = scan.nextLine();
		System.out.println("Informe o nome da m�e do(a) paciente: ");
		namemom = scan.nextLine();
		System.out.println("Informa��es complementares (Exames, Sintomas, Etc...): ");
		infcompl = scan.nextLine();
		Patient p = new Patient(cpf, name, cel, birthdate, adress, cep, city, state, suscode, namemom, infcompl);
		repositoryPatients.insert(p);
		System.out.println("\n***** Atendimento registrado, em breve um m�dico entrar� em contato atrav�s do seu n�mero de celular! *****\n");
		
	}
	
	public void delete() {
		System.out.println("Digite o CPF do(a) paciente que deseja cancelar o atendimento: ");
		long cpf = scan.nextLong();
		scan.nextLine();
		Patient patient = repositoryPatients.one(cpf);
		if (patient!=null) {
			repositoryPatients.delete(cpf);
			System.out.println("Consulta cancelada com sucesso!\n");
		} else {
			System.out.println("CPF n�o cadastrado.\n");
		}
	}

}

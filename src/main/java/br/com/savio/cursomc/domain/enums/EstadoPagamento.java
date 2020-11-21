package br.com.savio.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDETE(1, "Pessoa Física"),
	CANCELADO(1, "Pessoa Física"),
	QUITADO(2, "Pessoa Juridica");

	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;

	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod.equals(null))
			return null;
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id Invalido"+cod);
	}

}

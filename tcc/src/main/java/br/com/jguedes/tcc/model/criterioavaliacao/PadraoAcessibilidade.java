package br.com.jguedes.tcc.model.criterioavaliacao;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "padraoacessibilidade")
@XmlEnum
public enum PadraoAcessibilidade {

	@XmlEnumValue(value = "1") WCAG(1),

	@XmlEnumValue(value = "2") EMAG(2);

	private final int value;

	private PadraoAcessibilidade(int value) {

		this.value = value;

	}

	public int getValue() {
		return value;
	}

	public static PadraoAcessibilidade getOrgao(int value) {
		for (PadraoAcessibilidade o : values()) {
			if (o.value == value) {
				return o;
			}
		}
		return null;
	}

}

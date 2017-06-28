package br.com.jguedes.tcc.model.criterioavaliacao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "profundidade")
@XmlEnum
public enum Profundidade {

	@XmlEnumValue(value = "1") NIVEL1(1), //
	@XmlEnumValue(value = "2") NIVEL2(2), //
	@XmlEnumValue(value = "3") NIVEL3(3), //
	@XmlEnumValue(value = "4") SITE(4), //
	@XmlEnumValue(value = "5") PAGINA(5), //
	@XmlEnumValue(value = "6") HTML_LOCAL(6), //
	@XmlEnumValue(value = "7") DIRETORIO_LOCAL(7);//

	@XmlElement
	private final int value;

	private Profundidade(int value) {

		this.value = value;

	}

	public int getValue() {
		return value;
	}

	public static Profundidade getProfundidade(int value) {
		for (Profundidade p : values()) {
			if (p.value == value) {
				return p;
			}
		}
		return null;
	}

}

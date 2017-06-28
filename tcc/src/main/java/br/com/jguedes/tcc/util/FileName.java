package br.com.jguedes.tcc.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum FileName {

	@XmlEnumValue(value = "criteriodeavaliacao.xml") CRITERIODEAVALIACAO("criteriodeavaliacao.xml"), //
	@XmlEnumValue(value = "resumodeavaliacaoatual.xml") RESUMODEAVALICAOATUAL("resumodeavaliacaoatual.xml"), //
	@XmlEnumValue(value = "contextodeavaliacao.xml") CONTEXTODEAVALIACAO("contextodeavaliacao.xml");

	@XmlElement
	private final String value;

	private FileName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static FileName getFileName(String value) {

		for (FileName f : FileName.values()) {
			if (f.value.equals(value)) {
				return f;
			}
		}
		return null;
	}

}

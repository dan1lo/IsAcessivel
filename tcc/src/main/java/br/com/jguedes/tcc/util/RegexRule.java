package br.com.jguedes.tcc.util;

import java.util.regex.Pattern;

public class RegexRule {
	private final boolean accept;
	private Pattern pattern;

	protected RegexRule(boolean accept, String regex) {

		this.accept = accept;

		this.pattern = Pattern.compile(regex);

	}

	protected boolean accept() {
		return this.accept;
	}

	protected boolean match(String url) {
		return this.pattern.matcher(url).find();
	}
}

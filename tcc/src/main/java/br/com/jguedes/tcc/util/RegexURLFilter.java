package br.com.jguedes.tcc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegexURLFilter {

	private static final Logger LOG = LoggerFactory.getLogger(RegexURLFilter.class);

	private List<RegexRule> rules;

	public RegexURLFilter() {
	}

	public RegexURLFilter(File filename) throws IOException, IllegalArgumentException {
		this(new FileReader(filename));
	}

	public RegexURLFilter(String rules) throws IOException, IllegalArgumentException {
		this(new StringReader(rules));
	}

	protected RegexURLFilter(Reader reader) throws IOException, IllegalArgumentException {
		this.rules = readRules(reader);
	}

	protected RegexRule createRule(boolean paramBoolean, String paramString) {

		return new RegexRule(paramBoolean, paramString);

	}

	public String filter(String url) {
		for (RegexRule rule : this.rules) {
			if (rule.match(url)) {
				return rule.accept() ? url : null;
			}
		}
		return null;
	}

	private List<RegexRule> readRules(Reader reader) throws IOException, IllegalArgumentException {

		BufferedReader in = new BufferedReader(reader);

		List<RegexRule> rules = new ArrayList<RegexRule>();

		String line;

		while ((line = in.readLine()) != null)

			if (line.length() != 0) {

				char first = line.charAt(0);

				boolean sign = false;

				boolean ignore = false;

				switch (first) {

				case '+':
					sign = true;
					break;
				case '-':
					sign = false;
					break;
				case '\n':
				case ' ':
				case '#':
					ignore = true;
					break;
				default:
					throw new IOException("Invalid first character: " + line);
				}

				if (!ignore) {

					String regex = line.substring(1);

					if (LOG.isInfoEnabled())

						LOG.info("Adding rule [" + regex + "]");

					RegexRule rule = createRule(sign, regex);

					rules.add(rule);

				}

			}

		return rules;
	}
}

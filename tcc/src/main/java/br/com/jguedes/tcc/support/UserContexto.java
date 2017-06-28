package br.com.jguedes.tcc.support;

import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.xml.bind.JAXBException;

import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.model.criterioavaliacao.CriterioAvaliacao;
import br.com.jguedes.tcc.model.relatorioresumido.ResumoDeAvaliacao;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;
import br.com.jguedes.tcc.util.FileName;
import br.com.jguedes.tcc.util.Pojo2XML;

public class UserContexto implements Serializable {

	private static final long serialVersionUID = -6420537180945770051L;
	private static String pathname;

	private static String getUserPath() {

		return pathname;

	}

	private static void toXML(Object object, FileName fileName) {

		try {

			Pojo2XML.marshalling(object, getUserPath().concat(fileName.getValue()));

		} catch (FileNotFoundException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Object fromXML(FileName fileName) {

		@SuppressWarnings("rawtypes")
		Class classe = null;

		if (fileName == FileName.CRITERIODEAVALIACAO) {
			classe = CriterioAvaliacao.class;
		} else if (fileName == FileName.RESUMODEAVALICAOATUAL) {
			classe = ResumoDeAvaliacao.class;
		} else if (fileName == FileName.CONTEXTODEAVALIACAO) {
			classe = ContextoDeAvaliacao.class;
		}

		try {

			return Pojo2XML.unMarshalling(getUserPath().concat(fileName.getValue()), classe);

		} catch (FileNotFoundException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	private static void setPathname(String pathname) {
		UserContexto.pathname = pathname;
	}

	public static void toXML(Object object, FileName fileName, User user) {

		toXML(object, fileName, user.getContexto().getFolderTemp());

	}

	public static Object fromXML(FileName fileName, User user) {

		return fromXML(fileName, user.getContexto().getFolderTemp());
	}

	public static Object fromXML(FileName fileName, String path) {

		setPathname(path);

		return fromXML(fileName);

	}

	public static void toXML(Object object, FileName fileName, String path) {

		setPathname(path);

		toXML(object, fileName);

	}

}

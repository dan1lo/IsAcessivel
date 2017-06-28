package br.com.jguedes.tcc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Pojo2XML implements Serializable {

	private static final long serialVersionUID = -635383198444264555L;

	public synchronized static File marshalling(Object pojo, String filePath)
			throws JAXBException, FileNotFoundException {

		JAXBContext context = JAXBContext.newInstance(pojo.getClass());

		Marshaller marshaller = context.createMarshaller();

		File file = new File(filePath);

		marshaller.marshal(pojo, file);

		return file;

	}

	public synchronized static Object unMarshalling(String filePath,
			@SuppressWarnings("rawtypes") Class... classesToBeBound) throws JAXBException, FileNotFoundException {

		JAXBContext context = JAXBContext.newInstance(classesToBeBound);

		Unmarshaller unMarshaller = context.createUnmarshaller();

		FileInputStream fileInputStream = new FileInputStream(filePath);

		return unMarshaller.unmarshal(fileInputStream);

	}

}

package br.com.jguedes.tcc.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectToBinary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7821574739259496580L;

	public static Object gravaObject(Object obj, File file) {

		try {

			FileOutputStream stream = new FileOutputStream(file);

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);

			objectOutputStream.writeObject(obj);

			objectOutputStream.flush();

			objectOutputStream.close();

			stream.close();

			return file;

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;

	}

	public static Object lerObject(File file) {

		try {

			FileInputStream stream = new FileInputStream(file);

			ObjectInputStream objectInputStream = new ObjectInputStream(stream);

			Object obj = objectInputStream.readObject();

			objectInputStream.close();

			stream.close();

			return obj;

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}

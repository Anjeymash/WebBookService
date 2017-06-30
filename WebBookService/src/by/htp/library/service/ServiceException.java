package by.htp.library.service;

import by.htp.library.dao.DAOException;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	public ServiceException(){super();}

	public ServiceException(String mes)
	{super(mes);}

	public ServiceException(Exception e)
	{super(e);}

	public ServiceException(String mes, Exception e)
	{super(mes, e);}

}

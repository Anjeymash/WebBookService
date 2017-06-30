package by.htp.library.service;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private final LibraryService libraryService = new LibraryServiceImpl();
	private final ClientService clientService = new ClientServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}
	public ClientService getClientService() {
		return clientService;
	}
}

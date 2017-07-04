package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.controller.command.CommandName;
import by.htp.library.controller.command.impl.AddBook;
import by.htp.library.controller.command.impl.ChangeBook;
import by.htp.library.controller.command.impl.DelBook;
import by.htp.library.controller.command.impl.FndBook;
import by.htp.library.controller.command.impl.Registration;
import by.htp.library.controller.command.impl.Show;
import by.htp.library.controller.command.impl.ShowAllBooks;
import by.htp.library.controller.command.impl.SingIn;
import by.htp.library.controller.command.impl.SingOut;
import by.htp.library.controller.command.impl.WrongRequest;

public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.ADDBOOK, new AddBook());
		repository.put(CommandName.FNDBOOK, new FndBook());
		repository.put(CommandName.DELBOOK, new DelBook());
		repository.put(CommandName.CHANGEBOOK, new ChangeBook());
		repository.put(CommandName.SHOW, new Show());
		repository.put(CommandName.SHOWALLBOOKS, new ShowAllBooks());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.SINGIN, new SingIn());
		repository.put(CommandName.SINGOUT, new SingOut());
		repository.put(CommandName.WRONGREQUEST, new WrongRequest());
		

	}

	public Command getCommand(String commandName) {
		
		Command command = null;
		try {
			commandName = commandName.toUpperCase();
			command = repository.get(CommandName.valueOf(commandName));
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONGREQUEST);
		}
		return command;
	}
	
}

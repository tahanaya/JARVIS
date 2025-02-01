


import com.example.coreai.CommandInterpreter;
import com.example.coreai.commands.GreetCommand;
import com.example.coreai.commands.HelpCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandInterpreterTest {

    @Test
    public void testGreetCommand() {
        CommandInterpreter interpreter = new CommandInterpreter();
        interpreter.registerCommand("greet", new GreetCommand());

        String result = interpreter.interpret("greet Taha");
        Assertions.assertEquals("Hello, Taha!", result);

        result = interpreter.interpret("greet");
        Assertions.assertEquals("Hello!", result);
    }

    @Test
    public void testHelpCommand() {
        CommandInterpreter interpreter = new CommandInterpreter();
        interpreter.registerCommand("greet", new GreetCommand());
        interpreter.registerCommand("help", new HelpCommand(interpreter));

        String result = interpreter.interpret("help");
        // Verify that the help message contains registered commands.
        Assertions.assertTrue(result.contains("greet"));
        Assertions.assertTrue(result.contains("help"));
    }

    @Test
    public void testUnknownCommand() {
        CommandInterpreter interpreter = new CommandInterpreter();
        String result = interpreter.interpret("unknownCommand");
        Assertions.assertTrue(result.contains("command ma3rfthach"));
    }
}

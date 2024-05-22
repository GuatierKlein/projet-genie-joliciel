package fr.miage.klein;

public class Command {
    private String command; 
    private String[] args;

    public Command(String commandLine) {
        String[] splitLine = commandLine.split(" ");
        if(splitLine.length == 0)
            return;
        
        command = splitLine[0];
        
        if(splitLine.length > 1)
            System.arraycopy(splitLine, 1, args, 0, splitLine.length - 1);
    }

    public String getCommand() {
        return command;
    } 

    public String[] getArgs() {
        if(args.length != 0)
            return args;

        return new String[0];
    }
}

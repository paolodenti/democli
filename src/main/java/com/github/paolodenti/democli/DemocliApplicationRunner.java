package com.github.paolodenti.democli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
public class DemocliApplicationRunner implements CommandLineRunner, ExitCodeGenerator {

    private final CheckSum checkSum;
    private final IFactory factory;
    private int exitCode;

    /**
     * Command line runner for the application.
     *
     * @param checkSum checksum command
     * @param factory  factory for creating command instances
     */
    public DemocliApplicationRunner(CheckSum checkSum, IFactory factory) {

        this.checkSum = checkSum;
        this.factory = factory;
    }

    @Override
    public void run(String... args) {

        exitCode = new CommandLine(checkSum, factory).execute(args);
    }

    @Override
    public int getExitCode() {

        return exitCode;
    }
}

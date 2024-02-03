package com.github.paolodenti.democli;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Component
@Command(
        name = "checksum",
        mixinStandardHelpOptions = true,
        version = "checksum 0.0.1",
        description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
public class CheckSum implements Callable<Integer> {

    @Parameters(
            index = "0..*",
            description = "The file whose checksum to calculate.")
    private String[] positionals;

    @Option(
            names = {"-a", "--algorithm"},
            description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "SHA-256";

    @Override
    public Integer call() {

        String input = positionals == null || positionals.length < 1 ? null : positionals[0];
        try {
            byte[] digest = MessageDigest.getInstance(algorithm).digest(getFileContent(input).getBytes());
            System.out.printf("%0" + (digest.length * 2) + "x", new BigInteger(1, digest));
            return 0;
        } catch (NoSuchAlgorithmException e) {
            System.out.printf("%nInvalid algorithm '%s'%n", algorithm);
            return 1;
        } catch (Exception e) {
            System.out.printf("%nGeneric error: %s%n", e);
            return 1;
        }
    }

    private String getFileContent(String file) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(file == null ? new InputStreamReader(System.in) : new FileReader(positionals[0]))) {
            char[] buffer = new char[1000];
            int c;
            while ((c = br.read(buffer)) > 0) {
                sb.append(buffer, 0, c);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("%nCannot load file '%s'%n", file);
        } catch (IOException e) {
            System.out.println("%nError while reading input: " + e.getMessage());
        }
        return sb.toString();
    }
}

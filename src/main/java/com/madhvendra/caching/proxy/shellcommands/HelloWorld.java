package com.madhvendra.caching.proxy.shellcommands;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class HelloWorld {

    @ShellMethod(key="hello-world")
    public String helloWorld(@ShellOption(defaultValue = "madhv") String name){

        return "hello world " + name;

    }

}

package spring.project.nyangmong.util;

import org.springframework.stereotype.Component;

@Component
public class OptionChange {

    // Y = true , N = false
    // IN = true, OUT = false
    public String change(String option) {

        if (option.equals("Y")) {
            return "true";
        } else if (option.equals("N")) {
            return "false";
        } else if (option.equals("IN")) {
            return "true";
        } else if (option.equals("OUT")) {
            return "flase";
        } else {
            return "null";
        }
    }

}
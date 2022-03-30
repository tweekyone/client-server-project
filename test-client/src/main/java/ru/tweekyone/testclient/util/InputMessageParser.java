package ru.tweekyone.testclient.util;

import ru.tweekyone.testclient.exceptions.IncorrectInputRequestException;

import java.util.HashMap;
import java.util.Map;

public class InputMessageParser {

    public static Map<String, String> parseInputMessage(String message) throws IncorrectInputRequestException {
        Map<String, String> result = new HashMap<>();

        if (message.startsWith("message=")) {
            result.put("message", message.substring(8));
        } else if (message.startsWith("find=")) {
            result.put("find", null);
            for (String s : message.substring(5).split(" ")) {
                String[] param = s.split(":");
                result.put(param[0], param[1]);
            }
        } else if (message.startsWith("exit")) {
            result.put("exit", null);
        } else {
            throw new IncorrectInputRequestException();
        }

        return result;
    }
}

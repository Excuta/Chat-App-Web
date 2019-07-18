package com.piedpiper.piperchat.bean.validation;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 2:17 PM
 * <p>
 * /**
 * Class parses Id path variables and throws {@link IllegalArgumentException}
 * which is handled in global advice
 */
public class IdParser {

    public long parse(String userId) {
        try {
            return Long.parseLong(userId);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}

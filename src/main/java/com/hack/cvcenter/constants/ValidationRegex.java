package com.hack.cvcenter.constants;

public interface ValidationRegex {

    static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    static final String DATE_REGEX = "^(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/(\\d{4})$";

    static final String TIME_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])$";

    static final String DOB_REGEX = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$";

}

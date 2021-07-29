package com.huseyin.youcontribute.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class DuplicatedRepositoryException extends RuntimeException {

    public DuplicatedRepositoryException(String organization, String repository) {
        super(String.format("Organization: %s Repository: %s already exists.", organization, repository));
    }

}

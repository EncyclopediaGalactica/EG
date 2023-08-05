package com.encyclopediagalactica.document.validation.validators;

public interface LongValidatorInterface {

    /**
     * Returns true if the provided id equals to the to value. In any other case, returns false.
     *
     * @param id the provided value
     * @param to equals to value
     * @return result
     */
    boolean equalsTo(Long id, Long to);
}

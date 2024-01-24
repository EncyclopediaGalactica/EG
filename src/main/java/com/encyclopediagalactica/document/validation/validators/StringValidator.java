package com.encyclopediagalactica.document.validation.validators;

public interface StringValidator {

    /**
     * Checks if the provided string length is equal or greater than the provided expected length. The provided string
     * will be trimmed.
     * <p>
     *     <ul>
     *         <li>if equal or bigger returns true</li>
     *         <li>if not equal or less returns false</li>
     *     </ul>
     * </p>
     *
     * @param str    the provided string
     * @param length the expected length
     * @return result
     */
    boolean isLongerOrEqualThan(String str, int length);

    /**
     * Checks if the provided string is null.
     *
     * <p>
     *     <ul>
     *         <li>if null returns true</li>
     *         <li>if not null returns false</li>
     *     </ul>
     * </p>
     *
     * @param str the provided string
     * @return return
     */
    boolean isNull(String str);

    /**
     * Returns true if the provided string is empty. The string is trimmed.
     *
     * @param str the provided string
     * @return result
     */
    boolean isEmpty(String str);
}

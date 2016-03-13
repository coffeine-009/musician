/**
 * Copyright (c) 2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 08/12/14 3:26 PM
 */

package com.coffeine.virtuoso.library.validator.anotation.implementation;

import com.coffeine.virtuoso.library.validator.anotation.InEnum;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for enumeration values.
 *
 * @version 1.0
 */
public class InEnumValidatorImpl implements ConstraintValidator<InEnum, List<String>> {

    /// *** Properties  *** ///
    /**
     * List of available values.
     */
    private List<String> valueList = null;


    /// *** Methods     *** ///
    /**
     * Validate input.
     *
     * @param values     List of values for checking.
     * @param context    Context.
     *
     * @return boolean true - input is valid, false - not valid.
     */
    @Override
    public boolean isValid(
        List<String> values,
        ConstraintValidatorContext context
    ) {
        for ( String value : values ) {
            if ( !valueList.contains( value.toUpperCase()) ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Initialization.
     *
     * @param constraintAnnotation    Annotation.
     */
    @Override
    public void initialize( InEnum constraintAnnotation ) {

        valueList = new ArrayList<>();

        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        @SuppressWarnings( "rawtypes" )
        Enum[] enumValArr = enumClass.getEnumConstants();

        for( @SuppressWarnings( "rawtypes" )Enum enumVal : enumValArr ) {
            valueList.add( enumVal.toString().toUpperCase() );
        }

    }
}

/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.thecoffeine.virtuoso.module.model.AbstractModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for Poet
 * @see Poet
 *
 * @version 1.0
 */
public class PoetTest extends AbstractModel {

    /*
* Test field validation for entity correct
*/
    @Test
    public void testPoetFieldSuccess() {

        Set < ConstraintViolation< Poet > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        Poet poetSuccess = new Poet(
            new ArrayList < PoetLocale >() {{
               add(
                   new PoetLocale(
                       "Test",
                       "Unit",
                       "Validation",
                       "en-US"
                   )
               );
            }},
            "uk-UA"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( poetSuccess );

        assertEquals(0, constraintViolationSet.size());


        //- Success: No user -//
        //- Create entity -//
        Poet poetFailureUser = new Poet(
            new ArrayList<PoetLocale>() {{
                add(
                    new PoetLocale(
                        "Test",
                        "Unit",
                        "Validation",
                        "en-US"
                    )
                );
            }},
            "uk-UA"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetFailureUser );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /*
     * Test field validation for entity failure
     */
    @Test
    public void testPoetFieldFailure() {

        Set<ConstraintViolation<Poet>> constraintViolationSet;

        //-Failure-//
        //-Create entity-//
        Poet poetFailure = new Poet(
            null
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetFailure );

        assertEquals( 4, constraintViolationSet.size() );
        for ( ConstraintViolation < Poet > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("data");
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add( NotNull.class );
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "may not be null" );
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }

        //- Failure: Incorrect length -//
        //- Create entity -//
        Poet poetFailureLength = new Poet(
            new ArrayList<PoetLocale>() {{
                add(
                    new PoetLocale(
                        "Test",
                        "Unit",
                        "Validation",
                        "en-US"
                    )
                );
            }},
            "123456"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetFailureLength );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation<Poet> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "locale" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "length must be between 0 and 5" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testPoetFieldEmpty() {

        Set<ConstraintViolation<Poet>> constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Poet poetFailureEmpty = new Poet(
            new ArrayList<PoetLocale>(),
            ""
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetFailureEmpty );

        assertEquals( 2, constraintViolationSet.size() );

        for ( ConstraintViolation<Poet> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String >() {{
                    add( "locale" );
                    add( "data" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }
}

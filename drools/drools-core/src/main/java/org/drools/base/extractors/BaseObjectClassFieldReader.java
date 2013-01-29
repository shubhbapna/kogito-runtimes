/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.base.extractors;

import org.drools.RuntimeDroolsException;
import org.drools.base.BaseClassFieldReader;
import org.drools.base.ValueType;
import org.drools.common.InternalWorkingMemory;

import java.lang.reflect.Method;

public abstract class BaseObjectClassFieldReader extends BaseClassFieldReader {

    private static final long serialVersionUID = 510l;

    public BaseObjectClassFieldReader() {

    }

    protected BaseObjectClassFieldReader(final int index,
                                         final Class< ? > fieldType,
                                         final ValueType valueType) {
        super( index,
               fieldType,
               valueType );
    }

    public abstract Object getValue(InternalWorkingMemory workingMemory,
                                    Object object);

    public boolean getBooleanValue(InternalWorkingMemory workingMemory,
                                   final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if ( value instanceof Boolean ) {
            return ((Boolean) value).booleanValue();
        }
        
        throw new RuntimeDroolsException( "Conversion to boolean not supported from " + getExtractToClass().getName() );
    }

    public byte getByteValue(InternalWorkingMemory workingMemory,
                             final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if ( value instanceof Character ) {
            return (byte) ((Character) value).charValue();
        } 
        
        throw new RuntimeDroolsException( "Conversion to byte not supported from " +  getExtractToClass().getName());
    }

    public char getCharValue(InternalWorkingMemory workingMemory,
                             final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if ( value instanceof Character ) {
            return ((Character) value).charValue();
        } 
        
        throw new RuntimeDroolsException( "Conversion to char not supported from " +  getExtractToClass().getName() );
    }

    public double getDoubleValue(InternalWorkingMemory workingMemory,
                                 final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if( value instanceof Character ) {
            return ((Character) value).charValue();
        }
        
        throw new RuntimeDroolsException( "Conversion to double not supported from " +  getExtractToClass().getName() );
    }

    public float getFloatValue(InternalWorkingMemory workingMemory,
                               final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if( value instanceof Character ) {
            return ((Character) value).charValue();
        }
        
        throw new RuntimeDroolsException( "Conversion to float not supported from " +  getExtractToClass().getName() );
    }

    public int getIntValue(InternalWorkingMemory workingMemory,
                           final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if( value instanceof Character ) {
            return ((Character) value).charValue();
        }
        
        throw new RuntimeDroolsException( "Conversion to int not supported from " +  getExtractToClass().getName() );
    }

    public long getLongValue(InternalWorkingMemory workingMemory,
                             final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if( value instanceof Character ) {
            return ((Character) value).charValue();
        }
        
        throw new RuntimeDroolsException( "Conversion to long not supported from " +  getExtractToClass().getName() );
    }

    public short getShortValue(InternalWorkingMemory workingMemory,
                               final Object object) {
        final Object value = getValue( workingMemory,
                                       object );

        if( value instanceof Character ) {
            return (short) ((Character) value).charValue();
        }
        
        throw new RuntimeDroolsException( "Conversion to short not supported from " +  getExtractToClass().getName() );
    }

    public boolean isNullValue(InternalWorkingMemory workingMemory,
                               final Object object) {
        if ( object == null ) {
            return true;
        } else {
            return getValue( workingMemory,
                             object ) == null;
        }
    }

    public Method getNativeReadMethod() {
        try {
            return this.getClass().getMethod( getNativeReadMethodName(),
                                              new Class[]{InternalWorkingMemory.class, Object.class} );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( "This is a bug. Please report to development team: " + e.getMessage(),
                                              e );
        }
    }

    public String getNativeReadMethodName() {
        Class<?> type = getExtractToClass();
        if (!type.isPrimitive()) {
            return "getValue";
        }
        return "get" + type.getName().substring(0, 1).toUpperCase() + type.getName().substring(1) + "Value";
    }

    public int getHashCode(InternalWorkingMemory workingMemory,
                           final Object object) {
        final Object value = getValue( workingMemory,
                                       object );
        return (value != null) ? value.hashCode() : 0;
    }

}

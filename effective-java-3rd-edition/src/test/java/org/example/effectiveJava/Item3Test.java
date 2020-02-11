package org.example.effectiveJava;

import org.example.effectiveJava.item3.EnumSingleton;
import org.example.effectiveJava.item3.SingletonPrivateInstance;
import org.example.effectiveJava.item3.SingletonPublicInstance;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Item3Test {

    /**
     * Singleton with public final field
     */
    @Test
    public void Get_PublicInstanceSingleton_IncrementedCounter() {
        SingletonPublicInstance singletonPublicInstance = SingletonPublicInstance.INSTANCE;
        assertEquals(0, singletonPublicInstance.getCounter());
        SingletonPublicInstance singletonPublicInstance2 = SingletonPublicInstance.INSTANCE;
        singletonPublicInstance.count();
        assertEquals(1, singletonPublicInstance2.getCounter());
        assertEquals(singletonPublicInstance, singletonPublicInstance2);
    }

    /**
     * Singleton with static factory
     */
    @Test
    public void Get_StaticFactorySingleton_IncrementedCounter() {
        SingletonPrivateInstance singletonPrivateInstance = SingletonPrivateInstance.getInstance();
        assertEquals(0, singletonPrivateInstance.getCounter());
        SingletonPrivateInstance singletonPrivateInstance2 = SingletonPrivateInstance.getInstance();
        singletonPrivateInstance.count();
        assertEquals(1, singletonPrivateInstance2.getCounter());
        assertEquals(singletonPrivateInstance, singletonPrivateInstance2);
    }

    /**
     * Singleton defined as enum
     */
    @Test
    public void Get_EnumSingleton_IncrementedCounter() {
        EnumSingleton enumSingleton = EnumSingleton.ENUM_SINGLETON;
        assertEquals(1, enumSingleton.getCounter());
        EnumSingleton enumSingleton1 = EnumSingleton.ENUM_SINGLETON;
        enumSingleton.count();
        assertEquals(2, enumSingleton1.getCounter());
        assertEquals(enumSingleton, enumSingleton1);
    }
}

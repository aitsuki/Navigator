package com.aitsuki.navigator.annotation;

public @interface Navigator {
    Argument[] args() default {};
}

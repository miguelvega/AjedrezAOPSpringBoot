// Ruta: com.fr.yncrea.isen.cir3.chess.annotation.RequireLogin.java

package com.fr.yncrea.isen.cir3.chess.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {}

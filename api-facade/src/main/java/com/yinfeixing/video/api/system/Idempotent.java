package com.yinfeixing.video.api.system;

import java.lang.annotation.*;

@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Idempotent {

}

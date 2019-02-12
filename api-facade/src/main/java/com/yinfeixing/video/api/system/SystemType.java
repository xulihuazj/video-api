package com.yinfeixing.video.api.system;

import java.lang.annotation.*;

/**
 * @author xulh on 2018-03-20.
 *  用户备注当前请求是否需要权限限制
 *    internal   内部系统权限校验
 *    external 外部用户权限
 *    common 表示不需要校验权限
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SystemType {
    String value() default "common";

}

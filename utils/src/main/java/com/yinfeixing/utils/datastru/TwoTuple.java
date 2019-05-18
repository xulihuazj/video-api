package com.yinfeixing.utils.datastru;

import java.io.Serializable;

/**
 * 两元组
 *
 * @param <A>
 * @param <B>
 * @author mazy
 */
public class TwoTuple<A, B> implements Serializable {

    private static final long serialVersionUID = -3133158426582297885L;

    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }
}

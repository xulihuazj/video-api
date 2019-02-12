package com.yinfeixing.utils.common;

public class HashArithmetic {

    public static long ELFHash(String str)
    {
        long hash = 0;
        long x;
        for(int i = 0; i < str.length(); i++)
        {
            hash = (hash << 4) + str.charAt(i);
            if((x = hash & 0xF0000000L) != 0)
            {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }
        return (hash & 0x7FFFFFFF);
    }
}



package com.znjt.rpc;

import com.google.protobuf.ByteString;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 */
public class ByteStringUtils {
    private ByteStringUtils(){}

    /**
     * Java字节数组转换为ByteString
     * @param datas
     * @return
     */
    public static ByteString changeBytes2ByteString(byte[] datas){
        if(datas==null){
            return null;
        }
        return ByteString.copyFrom(datas);
    }
}

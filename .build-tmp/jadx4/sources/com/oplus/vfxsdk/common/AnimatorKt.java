package com.oplus.vfxsdk.common;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AnimatorKt {
    public static final <E, T extends Comparable<? super T>> int binarySearchByKey(E[] eArr, T t, Function1 function1) {
        int length = eArr.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = i + length;
            int iCompareTo = t.compareTo((Comparable) function1.invoke(eArr[i2]));
            if (iCompareTo < 0) {
                length = i2 - 1;
            } else {
                if (iCompareTo <= 0) {
                    return i2;
                }
                i = i2 + 1;
            }
        }
        return i;
    }
}

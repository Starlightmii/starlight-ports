package com.oplus.systemui.notification.lockscreen.capsule.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ResourceUtils {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ResourceUtils";
    private static final String VECTOR_ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
    private static final String VECTOR_NAME_PATH = "path";
    private static final String VECTOR_NAME_PATH_DATA = "pathData";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v2, types: [com.oplusos.systemui.common.util.LogUtil$IDebug, java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.String] */
        public final String getVectorDrawablePathData(int i, Context context) {
            ?? attributeValue = 0;
            if (i != 0) {
                try {
                    XmlResourceParser xml = context.getResources().getXml(i);
                    for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                        if (eventType == 2 && Intrinsics.areEqual(xml.getName(), ResourceUtils.VECTOR_NAME_PATH)) {
                            attributeValue = xml.getAttributeValue(ResourceUtils.VECTOR_ANDROID_NAMESPACE, ResourceUtils.VECTOR_NAME_PATH_DATA);
                            return attributeValue;
                        }
                    }
                } catch (Resources.NotFoundException unused) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, ResourceUtils.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "Vector drawable not found: vectorResId:"), attributeValue, 8);
                    return attributeValue;
                }
            }
            return null;
        }
    }

    public static final String getVectorDrawablePathData(int i, Context context) {
        return Companion.getVectorDrawablePathData(i, context);
    }
}

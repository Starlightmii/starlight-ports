package com.oplus.systemui.notification.row;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallUtils;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationBackgroundCustomRowHandler {
    public static final NotificationBackgroundCustomRowHandler INSTANCE = new NotificationBackgroundCustomRowHandler();
    private static final LruCache<String, ContentBgColor> parseCache = new LruCache<>(50);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ContentBgColor {
        private final Float angle;
        private final List<Integer> colors;

        public ContentBgColor(Float f, List<Integer> list) {
            this.angle = f;
            this.colors = list;
        }

        public final Float component1() {
            return this.angle;
        }

        public final List<Integer> component2() {
            return this.colors;
        }

        public final ContentBgColor copy(Float f, List<Integer> list) {
            return new ContentBgColor(f, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ContentBgColor)) {
                return false;
            }
            ContentBgColor contentBgColor = (ContentBgColor) obj;
            return Intrinsics.areEqual(this.angle, contentBgColor.angle) && Intrinsics.areEqual(this.colors, contentBgColor.colors);
        }

        public final Float getAngle() {
            return this.angle;
        }

        public final List<Integer> getColors() {
            return this.colors;
        }

        public final int hashCode() {
            Float f = this.angle;
            return this.colors.hashCode() + ((f == null ? 0 : f.hashCode()) * 31);
        }

        public String toString() {
            return "ContentBgColor(angle=" + this.angle + ", colors=" + this.colors + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ContentBgColorJson {
        private final Float angle;
        private final List<String> colors;

        public ContentBgColorJson(Float f, List<String> list) {
            this.angle = f;
            this.colors = list;
        }

        public final Float component1() {
            return this.angle;
        }

        public final List<String> component2() {
            return this.colors;
        }

        public final ContentBgColorJson copy(Float f, List<String> list) {
            return new ContentBgColorJson(f, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ContentBgColorJson)) {
                return false;
            }
            ContentBgColorJson contentBgColorJson = (ContentBgColorJson) obj;
            return Intrinsics.areEqual(this.angle, contentBgColorJson.angle) && Intrinsics.areEqual(this.colors, contentBgColorJson.colors);
        }

        public final Float getAngle() {
            return this.angle;
        }

        public final List<String> getColors() {
            return this.colors;
        }

        public final int hashCode() {
            Float f = this.angle;
            return this.colors.hashCode() + ((f == null ? 0 : f.hashCode()) * 31);
        }

        public final ContentBgColor toContentBgColor() {
            List<String> list = this.colors;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (true) {
                Integer numValueOf = null;
                if (!it.hasNext()) {
                    break;
                }
                String str = (String) it.next();
                try {
                    numValueOf = Integer.valueOf(Color.parseColor(str));
                } catch (NumberFormatException e) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("流体云背景: 解析颜色字符串失败 ", str, ", ", Log.getStackTraceString(e), "NotificationMenuRowMetaBall-->"));
                    }
                } catch (IllegalArgumentException e2) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("流体云背景: 解析颜色字符串失败 ", str, ", ", Log.getStackTraceString(e2), "NotificationMenuRowMetaBall-->"));
                    }
                }
                if (numValueOf != null) {
                    arrayList.add(numValueOf);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new ContentBgColor(this.angle, arrayList);
        }

        public String toString() {
            return "ContentBgColorJson(angle=" + this.angle + ", colors=" + this.colors + ")";
        }
    }

    public final boolean enableMetaBallFeature(Context context) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            return false;
        }
        return CapsuleMetaBallUtils.isSupportMetaBall(context);
    }

    public final String getContentBgColor(CustomRowEntry customRowEntry) {
        Bundle extras;
        if (customRowEntry == null || (extras = customRowEntry.getExtras()) == null) {
            return null;
        }
        return extras.getString(OplusCustomRowControllerManager.EXTRA_KEY_CONTENT_BG_COLOR);
    }

    public final boolean needDrawBgColorByMetaBall(Context context, CustomRowEntry customRowEntry) {
        String contentBgColor;
        return (!enableMetaBallFeature(context) || (contentBgColor = getContentBgColor(customRowEntry)) == null || contentBgColor.length() == 0) ? false : true;
    }

    public final void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
        if (seedlingPlugin != null && enableMetaBallFeature(context)) {
            Bundle bundle = new Bundle();
            bundle.putString("method", "updateCardConfig");
            bundle.putInt("disableNtfContentBgColor", 1);
            NotificationMenuRowLog.e("[流体云] " + NotificationMenuRowLog.simpleNameAndHash(seedlingPlugin) + ", call with param = " + bundle);
            seedlingPlugin.call(bundle);
        }
    }

    public final void updateDrawableMetaColor(Context context, CustomRowEntry customRowEntry, MetaBallBlurDrawable metaBallBlurDrawable) {
        GradientDrawable.Orientation orientation;
        if (customRowEntry == null || metaBallBlurDrawable == null || !enableMetaBallFeature(context)) {
            return;
        }
        String contentBgColor = getContentBgColor(customRowEntry);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("流体云背景: colorJson = ", contentBgColor, "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
        }
        ContentBgColor contentBgColor2 = null;
        if (contentBgColor != null && contentBgColor.length() != 0) {
            LruCache<String, ContentBgColor> lruCache = parseCache;
            ContentBgColor contentBgColor3 = lruCache.get(contentBgColor);
            if (contentBgColor3 == null) {
                try {
                    ContentBgColorJson contentBgColorJson = (ContentBgColorJson) new GsonBuilder().create().fromJson(ContentBgColorJson.class, contentBgColor);
                    contentBgColor3 = contentBgColorJson != null ? contentBgColorJson.toContentBgColor() : null;
                    if (contentBgColor3 != null) {
                        lruCache.put(contentBgColor, contentBgColor3);
                    }
                } catch (JsonParseException e) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("流体云背景: 解析json出错 = ", Log.getStackTraceString(e), "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
                    }
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("流体云背景: 使用缓存结果 " + contentBgColor3));
            }
            contentBgColor2 = contentBgColor3;
        }
        if (contentBgColor2 == null || contentBgColor2.getColors().isEmpty()) {
            metaBallBlurDrawable.setMetaColor(0);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->流体云背景: 无颜色，return");
                return;
            }
            return;
        }
        if (contentBgColor2.getAngle() == null || contentBgColor2.getColors().size() == 1) {
            int iIntValue = contentBgColor2.getColors().get(0).intValue();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(iIntValue, "流体云背景:单色 setMetaColor --- ", "NotificationMenuRowMetaBall-->", "SystemUi--Notification");
            }
            NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawable, 1, "NotificationBackgroundCustomRowHandler setMetaColor");
            metaBallBlurDrawable.setMetaColor(iIntValue);
            return;
        }
        int iFloatValue = (90 - ((int) contentBgColor2.getAngle().floatValue())) % 360;
        if (iFloatValue < 0) {
            iFloatValue += 360;
        }
        int i = iFloatValue - (iFloatValue % 45);
        if (i == 0) {
            orientation = GradientDrawable.Orientation.LEFT_RIGHT;
        } else if (i == 45) {
            orientation = GradientDrawable.Orientation.BL_TR;
        } else if (i == 90) {
            orientation = GradientDrawable.Orientation.BOTTOM_TOP;
        } else if (i == 135) {
            orientation = GradientDrawable.Orientation.BR_TL;
        } else if (i == 180) {
            orientation = GradientDrawable.Orientation.RIGHT_LEFT;
        } else if (i == 225) {
            orientation = GradientDrawable.Orientation.TR_BL;
        } else if (i != 270) {
            orientation = i != 315 ? GradientDrawable.Orientation.LEFT_RIGHT : GradientDrawable.Orientation.TL_BR;
        } else {
            orientation = GradientDrawable.Orientation.TOP_BOTTOM;
        }
        int[] intArray = CollectionsKt___CollectionsKt.toIntArray(contentBgColor2.getColors());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("流体云背景:渐变色 setGradientColors --- " + orientation.name() + " ," + intArray));
        }
        NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawable, 1, "NotificationBackgroundCustomRowHandler setGradientColors");
        metaBallBlurDrawable.setGradientColors(orientation, intArray);
    }
}

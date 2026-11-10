package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.graphics.Rect;
import android.util.Log;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.posteffect.params.ClipShader;
import com.oplus.posteffect.params.ClipShaderParams;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.NotificationInnerShadowAdapter;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleBackgroundView;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MetaBallEarManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MetaBallEarManager";
    private List<MetaBallDrawableWrapper> drawableList;
    private CapsuleMetaBallController.MetaBallViewContext metaBallContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DrawablePosition {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ DrawablePosition[] $VALUES;
        public static final DrawablePosition END;
        public static final DrawablePosition START;

        static {
            DrawablePosition drawablePosition = new DrawablePosition("START", 0);
            START = drawablePosition;
            DrawablePosition drawablePosition2 = new DrawablePosition("END", 1);
            END = drawablePosition2;
            DrawablePosition[] drawablePositionArr = {drawablePosition, drawablePosition2};
            $VALUES = drawablePositionArr;
            $ENTRIES = EnumEntriesKt.enumEntries(drawablePositionArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static DrawablePosition valueOf(String str) {
            return (DrawablePosition) Enum.valueOf(DrawablePosition.class, str);
        }

        public static DrawablePosition[] values() {
            return (DrawablePosition[]) $VALUES.clone();
        }

        public final String toDebugString() {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                return "Start";
            }
            if (iOrdinal == 1) {
                return "End";
            }
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MetaBallDrawableWrapper {
        private final CapsuleBackgroundView bgView;
        private Pair<ClipShader, ClipShaderParams> clipParams;
        private final MetaBallBlurDrawable drawable;
        private DrawablePosition position;
        private Rect rect;

        public MetaBallDrawableWrapper(CapsuleBackgroundView capsuleBackgroundView, MetaBallBlurDrawable metaBallBlurDrawable, DrawablePosition drawablePosition, Rect rect, Pair<ClipShader, ClipShaderParams> pair) {
            this.bgView = capsuleBackgroundView;
            this.drawable = metaBallBlurDrawable;
            this.position = drawablePosition;
            this.rect = rect;
            this.clipParams = pair;
        }

        public final void changeEnableCustomShape() {
            ((ClipShaderParams) this.clipParams.getSecond()).setEnableClipShape(false);
            this.drawable.setCustomClipShaderParams((ClipShaderParams) this.clipParams.getSecond());
        }

        public final CapsuleBackgroundView getBgView() {
            return this.bgView;
        }

        public final Pair<ClipShader, ClipShaderParams> getClipParams() {
            return this.clipParams;
        }

        public final MetaBallBlurDrawable getDrawable() {
            return this.drawable;
        }

        public final DrawablePosition getPosition() {
            return this.position;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final void setClipParams(Pair<ClipShader, ClipShaderParams> pair) {
            this.clipParams = pair;
        }

        public final void setPosition(DrawablePosition drawablePosition) {
            this.position = drawablePosition;
        }

        public final void setRect(Rect rect) {
            this.rect = rect;
        }

        public final void setRoundCornerRadius(boolean z) {
            this.drawable.setCornerParams(SmoothRoundEx.INSTANCE.createCapsuleCornerParams(z ? CapsuleConfig.INSTANCE.getDefaultRadius() : 0.0f));
        }

        public final void setStrokeAndShadowAlpha(float f, float f2, Float f3) {
            CapsuleBackgroundView capsuleBackgroundView = this.bgView;
            if (capsuleBackgroundView != null) {
                capsuleBackgroundView.setStrokeAndShadowAlpha(f, f2, f3);
            }
        }

        /* JADX WARN: Code duplicated, block: B:6:0x001d  */
        public final void updateEarShape(int i) {
            CapsuleEarShapeTool capsuleEarShapeTool = CapsuleEarShapeTool.INSTANCE;
            int earEndHeightForCapsule = capsuleEarShapeTool.getEarEndHeightForCapsule();
            float fWidth = this.rect.width() / earEndHeightForCapsule;
            int iOrdinal = this.position.ordinal();
            boolean z = false;
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                } else if (i < 0) {
                    z = true;
                }
            } else if (i > 0) {
                z = true;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Rect rect = this.rect;
                StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(fWidth, earEndHeightForCapsule, "updateEarShape: earEndHeight=", ", process=", ", rect=");
                sbM.append(rect);
                Log.d("SystemUi--Notification", "MetaBallEarManager-->".concat(sbM.toString()));
            }
            capsuleEarShapeTool.updateEarShape(this.drawable, this.rect.width(), fWidth, z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DrawablePosition.values().length];
            try {
                DrawablePosition drawablePosition = DrawablePosition.START;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final void clearDrawableResource(String str) {
        List<MetaBallDrawableWrapper> list = this.drawableList;
        if (list != null) {
            for (MetaBallDrawableWrapper metaBallDrawableWrapper : list) {
                metaBallDrawableWrapper.changeEnableCustomShape();
                metaBallDrawableWrapper.setRoundCornerRadius(true);
                metaBallDrawableWrapper.setStrokeAndShadowAlpha(1.0f, 1.0f, null);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("reset drawable resource, reason: ", str, "MetaBallEarManager-->", "SystemUi--Notification");
                }
            }
        }
        this.drawableList = null;
        this.metaBallContext = null;
    }

    public final void initMetaBallViewContext(CapsuleMetaBallController.MetaBallViewContext metaBallViewContext) {
        this.metaBallContext = metaBallViewContext;
    }

    public final void initOrUpdateDrawableList(int i, Rect rect, Rect rect2) {
        if (this.drawableList != null) {
            updateDrawableListRect(rect, rect2);
            return;
        }
        boolean z = i > 0;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext = this.metaBallContext;
        List<MetaBallDrawableWrapper> listListOf = null;
        CapsuleBackgroundView nextCardBgView = metaBallViewContext != null ? metaBallViewContext.getNextCardBgView() : null;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext2 = this.metaBallContext;
        CapsuleBackgroundView baseCardBgView = metaBallViewContext2 != null ? metaBallViewContext2.getBaseCardBgView() : null;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext3 = this.metaBallContext;
        MetaBallBlurDrawable nextCardBgDrawable = metaBallViewContext3 != null ? metaBallViewContext3.getNextCardBgDrawable() : null;
        CapsuleMetaBallController.MetaBallViewContext metaBallViewContext4 = this.metaBallContext;
        MetaBallBlurDrawable baseCardBgDrawable = metaBallViewContext4 != null ? metaBallViewContext4.getBaseCardBgDrawable() : null;
        if (nextCardBgDrawable != null) {
            DrawablePosition drawablePosition = DrawablePosition.START;
            CapsuleEarShapeTool capsuleEarShapeTool = CapsuleEarShapeTool.INSTANCE;
            MetaBallDrawableWrapper metaBallDrawableWrapper = new MetaBallDrawableWrapper(nextCardBgView, nextCardBgDrawable, drawablePosition, rect, capsuleEarShapeTool.getInitEarShapeParams(z));
            if (baseCardBgDrawable != null) {
                listListOf = CollectionsKt__CollectionsKt.listOf(new MetaBallDrawableWrapper[]{metaBallDrawableWrapper, new MetaBallDrawableWrapper(baseCardBgView, baseCardBgDrawable, DrawablePosition.END, rect2, capsuleEarShapeTool.getInitEarShapeParams(!z))});
            }
        }
        this.drawableList = listListOf;
    }

    public final boolean shouldBeEarState(Rect rect) {
        return rect.width() < CapsuleEarShapeTool.INSTANCE.getEarEndHeightForCapsule();
    }

    public final void updateDrawableListRect(Rect rect, Rect rect2) {
        List<MetaBallDrawableWrapper> list = this.drawableList;
        if (list != null) {
            for (MetaBallDrawableWrapper metaBallDrawableWrapper : list) {
                if (WhenMappings.$EnumSwitchMapping$0[metaBallDrawableWrapper.getPosition().ordinal()] == 1) {
                    metaBallDrawableWrapper.setRect(rect2);
                } else {
                    metaBallDrawableWrapper.setRect(rect);
                }
            }
        }
    }

    public final void updateEarClipEnable() {
        List<MetaBallDrawableWrapper> list = this.drawableList;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((MetaBallDrawableWrapper) it.next()).changeEnableCustomShape();
            }
        }
    }

    public final void updateEarState(int i, Rect rect, Rect rect2) {
        Integer num;
        initOrUpdateDrawableList(i, rect, rect2);
        int earWidth = CapsuleConfig.INSTANCE.getEarWidth();
        int earEndHeightForCapsule = CapsuleEarShapeTool.INSTANCE.getEarEndHeightForCapsule();
        int i2 = earWidth + 20;
        List<MetaBallDrawableWrapper> list = this.drawableList;
        if (list != null) {
            Iterator<T> it = list.iterator();
            if (it.hasNext()) {
                Integer numValueOf = Integer.valueOf(((MetaBallDrawableWrapper) it.next()).getRect().width());
                while (it.hasNext()) {
                    Integer numValueOf2 = Integer.valueOf(((MetaBallDrawableWrapper) it.next()).getRect().width());
                    if (numValueOf.compareTo(numValueOf2) > 0) {
                        numValueOf = numValueOf2;
                    }
                }
                num = numValueOf;
            } else {
                num = null;
            }
            if (num != null) {
                int iIntValue = num.intValue();
                NotificationInnerShadowAdapter.CapsuleInnerShadowGroup capsuleInnerShadowGroup = NotificationInnerShadowAdapter.CapsuleInnerShadowGroup.INSTANCE;
                float fFloatValue = capsuleInnerShadowGroup.getOffsetShadowScaleX().get().floatValue();
                if (iIntValue > earWidth && earEndHeightForCapsule > earWidth) {
                    fFloatValue += (capsuleInnerShadowGroup.getMetaBallTouchOffsetScaleX().get().floatValue() - fFloatValue) * RangesKt___RangesKt.coerceIn((iIntValue - earWidth) / (earEndHeightForCapsule - earWidth), 0.0f, 1.0f);
                }
                List<MetaBallDrawableWrapper> list2 = this.drawableList;
                if (list2 != null) {
                    for (MetaBallDrawableWrapper metaBallDrawableWrapper : list2) {
                        if (shouldBeEarState(metaBallDrawableWrapper.getRect())) {
                            int iWidth = metaBallDrawableWrapper.getRect().width();
                            metaBallDrawableWrapper.setStrokeAndShadowAlpha(0.0f, (iWidth <= i2 || earEndHeightForCapsule <= i2) ? 0.0f : RangesKt___RangesKt.coerceIn((iWidth - i2) / (earEndHeightForCapsule - i2), 0.0f, 1.0f), Float.valueOf(fFloatValue));
                            metaBallDrawableWrapper.updateEarShape(i);
                        } else {
                            metaBallDrawableWrapper.setStrokeAndShadowAlpha(1.0f, 1.0f, Float.valueOf(fFloatValue));
                        }
                    }
                }
            }
        }
    }
}

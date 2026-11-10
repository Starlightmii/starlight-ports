package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.haptics.SegmentHaptics$SpringTension$;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.qs.base.util.SpecialLanguageTextScaleHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TextSizeConfig {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TextSizeConfig";
    private static volatile TextSizeConfig instance;
    private final Context context;
    public float defaultCellSizeTotalScale;
    private final OneXOneConfig oneXOneConfig;
    private final PreviewConfig previewConfig;
    public float smallCellSizeTotalScale;
    private final SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper = new SpecialLanguageTextScaleHelper();
    private final List<ITextStyle> textStyleList;
    public float twoXOneCellSizeTotalScale;
    private final TwoXOneLabelConfig twoXOneLabelConfig;
    private final TwoXOneLabelDescConfig twoXOneLabelDescConfig;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final TextSizeConfig getInstance(Context context) {
            TextSizeConfig textSizeConfig;
            TextSizeConfig textSizeConfig2 = TextSizeConfig.instance;
            if (textSizeConfig2 != null) {
                return textSizeConfig2;
            }
            synchronized (this) {
                textSizeConfig = TextSizeConfig.instance;
                if (textSizeConfig == null) {
                    textSizeConfig = new TextSizeConfig(context.getApplicationContext());
                    TextSizeConfig.instance = textSizeConfig;
                }
            }
            return textSizeConfig;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OneXOneConfig implements ITextStyle {
        public int lineHeight;
        public float textSize;
        public /* synthetic */ TextSizeConfig this$0;

        private final float getOneXOneScale() {
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            TextSizeConfig textSizeConfig = this.this$0;
            int cellHeight = companion.getInstance(textSizeConfig.context).getCellSize(false).getCellHeight() / companion.getInitialDensity(textSizeConfig.context);
            SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper = textSizeConfig.getSpecialLanguageTextScaleHelper();
            float initialDensity = companion.getInitialDensity(textSizeConfig.context);
            specialLanguageTextScaleHelper.getClass();
            float minScale = SpecialLanguageTextScaleHelper.getMinScale(initialDensity);
            float f = textSizeConfig.smallCellSizeTotalScale;
            return cellHeight >= 90 ? RangesKt___RangesKt.coerceIn(f, minScale, 1.15f) : RangesKt___RangesKt.coerceIn(f, minScale, 1.08f);
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public int getTextLineHeight() {
            return this.lineHeight;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public float getTextSize() {
            return this.textSize;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public final void updateTextStyle() {
            int i;
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            TextSizeConfig textSizeConfig = this.this$0;
            this.lineHeight = (int) Math.ceil(companion.lockDpi(textSizeConfig.context, 2131172017) * getOneXOneScale());
            if (textSizeConfig.getSpecialLanguageTextScaleHelper().isSpecialHeightLanguage()) {
                i = 2131172019;
            } else {
                i = textSizeConfig.getSpecialLanguageTextScaleHelper().isSecondSpecialHeightLanguage() ? 2131172021 : 2131172020;
            }
            this.textSize = companion.lockDpi(textSizeConfig.context, i) * getOneXOneScale();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PreviewConfig implements ITextStyle {
        public int lineHeight;
        public float textSize;
        public /* synthetic */ TextSizeConfig this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public int getTextLineHeight() {
            return this.lineHeight;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public float getTextSize() {
            return this.textSize;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public final void updateTextStyle() {
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            TextSizeConfig textSizeConfig = this.this$0;
            this.lineHeight = (int) (companion.lockDpi(textSizeConfig.context, 2131171516) * textSizeConfig.defaultCellSizeTotalScale);
            this.textSize = companion.lockDpi(textSizeConfig.context, 2131171519) * textSizeConfig.defaultCellSizeTotalScale;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TwoXOneLabelConfig implements ITextStyle {
        public int lineHeight;
        public float textSize;
        public /* synthetic */ TextSizeConfig this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public int getTextLineHeight() {
            return this.lineHeight;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public float getTextSize() {
            return this.textSize;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public final void updateTextStyle() {
            int i;
            TextSizeConfig textSizeConfig = this.this$0;
            if (!textSizeConfig.getSpecialLanguageTextScaleHelper().isSpecialHeightLanguage()) {
                textSizeConfig.getSpecialLanguageTextScaleHelper().isSecondSpecialHeightLanguage();
            }
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            this.lineHeight = (int) (companion.lockDpi(textSizeConfig.context, 2131171931) * textSizeConfig.twoXOneCellSizeTotalScale);
            if (textSizeConfig.getSpecialLanguageTextScaleHelper().isSpecialHeightLanguage()) {
                i = 2131171947;
            } else {
                i = textSizeConfig.getSpecialLanguageTextScaleHelper().isSecondSpecialHeightLanguage() ? 2131172232 : 2131171948;
            }
            this.textSize = companion.lockDpi(textSizeConfig.context, i) * textSizeConfig.twoXOneCellSizeTotalScale;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TwoXOneLabelDescConfig implements ITextStyle {
        public int lineHeight;
        public float textSize;
        public /* synthetic */ TextSizeConfig this$0;

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public int getTextLineHeight() {
            return this.lineHeight;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public float getTextSize() {
            return this.textSize;
        }

        @Override // com.oplus.systemui.plugins.qs.customize.view.tile.ITextStyle
        public final void updateTextStyle() {
            int i;
            TextSizeConfig textSizeConfig = this.this$0;
            int i2 = 2131171931;
            if (!textSizeConfig.getSpecialLanguageTextScaleHelper().isSpecialHeightLanguage() && !textSizeConfig.getSpecialLanguageTextScaleHelper().isSecondSpecialHeightLanguage()) {
                i2 = 2131171930;
            }
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            this.lineHeight = (int) (companion.lockDpi(textSizeConfig.context, i2) * textSizeConfig.twoXOneCellSizeTotalScale);
            if (textSizeConfig.getSpecialLanguageTextScaleHelper().isSpecialHeightLanguage()) {
                i = 2131171944;
            } else {
                i = textSizeConfig.getSpecialLanguageTextScaleHelper().isSecondSpecialHeightLanguage() ? 2131172231 : 2131171933;
            }
            this.textSize = companion.lockDpi(textSizeConfig.context, i) * textSizeConfig.twoXOneCellSizeTotalScale;
        }
    }

    public TextSizeConfig(Context context) {
        this.context = context;
        TwoXOneLabelConfig twoXOneLabelConfig = new TwoXOneLabelConfig();
        twoXOneLabelConfig.this$0 = this;
        VarHandle.storeStoreFence();
        this.twoXOneLabelConfig = twoXOneLabelConfig;
        TwoXOneLabelDescConfig twoXOneLabelDescConfig = new TwoXOneLabelDescConfig();
        twoXOneLabelDescConfig.this$0 = this;
        VarHandle.storeStoreFence();
        this.twoXOneLabelDescConfig = twoXOneLabelDescConfig;
        OneXOneConfig oneXOneConfig = new OneXOneConfig();
        oneXOneConfig.this$0 = this;
        VarHandle.storeStoreFence();
        this.oneXOneConfig = oneXOneConfig;
        PreviewConfig previewConfig = new PreviewConfig();
        previewConfig.this$0 = this;
        VarHandle.storeStoreFence();
        this.previewConfig = previewConfig;
        this.textStyleList = CollectionsKt__CollectionsKt.listOf(new ITextStyle[]{twoXOneLabelConfig, twoXOneLabelDescConfig, oneXOneConfig, previewConfig});
        this.defaultCellSizeTotalScale = 1.0f;
        this.twoXOneCellSizeTotalScale = 1.0f;
        this.smallCellSizeTotalScale = 1.0f;
    }

    public static final TextSizeConfig getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public final OneXOneConfig getOneXOneConfig() {
        return this.oneXOneConfig;
    }

    public final PreviewConfig getPreviewConfig() {
        return this.previewConfig;
    }

    public final SpecialLanguageTextScaleHelper getSpecialLanguageTextScaleHelper() {
        return this.specialLanguageTextScaleHelper;
    }

    public final void updateScale(float f) {
        SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper = this.specialLanguageTextScaleHelper;
        float initialDensity = CellCalculatorManager.Companion.getInitialDensity(this.context);
        specialLanguageTextScaleHelper.getClass();
        float minScale = SpecialLanguageTextScaleHelper.getMinScale(initialDensity);
        this.defaultCellSizeTotalScale = RangesKt___RangesKt.coerceIn(f, minScale, 1.15f);
        this.twoXOneCellSizeTotalScale = RangesKt___RangesKt.coerceIn(f, minScale, 1.13f);
        this.smallCellSizeTotalScale = f;
        Iterator<T> it = this.textStyleList.iterator();
        while (it.hasNext()) {
            ((ITextStyle) it.next()).updateTextStyle();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TextSizeConfig-->".concat(SegmentHaptics$SpringTension$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("[calculatorSystemScale] currentFontScale=", f, ", defaultCellSizeTotalScale=", this.defaultCellSizeTotalScale, ", twoXOneCellSizeTotalScale="), this.twoXOneCellSizeTotalScale, ", smallCellSizeTotalScale=", this.smallCellSizeTotalScale, ", ")));
        }
    }

    public final void updateTwoXOneTextStyleInfo(CellCalculatorManager.TwoXOneTextStyleInfo twoXOneTextStyleInfo) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TextSizeConfig-->[updateTwoXOneTextStyleInfo] tryUpdateInfo refresh fontScale");
        }
        twoXOneTextStyleInfo.setTwoXOneLabelTextSize(this.twoXOneLabelConfig.getTextSize());
        twoXOneTextStyleInfo.setTwoXOneLabelDescTextSize(this.twoXOneLabelDescConfig.getTextSize());
        twoXOneTextStyleInfo.setTwoXOneLabelLineHeight(this.twoXOneLabelConfig.getTextLineHeight());
        twoXOneTextStyleInfo.setTwoXOneLabelDescLineHeight(this.twoXOneLabelDescConfig.getTextLineHeight());
    }
}

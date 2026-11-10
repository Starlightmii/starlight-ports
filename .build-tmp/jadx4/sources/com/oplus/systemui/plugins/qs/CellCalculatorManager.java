package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.model.TimerSpan$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.fragment.app.Fragment$5$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.view.tile.TextSizeConfig;
import com.oplus.systemui.plugins.qs.customize.viewmodel.OplusSeparateTileNameState;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CellCalculatorManager {
    public static final Companion Companion = new Companion();
    private static final String LOCALE_COUNTRY = "CN";
    private static final String LOCALE_LANGUAGE_CN = "zh";
    private static final String TAG = "CellCalculatorManager";
    private static volatile CellCalculatorManager instance;
    private ICellAreaProvider cellAreaProvider;
    public int cellMarginBottom;
    private final Context context;
    public int noPersonalRowCountLandLeft;
    public int noPersonalRowCountLandRight;
    public int noPersonalRowCountPort;
    public int personalRowCount;
    private CellSize baseCellSize = new CellSize(dpToPxWithInitialDensity(76.0f), dpToPxWithInitialDensity(76.0f));
    private Map<SpanSize, LayoutConstraint> layoutConstraints = new LinkedHashMap();
    private final SpanSize basicSpanSize = SpanCache.INSTANCE.spanSize(1, 1);
    private CapsuleCalculateFactor lastCapsuleFactor = new CapsuleCalculateFactor(160.0f, false, "", this.baseCellSize, 1.0f, LOCALE_COUNTRY);
    private final CopyOnWriteArraySet<CellCalculatorListener> listeners = new CopyOnWriteArraySet<>();
    private final TwoXOneTextStyleInfo twoXOneTextStyleInfo = new TwoXOneTextStyleInfo(0.0f, 0.0f, 0, 0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleCalculateFactor {
        private CellSize cellSize;
        private String country;
        public float density;
        public float fontScale;
        public boolean isEditMode;
        private String language;

        public CapsuleCalculateFactor(float f, boolean z, String str, CellSize cellSize, float f2, String str2) {
            this.density = f;
            this.isEditMode = z;
            this.language = str;
            this.cellSize = cellSize;
            this.fontScale = f2;
            this.country = str2;
        }

        public final String component3() {
            return this.language;
        }

        public final CellSize component4() {
            return this.cellSize;
        }

        public final String component6() {
            return this.country;
        }

        public final CapsuleCalculateFactor copy(float f, boolean z, String str, CellSize cellSize, float f2, String str2) {
            return new CapsuleCalculateFactor(f, z, str, cellSize, f2, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleCalculateFactor)) {
                return false;
            }
            CapsuleCalculateFactor capsuleCalculateFactor = (CapsuleCalculateFactor) obj;
            return Float.compare(this.density, capsuleCalculateFactor.density) == 0 && this.isEditMode == capsuleCalculateFactor.isEditMode && Intrinsics.areEqual(this.language, capsuleCalculateFactor.language) && Intrinsics.areEqual(this.cellSize, capsuleCalculateFactor.cellSize) && Float.compare(this.fontScale, capsuleCalculateFactor.fontScale) == 0 && Intrinsics.areEqual(this.country, capsuleCalculateFactor.country);
        }

        public final CellSize getCellSize() {
            return this.cellSize;
        }

        public final String getCountry() {
            return this.country;
        }

        public final float getDensity() {
            return this.density;
        }

        public final float getFontScale() {
            return this.fontScale;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final int hashCode() {
            return this.country.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.fontScale, (this.cellSize.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Float.hashCode(this.density) * 31, 31, this.isEditMode), 31, this.language)) * 31, 31);
        }

        public final void setCellSize(CellSize cellSize) {
            this.cellSize = cellSize;
        }

        public final void setCountry(String str) {
            this.country = str;
        }

        public final void setDensity(float f) {
            this.density = f;
        }

        public final void setEditMode(boolean z) {
            this.isEditMode = z;
        }

        public final void setFontScale(float f) {
            this.fontScale = f;
        }

        public final void setLanguage(String str) {
            this.language = str;
        }

        public String toString() {
            return "CapsuleCalculateFactor(density=" + this.density + ", isEditMode=" + this.isEditMode + ", language=" + this.language + ", cellSize=" + this.cellSize + ", fontScale=" + this.fontScale + ", country=" + this.country + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CellCalculatorListener {
        void onCellSizeChange(boolean z);

        void onGridInfoChange(boolean z, boolean z2, boolean z3);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CellSize {
        public final int cellHeight;
        public final int cellWidth;

        public CellSize(int i, int i2) {
            this.cellWidth = i;
            this.cellHeight = i2;
        }

        public final CellSize copy(int i, int i2) {
            return new CellSize(i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CellSize)) {
                return false;
            }
            CellSize cellSize = (CellSize) obj;
            return this.cellWidth == cellSize.cellWidth && this.cellHeight == cellSize.cellHeight;
        }

        public final int getCellHeight() {
            return this.cellHeight;
        }

        public final int getCellWidth() {
            return this.cellWidth;
        }

        public final int hashCode() {
            return Integer.hashCode(this.cellHeight) + (Integer.hashCode(this.cellWidth) * 31);
        }

        public String toString() {
            return MutableVectorKt$.ExternalSyntheticOutline0.m("CellSize(cellWidth=", this.cellWidth, this.cellHeight, ", cellHeight=", ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LayoutConstraint {
        public final int capsuleHeight;
        public final int capsuleWidth;
        public final float innerContentRatio;
        public final float innerMarginRatio;
        public final int labelLineHeight;
        public final int labelLines;
        public final int labelMarginTop;
        public final int labelSize;
        public final int layoutMarginTop;
        private final TwoXOneTextStyleInfo twoXOneTextStyleInfo;

        public LayoutConstraint(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, float f2, TwoXOneTextStyleInfo twoXOneTextStyleInfo) {
            this.capsuleWidth = i;
            this.capsuleHeight = i2;
            this.layoutMarginTop = i3;
            this.labelMarginTop = i4;
            this.labelSize = i5;
            this.labelLines = i6;
            this.labelLineHeight = i7;
            this.innerContentRatio = f;
            this.innerMarginRatio = f2;
            this.twoXOneTextStyleInfo = twoXOneTextStyleInfo;
        }

        public final TwoXOneTextStyleInfo component10() {
            return this.twoXOneTextStyleInfo;
        }

        public final LayoutConstraint copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, float f2, TwoXOneTextStyleInfo twoXOneTextStyleInfo) {
            return new LayoutConstraint(i, i2, i3, i4, i5, i6, i7, f, f2, twoXOneTextStyleInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LayoutConstraint)) {
                return false;
            }
            LayoutConstraint layoutConstraint = (LayoutConstraint) obj;
            return this.capsuleWidth == layoutConstraint.capsuleWidth && this.capsuleHeight == layoutConstraint.capsuleHeight && this.layoutMarginTop == layoutConstraint.layoutMarginTop && this.labelMarginTop == layoutConstraint.labelMarginTop && this.labelSize == layoutConstraint.labelSize && this.labelLines == layoutConstraint.labelLines && this.labelLineHeight == layoutConstraint.labelLineHeight && Float.compare(this.innerContentRatio, layoutConstraint.innerContentRatio) == 0 && Float.compare(this.innerMarginRatio, layoutConstraint.innerMarginRatio) == 0 && Intrinsics.areEqual(this.twoXOneTextStyleInfo, layoutConstraint.twoXOneTextStyleInfo);
        }

        public final int getCapsuleHeight() {
            return this.capsuleHeight;
        }

        public final int getCapsuleWidth() {
            return this.capsuleWidth;
        }

        public final float getInnerContentRatio() {
            return this.innerContentRatio;
        }

        public final float getInnerMarginRatio() {
            return this.innerMarginRatio;
        }

        public final int getLabelLineHeight() {
            return this.labelLineHeight;
        }

        public final int getLabelLines() {
            return this.labelLines;
        }

        public final int getLabelMarginTop() {
            return this.labelMarginTop;
        }

        public final int getLabelSize() {
            return this.labelSize;
        }

        public final int getLayoutMarginTop() {
            return this.layoutMarginTop;
        }

        public final TwoXOneTextStyleInfo getTwoXOneTextStyleInfo() {
            return this.twoXOneTextStyleInfo;
        }

        public final int hashCode() {
            return this.twoXOneTextStyleInfo.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.innerMarginRatio, ChangeSize$.ExternalSyntheticOutline0.m(this.innerContentRatio, MonoSpline$.ExternalSyntheticOutline0.m(this.labelLineHeight, MonoSpline$.ExternalSyntheticOutline0.m(this.labelLines, MonoSpline$.ExternalSyntheticOutline0.m(this.labelSize, MonoSpline$.ExternalSyntheticOutline0.m(this.labelMarginTop, MonoSpline$.ExternalSyntheticOutline0.m(this.layoutMarginTop, MonoSpline$.ExternalSyntheticOutline0.m(this.capsuleHeight, Integer.hashCode(this.capsuleWidth) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
        }

        public String toString() {
            TwoXOneTextStyleInfo twoXOneTextStyleInfo = this.twoXOneTextStyleInfo;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("LayoutConstraint(capsuleWidth=", this.capsuleWidth, this.capsuleHeight, ", capsuleHeight=", ", layoutMarginTop=");
            RectList$.ExternalSyntheticOutline0.m(this.layoutMarginTop, this.labelMarginTop, ", labelMarginTop=", ", labelSize=", sbM);
            RectList$.ExternalSyntheticOutline0.m(this.labelSize, this.labelLines, ", labelLines=", ", labelLineHeight=", sbM);
            IconProvider$.ExternalSyntheticOutline0.m(this.innerContentRatio, this.labelLineHeight, ", innerContentRatio=", ", innerMarginRatio=", sbM);
            sbM.append(this.innerMarginRatio);
            sbM.append(", twoXOneTextStyleInfo=");
            sbM.append(twoXOneTextStyleInfo);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TwoXOneTextStyleInfo {
        public int twoXOneLabelDescLineHeight;
        public float twoXOneLabelDescTextSize;
        public int twoXOneLabelLineHeight;
        public float twoXOneLabelTextSize;

        public TwoXOneTextStyleInfo(float f, float f2, int i, int i2) {
            this.twoXOneLabelTextSize = f;
            this.twoXOneLabelDescTextSize = f2;
            this.twoXOneLabelLineHeight = i;
            this.twoXOneLabelDescLineHeight = i2;
        }

        public final TwoXOneTextStyleInfo copy(float f, float f2, int i, int i2) {
            return new TwoXOneTextStyleInfo(f, f2, i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TwoXOneTextStyleInfo)) {
                return false;
            }
            TwoXOneTextStyleInfo twoXOneTextStyleInfo = (TwoXOneTextStyleInfo) obj;
            return Float.compare(this.twoXOneLabelTextSize, twoXOneTextStyleInfo.twoXOneLabelTextSize) == 0 && Float.compare(this.twoXOneLabelDescTextSize, twoXOneTextStyleInfo.twoXOneLabelDescTextSize) == 0 && this.twoXOneLabelLineHeight == twoXOneTextStyleInfo.twoXOneLabelLineHeight && this.twoXOneLabelDescLineHeight == twoXOneTextStyleInfo.twoXOneLabelDescLineHeight;
        }

        public final int getTwoXOneLabelDescLineHeight() {
            return this.twoXOneLabelDescLineHeight;
        }

        public final float getTwoXOneLabelDescTextSize() {
            return this.twoXOneLabelDescTextSize;
        }

        public final int getTwoXOneLabelLineHeight() {
            return this.twoXOneLabelLineHeight;
        }

        public final float getTwoXOneLabelTextSize() {
            return this.twoXOneLabelTextSize;
        }

        public final int hashCode() {
            return Integer.hashCode(this.twoXOneLabelDescLineHeight) + MonoSpline$.ExternalSyntheticOutline0.m(this.twoXOneLabelLineHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.twoXOneLabelDescTextSize, Float.hashCode(this.twoXOneLabelTextSize) * 31, 31), 31);
        }

        public final void setTwoXOneLabelDescLineHeight(int i) {
            this.twoXOneLabelDescLineHeight = i;
        }

        public final void setTwoXOneLabelDescTextSize(float f) {
            this.twoXOneLabelDescTextSize = f;
        }

        public final void setTwoXOneLabelLineHeight(int i) {
            this.twoXOneLabelLineHeight = i;
        }

        public final void setTwoXOneLabelTextSize(float f) {
            this.twoXOneLabelTextSize = f;
        }

        public String toString() {
            return TimerSpan$.ExternalSyntheticOutline0.m(this.twoXOneLabelLineHeight, this.twoXOneLabelDescLineHeight, ", twoXOneLabelDescLineHeight=", ")", MonoSpline$.ExternalSyntheticOutline0.m("TwoXOneTextStyleInfo(twoXOneLabelTextSize=", this.twoXOneLabelTextSize, ", twoXOneLabelDescTextSize=", this.twoXOneLabelDescTextSize, ", twoXOneLabelLineHeight="));
        }
    }

    public CellCalculatorManager(Context context) {
        this.context = context;
    }

    public static final int getInitialDensity(Context context) {
        return Companion.getInitialDensity(context);
    }

    public static final CellCalculatorManager getInstance(Context context) {
        return Companion.getInstance(context);
    }

    private final int getLabelLines() {
        return !LOCALE_LANGUAGE_CN.equals(StringsKt__StringsKt.trim(Locale.getDefault().getLanguage()).toString()) ? 2 : 1;
    }

    public static /* synthetic */ LayoutConstraint getLayoutConstraint$default(CellCalculatorManager cellCalculatorManager, SpanSize spanSize, boolean z, boolean z2, int i) {
        if ((i & 2) != 0) {
            z = ((Boolean) OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName().getValue()).booleanValue();
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return cellCalculatorManager.getLayoutConstraint(spanSize, z, z2, (i & 8) != 0);
    }

    public static final int lockDpi(float f, Context context) {
        return Companion.lockDpi(f, context);
    }

    private final void setBaseCellSize(CellSize cellSize) {
        if (Intrinsics.areEqual(this.baseCellSize, cellSize)) {
            return;
        }
        this.baseCellSize = cellSize;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateCellSize: " + cellSize, null, 8);
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((CellCalculatorListener) it.next()).onCellSizeChange(false);
        }
    }

    private final void setNoPersonalRowCountLandLeft(int i) {
        int i2 = this.noPersonalRowCountLandLeft;
        if (i2 != i) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, i, "noPersonalRowCountLandLeft changed, from ", " to "), null, 8);
            this.noPersonalRowCountLandLeft = i;
            updateGridInfo(true, false, true);
        }
    }

    private final void setNoPersonalRowCountLandRight(int i) {
        int i2 = this.noPersonalRowCountLandRight;
        if (i2 != i) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, i, "noPersonalRowCountLandRight changed, from ", " to "), null, 8);
            this.noPersonalRowCountLandRight = i;
            updateGridInfo(true, false, false);
        }
    }

    private final void setNoPersonalRowCountPort(int i) {
        int i2 = this.noPersonalRowCountPort;
        if (i2 != i) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, i, "noPersonalRowCountPort changed, from ", " to "), null, 8);
            this.noPersonalRowCountPort = i;
            updateGridInfo(false, false, false);
        }
    }

    private final void setPersonalRowCount(int i) {
        Function0 cellAreaChangeListener;
        if (this.personalRowCount != i) {
            this.personalRowCount = i;
            ICellAreaProvider iCellAreaProvider = this.cellAreaProvider;
            if (iCellAreaProvider != null && (cellAreaChangeListener = iCellAreaProvider.getCellAreaChangeListener()) != null) {
                cellAreaChangeListener.invoke();
            }
            updateGridInfo(false, true, false);
            updateGridInfo(true, true, false);
        }
    }

    public final void addCellCalculatorListener(CellCalculatorListener cellCalculatorListener) {
        this.listeners.add(cellCalculatorListener);
    }

    public final int allocateCellSpace(int i, boolean z) {
        int i2 = this.personalRowCount;
        Companion companion = Companion;
        int iDpToPxWithInitialDensity = i - (companion.dpToPxWithInitialDensity(76.0f, this.context) * i2);
        if (iDpToPxWithInitialDensity < 0) {
            int i3 = this.personalRowCount;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("allocateCellSpace()-> no enough space for personalArea, noPersonalLeftH:", iDpToPxWithInitialDensity, i, ", leftH:", ", personalRowCount:");
            sbM.append(i3);
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, sbM.toString());
            setNoPersonalRowCountLandLeft(0);
            setNoPersonalRowCountPort(0);
            return 0;
        }
        boolean zBooleanValue = ((Boolean) OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName().getValue()).booleanValue();
        int iDpToPxWithInitialDensity2 = companion.dpToPxWithInitialDensity(zBooleanValue ? 88.0f : 76.0f, this.context);
        int i4 = iDpToPxWithInitialDensity / iDpToPxWithInitialDensity2;
        int i5 = 8 - this.personalRowCount;
        if (i4 > i5) {
            i4 = i5;
        }
        if (z) {
            setNoPersonalRowCountLandLeft(i4);
        } else {
            setNoPersonalRowCountPort(i4);
        }
        int i6 = iDpToPxWithInitialDensity - (i4 * iDpToPxWithInitialDensity2);
        int i7 = this.personalRowCount;
        int i8 = this.noPersonalRowCountPort;
        int i9 = this.noPersonalRowCountLandLeft;
        StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("allocateCellSpace()-> leftH:", i, iDpToPxWithInitialDensity, ", noPersonalLeftH:", ", personalRowCount:");
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i7, ", showTimeName:", ", cellMinH:", sbM2, zBooleanValue);
        RectList$.ExternalSyntheticOutline0.m(iDpToPxWithInitialDensity2, i8, ", noPersonalRowCountPort:", ", noPersonalRowCountLandLeft:", sbM2);
        sbM2.append(i9);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM2.toString(), null, 8);
        return i6;
    }

    public final void allocateCellSpaceForLandRight(int i) {
        if (i < 0) {
            return;
        }
        setNoPersonalRowCountLandRight(i / this.baseCellSize.getCellHeight());
        int cellHeight = this.baseCellSize.getCellHeight();
        int i2 = this.noPersonalRowCountLandRight;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("allocateCellSpaceForLandRight()-> leftH:", i, cellHeight, ", cellHeight:", ", noPersonalRowCountLandRight:");
        sbM.append(i2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
    }

    public final int cellZoneHeight(boolean z, boolean z2, boolean z3) {
        int i;
        int cellHeight;
        if (z2) {
            i = this.personalRowCount;
            cellHeight = this.baseCellSize.getCellWidth();
        } else if (!z) {
            i = this.noPersonalRowCountPort;
            cellHeight = this.baseCellSize.getCellHeight();
        } else if (z3) {
            i = this.noPersonalRowCountLandLeft;
            cellHeight = this.baseCellSize.getCellHeight();
        } else {
            i = this.noPersonalRowCountLandRight;
            cellHeight = this.baseCellSize.getCellHeight();
        }
        return cellHeight * i;
    }

    public final int cellZoneWidth() {
        return this.baseCellSize.getCellWidth() * 4;
    }

    public final int dpToPxWithInitialDensity(float f) {
        return Companion.dpToPxWithInitialDensity(f, this.context);
    }

    public final ICellAreaProvider getCellAreaProvider() {
        return this.cellAreaProvider;
    }

    public final CellSize getCellSize(boolean z) {
        CellSize cellSize = this.baseCellSize;
        return z ? cellSize.copy(cellSize.getCellWidth() * 2, this.baseCellSize.getCellWidth()) : cellSize;
    }

    public final List<SpanSize> getGridInfo(boolean z, boolean z2) {
        if (z2) {
            return CollectionsKt__CollectionsJVMKt.listOf(DataConvertorKt.mergeTwoCols(SpanCache.INSTANCE.spanSize(4, this.personalRowCount)));
        }
        if (!z) {
            return CollectionsKt__CollectionsJVMKt.listOf(SpanCache.INSTANCE.spanSize(4, this.noPersonalRowCountPort));
        }
        SpanCache spanCache = SpanCache.INSTANCE;
        return CollectionsKt__CollectionsKt.listOf(new SpanSize[]{spanCache.spanSize(4, this.noPersonalRowCountLandLeft), spanCache.spanSize(4, this.noPersonalRowCountLandRight)});
    }

    public final LayoutConstraint getLayoutConstraint(SpanSize spanSize, boolean z, boolean z2, boolean z3) {
        int cellWidth;
        SpanSize spanSizeRevertToTwoCols = z3 ? DataConvertorKt.revertToTwoCols(spanSize) : spanSize;
        LayoutConstraint layoutConstraint = this.layoutConstraints.get(spanSizeRevertToTwoCols);
        if (layoutConstraint != null) {
            return layoutConstraint;
        }
        int iDpToPxWithInitialDensity = dpToPxWithInitialDensity(62.0f);
        int iLockDpi = 0;
        if (!Intrinsics.areEqual(spanSizeRevertToTwoCols, SpanCache.ONE_X_ONE) && z2) {
            iLockDpi = Companion.lockDpi(this.context.getResources().getDimensionPixelSize(2131172186), this.context);
        }
        int i = iLockDpi;
        TextSizeConfig.Companion companion = TextSizeConfig.Companion;
        companion.getInstance(this.context).updateTwoXOneTextStyleInfo(this.twoXOneTextStyleInfo);
        int textLineHeight = companion.getInstance(this.context).getOneXOneConfig().getTextLineHeight();
        float f = iDpToPxWithInitialDensity;
        LayoutConstraint layoutConstraint2 = new LayoutConstraint(iDpToPxWithInitialDensity, iDpToPxWithInitialDensity, i, LOCALE_LANGUAGE_CN.equals(Locale.getDefault().getLanguage()) ? Companion.lockDpi(this.context, 2131172018) : ((this.baseCellSize.getCellHeight() - iDpToPxWithInitialDensity) - (textLineHeight * 2)) / 2, (int) companion.getInstance(this.context).getOneXOneConfig().getTextSize(), getLabelLines(), textLineHeight, f / dpToPxWithInitialDensity(62.0f), f / DeviceUtil.dp2px(this.context, 62.0f), this.twoXOneTextStyleInfo);
        if (spanSizeRevertToTwoCols.getColSpan() <= 2) {
            cellWidth = (this.baseCellSize.getCellWidth() * (spanSizeRevertToTwoCols.getColSpan() - 1)) + layoutConstraint2.getCapsuleWidth();
        } else {
            cellWidth = (this.baseCellSize.getCellWidth() * (spanSizeRevertToTwoCols.getColSpan() - 2)) + this.baseCellSize.getCellWidth() + layoutConstraint2.getCapsuleWidth();
        }
        LayoutConstraint layoutConstraint3 = new LayoutConstraint(cellWidth, (this.baseCellSize.getCellWidth() * (spanSizeRevertToTwoCols.getRowSpan() - 1)) + layoutConstraint2.getCapsuleHeight(), layoutConstraint2.getLayoutMarginTop(), layoutConstraint2.getLabelMarginTop(), layoutConstraint2.getLabelSize(), layoutConstraint2.getLabelLines(), layoutConstraint2.getLabelLineHeight(), layoutConstraint2.getInnerContentRatio(), layoutConstraint2.getInnerMarginRatio(), layoutConstraint2.getTwoXOneTextStyleInfo());
        this.layoutConstraints.put(spanSizeRevertToTwoCols, layoutConstraint3);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "getLayoutConstraint cache miss ,spanSizeInner:" + spanSizeRevertToTwoCols + ", innerMarginRatio:" + layoutConstraint3.getInnerMarginRatio(), null, 8);
        return layoutConstraint3;
    }

    public final int getNoPersonalRowCountLandLeft() {
        return this.noPersonalRowCountLandLeft;
    }

    public final int getNoPersonalRowCountLandRight() {
        return this.noPersonalRowCountLandRight;
    }

    public final int getNoPersonalRowCountPort() {
        return this.noPersonalRowCountPort;
    }

    public final int getPersonalRowCount() {
        return this.personalRowCount;
    }

    public final void onCellAreaUpdateFinish(boolean z) {
        CapsuleCalculateFactor capsuleCalculateFactor = new CapsuleCalculateFactor(this.context.getResources().getDisplayMetrics().density, z, StringsKt__StringsKt.trim(Locale.getDefault().getLanguage()).toString(), this.baseCellSize, Resources.getSystem().getConfiguration().fontScale, Locale.getDefault().getCountry());
        if (capsuleCalculateFactor.equals(this.lastCapsuleFactor)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "checkLayoutConstraintCache cache invalidation ,curCapsuleFactor:" + capsuleCalculateFactor + ", lastCapsuleFactor:" + this.lastCapsuleFactor, null, 8);
        this.layoutConstraints.clear();
        this.lastCapsuleFactor = capsuleCalculateFactor;
    }

    public final void onDynamicAllocateFinish(int i) {
        boolean zBooleanValue = ((Boolean) OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName().getValue()).booleanValue();
        this.cellMarginBottom = i;
        Context context = this.context;
        int iDpToPxWithInitialDensity = zBooleanValue ? Companion.dpToPxWithInitialDensity(88.0f, context) + this.cellMarginBottom : Companion.dpToPxWithInitialDensity(76.0f, context);
        int iDpToPxWithInitialDensity2 = Companion.dpToPxWithInitialDensity(76.0f, this.context);
        int i2 = this.cellMarginBottom;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("cellWidth: ", iDpToPxWithInitialDensity2, iDpToPxWithInitialDensity, ", cellHeight: ", ", showTimeName:");
        PromptIconState$.ExternalSyntheticOutline0.m(i2, ", cellMarginBottom:", ", cellHeight:", sbM, zBooleanValue);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(sbM, iDpToPxWithInitialDensity, iDpToPxWithInitialDensity2, ", cellWidth:"), null, 8);
        setBaseCellSize(new CellSize(iDpToPxWithInitialDensity2, iDpToPxWithInitialDensity));
    }

    public final void removeCellCalculatorListener(CellCalculatorListener cellCalculatorListener) {
        this.listeners.remove(cellCalculatorListener);
    }

    public final void setCellAreaProvider(ICellAreaProvider iCellAreaProvider) {
        this.cellAreaProvider = iCellAreaProvider;
    }

    public final void updateGridInfo(boolean z, boolean z2, boolean z3) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateGridInfo()-> ", ", isPersonalArea:", z, z2), null, 8);
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((CellCalculatorListener) it.next()).onGridInfoChange(z, z2, z3);
        }
    }

    public final void updatePersonalAreaRow(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "updatePersonalAreaRow: "), null, 8);
        setPersonalRowCount(i);
    }

    public static final int lockDpi(Context context, int i) {
        return Companion.lockDpi(context, i);
    }

    public static final int dpToPxWithInitialDensity(float f, Context context) {
        return Companion.dpToPxWithInitialDensity(f, context);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int dpToPxWithInitialDensity(float f, Context context) {
            OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
            return (int) (f * (proxy != null ? proxy.getInitialDisplayDensity() / 160.0f : context.getResources().getDisplayMetrics().density));
        }

        public final int getInitialDensity(Context context) {
            OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
            return (int) (proxy != null ? proxy.getInitialDisplayDensity() / 160.0f : context.getResources().getDisplayMetrics().density);
        }

        public final CellCalculatorManager getInstance(Context context) {
            CellCalculatorManager cellCalculatorManager;
            CellCalculatorManager cellCalculatorManager2 = CellCalculatorManager.instance;
            if (cellCalculatorManager2 != null) {
                return cellCalculatorManager2;
            }
            synchronized (this) {
                cellCalculatorManager = CellCalculatorManager.instance;
                if (cellCalculatorManager == null) {
                    cellCalculatorManager = new CellCalculatorManager(context.getApplicationContext());
                    CellCalculatorManager.instance = cellCalculatorManager;
                }
            }
            return cellCalculatorManager;
        }

        public final int lockDpi(float f, Context context) {
            OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
            return (int) ((f * (proxy != null ? proxy.getInitialDisplayDensity() / 160.0f : context.getResources().getDisplayMetrics().density)) / context.getResources().getDisplayMetrics().density);
        }

        public final int lockDpi(Context context, int i) {
            return lockDpi(context.getResources().getDimensionPixelSize(i), context);
        }
    }
}

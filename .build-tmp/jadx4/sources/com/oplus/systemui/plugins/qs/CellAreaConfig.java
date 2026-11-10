package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.customize.viewmodel.OplusSeparateTileNameState;
import com.oplus.systemui.plugins.qs.widget.SpaceAllocator;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.zoom.ui.tips.TipsManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CellAreaConfig {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CellAreaConfig";
    public int aEditHeight;
    public int aMainHeight;
    public int aWidthLand;
    public SpaceAllocator.DynamicZone aZone;
    public int bottonZoneWidthLand;
    public int buttonZoneHeight;
    public SpaceAllocator.DynamicZone c1Zone;
    public SpaceAllocator.DynamicZone c2Zone;
    public SpaceAllocator.DynamicZone c3Zone;
    public SpaceAllocator.DynamicZone c4Zone;
    public SpaceAllocator.DynamicZone c5Zone;
    private final CellAreaProvider cellAreaProvider;
    private final Context context;
    public int editABottomBorderMargin;
    public int editALeftBorderMargin;
    public int editATopBorderMargin;
    public int fHeight;
    public int gHeight;
    public int height;
    public int holeBottom;
    private Rect holeRect;
    public int indicatorHeight;
    public boolean isCenterHole;
    public boolean isEditMode;
    public boolean isLeftHole;
    public boolean isRightHole;
    public int mediumScreenMaxHeight;
    public int minMargin;
    public int personalBottomMinHeight;
    private OplusSeparateQSPlugin.IStub proxy;
    public int shortScreenMaxHeight;
    public int shortScreenMaxHeightWhenEdit;
    private final SpaceAllocator spaceAllocator = new SpaceAllocator();
    public int statusBarHeight;
    public int statusBarRealHeight;
    public int width;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CellAreaConfig(Context context, CellAreaProvider cellAreaProvider) {
        this.context = context;
        this.cellAreaProvider = cellAreaProvider;
    }

    public final void bindStub(OplusSeparateQSPlugin.IStub iStub) {
        this.proxy = iStub;
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        int aHeight = getAHeight();
        int bHeight = getBHeight();
        int i = this.statusBarHeight;
        int i2 = this.buttonZoneHeight;
        int i3 = this.editATopBorderMargin;
        int i4 = this.editABottomBorderMargin;
        int c1Height = getC1Height();
        int c2Height = getC2Height();
        int c3Height = getC3Height();
        int c4Height = getC4Height();
        int c5Height = getC5Height();
        int i5 = this.indicatorHeight;
        int i6 = this.gHeight;
        int i7 = this.minMargin;
        int i8 = this.width;
        int i9 = this.height;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("CellAreaConfig aHeight=", aHeight, bHeight, ", bHeight=", ", statusBarHeight=");
        RectList$.ExternalSyntheticOutline0.m(i, i2, ", buttonZoneHeight=", ", editATopBorderMargin=", sbM);
        RectList$.ExternalSyntheticOutline0.m(i3, i4, ", editABottomBorderMargin=", ", c1Height=", sbM);
        RectList$.ExternalSyntheticOutline0.m(c1Height, c2Height, ", c2Height=", ", c3Height=", sbM);
        RectList$.ExternalSyntheticOutline0.m(c3Height, c4Height, ", c4Height=", ", c5Height=", sbM);
        RectList$.ExternalSyntheticOutline0.m(c5Height, i5, ", indicatorHeight=", ", gHeight=", sbM);
        RectList$.ExternalSyntheticOutline0.m(i6, i7, ", minMargin=", ", width=", sbM);
        sbM.append(i8);
        sbM.append(", height=");
        sbM.append(i9);
        printWriter.println(sbM.toString());
        this.spaceAllocator.dump(printWriter, strArr);
    }

    public final int getACurH(boolean z, boolean z2) {
        int rotation = this.context.getDisplay().getRotation();
        if (z2) {
            return 0;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() && this.isCenterHole && rotation == 0) {
            return CellCalculatorManager.Companion.lockDpi(this.context, 2131172071) + this.holeBottom;
        }
        if (this.height >= this.shortScreenMaxHeight) {
            return CellCalculatorManager.Companion.lockDpi(this.context, 2131172068);
        }
        int i = this.holeBottom;
        if (i > 0 && !z) {
            return CellCalculatorManager.Companion.lockDpi(this.context, 2131172067) + i;
        }
        Context context = this.context;
        return z ? CellCalculatorManager.Companion.lockDpi(context, 2131172067) : CellCalculatorManager.Companion.lockDpi(context, 2131172068);
    }

    public final int getAEditHeight() {
        return this.aEditHeight;
    }

    public final int getAHeight() {
        SpaceAllocator.DynamicZone dynamicZone = this.aZone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH();
    }

    public final int getAMainHeight() {
        return this.aMainHeight;
    }

    public final int getAPersonalHeight() {
        return this.editATopBorderMargin + this.editABottomBorderMargin + CellCalculatorManager.Companion.getInstance(this.context).cellZoneHeight(false, true, true);
    }

    public final int getAWidthLand() {
        int i = this.aWidthLand;
        if (i != 0) {
            return i;
        }
        if (!((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) {
            return this.aWidthLand;
        }
        int aCurH = getACurH(false, false);
        if (aCurH != 0) {
            this.aWidthLand = aCurH;
        }
        return this.aWidthLand;
    }

    public final int getBHeight() {
        int i = this.statusBarHeight + this.buttonZoneHeight;
        SpaceAllocator.DynamicZone dynamicZone = this.c5Zone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH() + i;
    }

    public final int getBOtherHeight() {
        return CellCalculatorManager.Companion.getInstance(this.context).cellZoneHeight(false, false, true);
    }

    public final int getBottomHeight() {
        return getC1Height() + this.gHeight;
    }

    public final int getBottonZoneWidthLand() {
        return this.bottonZoneWidthLand;
    }

    public final int getButtonZoneHeight() {
        return this.buttonZoneHeight;
    }

    public final int getC1Height() {
        SpaceAllocator.DynamicZone dynamicZone = this.c1Zone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH();
    }

    public final int getC2Height() {
        SpaceAllocator.DynamicZone dynamicZone = this.c2Zone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH();
    }

    public final int getC3Height() {
        SpaceAllocator.DynamicZone dynamicZone = this.c3Zone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH();
    }

    public final int getC4Height() {
        SpaceAllocator.DynamicZone dynamicZone = this.c4Zone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH();
    }

    public final int getC5Height() {
        SpaceAllocator.DynamicZone dynamicZone = this.c5Zone;
        if (dynamicZone == null) {
            dynamicZone = null;
        }
        return dynamicZone.getCurH();
    }

    public final int getEditABottomBorderMargin() {
        return this.editABottomBorderMargin;
    }

    public final int getEditALeftBorderMargin() {
        return this.editALeftBorderMargin;
    }

    public final int getEditATopBorderMargin() {
        return this.editATopBorderMargin;
    }

    public final int getFHeight() {
        return this.fHeight;
    }

    public final int getGHeight() {
        return this.gHeight;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getHoleBottom() {
        return this.holeBottom;
    }

    public final int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public final int getMinMargin() {
        return this.minMargin;
    }

    public final int getPersonalBottomMinHeight() {
        return this.personalBottomMinHeight;
    }

    public final int getStatusBarHeight() {
        return this.statusBarHeight;
    }

    public final int getStatusBarRealHeight() {
        return this.statusBarRealHeight;
    }

    public final int getWidth() {
        return this.width;
    }

    public final Point personalCellPortLoc(int i, int i2) {
        CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
        SpanSize spanSize = companion.getInstance(this.context).getGridInfo(false, true).get(0);
        CellCalculatorManager.CellSize cellSize = companion.getInstance(this.context).getCellSize(true);
        if (i2 < 0 || i2 > spanSize.getRowSpan() - 1 || i < 0 || i > spanSize.getColSpan() - 1) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, i, "personalCellPortLoc()-> wrong param row=", ", col="));
            return new Point(0, 0);
        }
        int c2Height = getC2Height() + getC5Height() + getAHeight() + this.statusBarHeight + this.buttonZoneHeight + this.editATopBorderMargin;
        return new Point((cellSize.getCellWidth() * i) + this.minMargin, (cellSize.getCellHeight() * i2) + c2Height);
    }

    public final void setAWidthLand(int i) {
        this.aWidthLand = i;
    }

    public final void setBottonZoneWidthLand(int i) {
        this.bottonZoneWidthLand = i;
    }

    public final void setEditMode(boolean z) {
        this.isEditMode = z;
    }

    public final void setPersonalBottomMinHeight(int i) {
        this.personalBottomMinHeight = i;
    }

    public final void setStatusBarRealHeight(int i) {
        this.statusBarRealHeight = i;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0176  */
    public final void update() {
        int iLockDpi;
        int iLockDpi2;
        int i;
        Rect rect;
        Rect rect2;
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) companion.getInstance().isLargeScreen().getValue()).booleanValue();
        boolean zBooleanValue3 = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        this.width = ((Number) companion.getInstance().getScreenWidth().getValue()).intValue();
        this.height = ((Number) companion.getInstance().getScreenHeight().getValue()).intValue();
        OplusSeparateQSPlugin.IStub iStub = this.proxy;
        if (iStub != null) {
            this.isRightHole = iStub.isRightHole();
            this.isLeftHole = iStub.isLeftHole();
            this.isCenterHole = iStub.isCenterHole();
            Rect screenHoleRect = iStub.getScreenHoleRect(this.context);
            int i2 = screenHoleRect.bottom;
            if (i2 > 0) {
                this.holeBottom = i2;
            }
            this.holeRect = screenHoleRect;
            boolean z = this.isRightHole;
            boolean z2 = this.isLeftHole;
            boolean z3 = this.isCenterHole;
            int i3 = this.holeBottom;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("initHoleInfo()-> isRightHole:", ", isLeftHole:", ", isCenterHole:", z, z2);
            sbM.append(z3);
            sbM.append(", holdRect:");
            sbM.append(screenHoleRect);
            sbM.append(", holeBottom:");
            sbM.append(i3);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        }
        CellCalculatorManager.Companion companion2 = CellCalculatorManager.Companion;
        int iLockDpi3 = companion2.lockDpi(this.context, 2131172066);
        this.statusBarRealHeight = iLockDpi3;
        boolean z4 = this.isEditMode;
        if (z4) {
            iLockDpi3 = 0;
        }
        this.statusBarHeight = iLockDpi3;
        Context context = this.context;
        this.buttonZoneHeight = z4 ? companion2.lockDpi(context, 2131172031) : companion2.lockDpi(context, 2131172062);
        if (zBooleanValue3) {
            this.bottonZoneWidthLand = companion2.lockDpi(this.context, 2131172065);
        }
        this.indicatorHeight = companion2.lockDpi(this.context, 2131172023);
        boolean zBooleanValue4 = ((Boolean) companion.getInstance().isGestureNavigation().getValue()).booleanValue();
        Context context2 = this.context;
        this.gHeight = zBooleanValue4 ? companion2.lockDpi(context2, 2131172044) : companion2.lockDpi(context2, 2131172073);
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131172032);
        if (!zBooleanValue3) {
            dimensionPixelSize = companion2.lockDpi(dimensionPixelSize, this.context);
        }
        this.fHeight = dimensionPixelSize;
        boolean zBooleanValue5 = ((Boolean) companion.getInstance().isLargeScreen().getValue()).booleanValue();
        Context context3 = this.context;
        if (zBooleanValue5) {
            boolean z5 = ListPopupWindow$.ExternalSyntheticOutline0.m(context3) == 1;
            int rotation = this.context.getDisplay().getRotation();
            if (this.isRightHole) {
                if (rotation == 0 && !z5) {
                    int i4 = this.width;
                    Rect rect3 = this.holeRect;
                    iLockDpi = i4 - (rect3 != null ? rect3.left : i4);
                } else if (rotation == 1 && z5 && (rect2 = this.holeRect) != null) {
                    iLockDpi = rect2.bottom;
                } else {
                    iLockDpi = 0;
                }
            } else if (!this.isLeftHole) {
                iLockDpi = 0;
            } else if (rotation == 0 && z5) {
                Rect rect4 = this.holeRect;
                if (rect4 != null) {
                    iLockDpi = rect4.right;
                } else {
                    iLockDpi = 0;
                }
            } else if (rotation != 3 || z5 || (rect = this.holeRect) == null) {
                iLockDpi = 0;
            } else {
                iLockDpi = rect.bottom;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iLockDpi, "marginAdjustHole()-> holeMargin="), null, 8);
            int iLockDpi4 = companion2.lockDpi(this.context, 2131172048);
            if (iLockDpi < iLockDpi4) {
                iLockDpi = iLockDpi4;
            }
            int iLockDpi5 = companion2.lockDpi(this.context, 2131172046);
            if (iLockDpi > iLockDpi5) {
                iLockDpi = iLockDpi5;
            }
        } else {
            iLockDpi = companion2.lockDpi(context3.getResources().getDimensionPixelSize(2131172048), this.context);
        }
        this.minMargin = iLockDpi;
        this.editATopBorderMargin = this.isEditMode ? companion2.lockDpi(this.context, 2131172038) : 0;
        this.editABottomBorderMargin = this.isEditMode ? companion2.lockDpi(this.context, 2131172036) : 0;
        this.editALeftBorderMargin = companion2.lockDpi(this.context, 2131172037);
        this.personalBottomMinHeight = companion2.lockDpi(this.context, 2131172047);
        this.shortScreenMaxHeight = companion2.lockDpi(this.context, 2131172041);
        this.shortScreenMaxHeightWhenEdit = companion2.lockDpi(this.context, 2131172040);
        int iLockDpi6 = companion2.lockDpi(this.context, 2131172039);
        this.mediumScreenMaxHeight = iLockDpi6;
        int i5 = this.width;
        int i6 = this.height;
        int i7 = this.statusBarHeight;
        int i8 = this.buttonZoneHeight;
        int i9 = this.indicatorHeight;
        int i10 = this.gHeight;
        int i11 = this.fHeight;
        int i12 = this.shortScreenMaxHeight;
        StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("initStaticZone()-> width:", i5, i6, ",  height:", ", statusBarHeight:");
        RectList$.ExternalSyntheticOutline0.m(i7, i8, ", settingMoreHeight:", ", indicatorHeight:", sbM2);
        RectList$.ExternalSyntheticOutline0.m(i9, i10, ", gHeight:", ", fHeight:", sbM2);
        RectList$.ExternalSyntheticOutline0.m(i11, i12, ", shortScreenMaxHeight:", ", mediumScreenMaxHeight:", sbM2);
        sbM2.append(iLockDpi6);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM2.toString(), null, 8);
        this.aZone = new SpaceAllocator.DynamicZone("A", companion2.lockDpi(this.context, 2131172069), companion2.lockDpi(this.context, 2131172068), 1, 0);
        this.c1Zone = new SpaceAllocator.DynamicZone("C1", companion2.lockDpi(this.context, 2131172033), TipsManager.DELAY_AUTO_HIDE_TIME, 1, 4);
        this.c2Zone = new SpaceAllocator.DynamicZone("C2", companion2.lockDpi(this.context, 2131172055), companion2.lockDpi(this.context, 2131172054), 1, 2);
        this.c3Zone = new SpaceAllocator.DynamicZone("C3", 0, companion2.lockDpi(this.context, 2131172035), 1, 1);
        this.c4Zone = new SpaceAllocator.DynamicZone("C4", 0, companion2.lockDpi(this.context, 2131172050), 1, 3);
        SpaceAllocator.DynamicZone dynamicZone = new SpaceAllocator.DynamicZone("C5", companion2.lockDpi(this.context, 2131172064), companion2.lockDpi(this.context, 2131172063), 1, 2);
        this.c5Zone = dynamicZone;
        SpaceAllocator spaceAllocator = this.spaceAllocator;
        SpaceAllocator.DynamicZone dynamicZone2 = this.aZone;
        SpaceAllocator.DynamicZone dynamicZone3 = dynamicZone2 == null ? null : dynamicZone2;
        SpaceAllocator.DynamicZone dynamicZone4 = this.c3Zone;
        SpaceAllocator.DynamicZone dynamicZone5 = dynamicZone4 == null ? null : dynamicZone4;
        SpaceAllocator.DynamicZone dynamicZone6 = this.c2Zone;
        SpaceAllocator.DynamicZone dynamicZone7 = dynamicZone6 == null ? null : dynamicZone6;
        SpaceAllocator.DynamicZone dynamicZone8 = this.c4Zone;
        SpaceAllocator.DynamicZone dynamicZone9 = dynamicZone8 == null ? null : dynamicZone8;
        SpaceAllocator.DynamicZone dynamicZone10 = this.c1Zone;
        spaceAllocator.setZoneList(CollectionsKt__CollectionsKt.arrayListOf(new SpaceAllocator.DynamicZone[]{dynamicZone3, dynamicZone5, dynamicZone, dynamicZone7, dynamicZone9, dynamicZone10 == null ? null : dynamicZone10}));
        this.spaceAllocator.reset();
        boolean zBooleanValue6 = ((Boolean) OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName().getValue()).booleanValue();
        boolean zBooleanValue7 = ((Boolean) companion.getInstance().isLargeScreen().getValue()).booleanValue();
        SpaceAllocator.DynamicZone dynamicZone11 = this.aZone;
        if (dynamicZone11 == null) {
            dynamicZone11 = null;
        }
        dynamicZone11.setFixed(true);
        SpaceAllocator.DynamicZone dynamicZone12 = this.aZone;
        if (dynamicZone12 == null) {
            dynamicZone12 = null;
        }
        dynamicZone12.setCurH(getACurH(zBooleanValue7, zBooleanValue));
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null) {
            SpaceAllocator.DynamicZone dynamicZone13 = this.aZone;
            if (dynamicZone13 == null) {
                dynamicZone13 = null;
            }
            proxy.onAZoneHeightChanged(dynamicZone13.getCurH());
        }
        if (this.isEditMode) {
            this.aEditHeight = getAHeight();
        } else {
            this.aMainHeight = getAHeight();
        }
        SpaceAllocator.DynamicZone dynamicZone14 = this.c1Zone;
        if (dynamicZone14 == null) {
            dynamicZone14 = null;
        }
        if (zBooleanValue) {
            iLockDpi2 = companion2.lockDpi(this.context, 2131172047);
        } else {
            boolean z6 = this.isEditMode;
            Context context4 = this.context;
            iLockDpi2 = z6 ? companion2.lockDpi(context4, 2131172034) : companion2.lockDpi(context4, 2131172033);
        }
        dynamicZone14.setMinH(iLockDpi2);
        QsColorUtil.INSTANCE.getClass();
        if (QsColorUtil.isSuperPowerSaveState) {
            SpaceAllocator.DynamicZone dynamicZone15 = this.c2Zone;
            if (dynamicZone15 == null) {
                dynamicZone15 = null;
            }
            dynamicZone15.setFixed(true);
            SpaceAllocator.DynamicZone dynamicZone16 = this.c2Zone;
            if (dynamicZone16 == null) {
                dynamicZone16 = null;
            }
            dynamicZone16.setCurH(this.context.getResources().getDimensionPixelSize(2131172165));
        } else if (zBooleanValue) {
            SpaceAllocator.DynamicZone dynamicZone17 = this.c2Zone;
            if (dynamicZone17 == null) {
                dynamicZone17 = null;
            }
            dynamicZone17.setFixed(true);
            SpaceAllocator.DynamicZone dynamicZone18 = this.c2Zone;
            if (dynamicZone18 == null) {
                dynamicZone18 = null;
            }
            dynamicZone18.setCurH(0);
        } else if (this.isEditMode) {
            int i13 = this.height;
            int i14 = this.mediumScreenMaxHeight;
            if (i13 > i14) {
                SpaceAllocator.DynamicZone dynamicZone19 = this.c2Zone;
                if (dynamicZone19 == null) {
                    dynamicZone19 = null;
                }
                dynamicZone19.setFixed(true);
                SpaceAllocator.DynamicZone dynamicZone20 = this.c2Zone;
                if (dynamicZone20 == null) {
                    dynamicZone20 = null;
                }
                dynamicZone20.setCurH(companion2.lockDpi(this.context, 2131172054));
            } else if (this.shortScreenMaxHeightWhenEdit > i13 || i13 > i14) {
                SpaceAllocator.DynamicZone dynamicZone21 = this.c2Zone;
                if (dynamicZone21 == null) {
                    dynamicZone21 = null;
                }
                dynamicZone21.setMinH(companion2.lockDpi(this.context, 2131172057));
            } else {
                SpaceAllocator.DynamicZone dynamicZone22 = this.c2Zone;
                if (dynamicZone22 == null) {
                    dynamicZone22 = null;
                }
                dynamicZone22.setMinH(companion2.lockDpi(this.context, 2131172056));
            }
        } else {
            SpaceAllocator.DynamicZone dynamicZone23 = this.c2Zone;
            if (dynamicZone23 == null) {
                dynamicZone23 = null;
            }
            int i15 = this.height;
            int i16 = this.mediumScreenMaxHeight;
            Context context5 = this.context;
            dynamicZone23.setMinH(i15 > i16 ? companion2.lockDpi(context5, 2131172058) : companion2.lockDpi(context5, 2131172059));
        }
        SpaceAllocator.DynamicZone dynamicZone24 = this.c3Zone;
        if (dynamicZone24 == null) {
            dynamicZone24 = null;
        }
        dynamicZone24.setFixed(!zBooleanValue6);
        if (!zBooleanValue6) {
            SpaceAllocator.DynamicZone dynamicZone25 = this.c3Zone;
            if (dynamicZone25 == null) {
                dynamicZone25 = null;
            }
            dynamicZone25.setCurH(0);
        }
        SpaceAllocator.DynamicZone dynamicZone26 = this.c4Zone;
        if (dynamicZone26 == null) {
            dynamicZone26 = null;
        }
        dynamicZone26.setFixed(this.isEditMode);
        if (this.isEditMode) {
            SpaceAllocator.DynamicZone dynamicZone27 = this.c4Zone;
            if (dynamicZone27 == null) {
                dynamicZone27 = null;
            }
            dynamicZone27.setCurH(companion2.lockDpi(this.context, 2131172049));
        }
        if (this.isEditMode || zBooleanValue) {
            SpaceAllocator.DynamicZone dynamicZone28 = this.c5Zone;
            if (dynamicZone28 == null) {
                dynamicZone28 = null;
            }
            dynamicZone28.setFixed(true);
            SpaceAllocator.DynamicZone dynamicZone29 = this.c5Zone;
            if (dynamicZone29 == null) {
                dynamicZone29 = null;
            }
            i = 0;
            dynamicZone29.setCurH(0);
        } else {
            SpaceAllocator.DynamicZone dynamicZone30 = this.c2Zone;
            if (dynamicZone30 == null) {
                dynamicZone30 = null;
            }
            int minH = dynamicZone30.getMinH();
            SpaceAllocator.DynamicZone dynamicZone31 = this.c2Zone;
            if (dynamicZone31 == null) {
                dynamicZone31 = null;
            }
            int maxH = dynamicZone31.getMaxH();
            int iLockDpi7 = companion2.lockDpi(this.context, 2131172070);
            SpaceAllocator.DynamicZone dynamicZone32 = this.c5Zone;
            if (dynamicZone32 == null) {
                dynamicZone32 = null;
            }
            dynamicZone32.setMinH(minH + iLockDpi7);
            SpaceAllocator.DynamicZone dynamicZone33 = this.c5Zone;
            if (dynamicZone33 == null) {
                dynamicZone33 = null;
            }
            dynamicZone33.setMaxH(maxH + iLockDpi7);
            SpaceAllocator.DynamicZone dynamicZone34 = this.c2Zone;
            if (dynamicZone34 == null) {
                dynamicZone34 = null;
            }
            dynamicZone34.setMinH(minH - iLockDpi7);
            SpaceAllocator.DynamicZone dynamicZone35 = this.c2Zone;
            if (dynamicZone35 == null) {
                dynamicZone35 = null;
            }
            dynamicZone35.setMaxH(maxH - iLockDpi7);
            i = 0;
        }
        boolean zBooleanValue8 = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        int iAllocateCellSpace = companion2.getInstance(this.context).allocateCellSpace((((((this.height - this.statusBarHeight) - this.buttonZoneHeight) - (zBooleanValue8 ? i : this.indicatorHeight)) - (zBooleanValue8 ? i : this.gHeight)) - this.editATopBorderMargin) - this.spaceAllocator.minTotalHeight(), zBooleanValue);
        SpaceAllocator.DynamicZone dynamicZone36 = this.c3Zone;
        if (dynamicZone36 == null) {
            dynamicZone36 = null;
        }
        dynamicZone36.setCount(companion2.getInstance(this.context).getNoPersonalRowCountPort());
        this.spaceAllocator.allocateSpace(iAllocateCellSpace);
        CellCalculatorManager companion3 = companion2.getInstance(this.context);
        SpaceAllocator.DynamicZone dynamicZone37 = this.c3Zone;
        if (dynamicZone37 == null) {
            dynamicZone37 = null;
        }
        companion3.onDynamicAllocateFinish(dynamicZone37.getCurH());
        if (zBooleanValue) {
            companion2.getInstance(this.context).allocateCellSpaceForLandRight(((this.height - this.statusBarHeight) - this.indicatorHeight) - this.gHeight);
        } else if (!zBooleanValue2) {
            this.aWidthLand = getAHeight();
        }
        companion2.getInstance(this.context).onCellAreaUpdateFinish(this.isEditMode);
        this.cellAreaProvider.notifyConfigUpdate();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "update() isLandSplit=" + zBooleanValue + ", isLargeScreen=" + zBooleanValue2, null, 8);
    }
}

package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.res.ColorStateList;
import android.util.Log;
import android.widget.ImageView;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSThreeStageLayout$$ExternalSyntheticLambda1 implements Function2 {
    public /* synthetic */ OplusQSThreeStageLayout f$0;

    public final Object invoke(Object obj, Object obj2) {
        OplusQSThreeStageLayout oplusQSThreeStageLayout = this.f$0;
        ImageView imageView = (ImageView) obj;
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        OplusQSThreeStageLayout.Companion companion = OplusQSThreeStageLayout.Companion;
        int activeIconColor = oplusQSThreeStageLayout.getActiveIconColor(zBooleanValue);
        ColorStateList imageTintList = imageView.getImageTintList();
        if (imageTintList == null || imageTintList.getDefaultColor() != activeIconColor) {
            imageView.setImageTintList(ColorStateList.valueOf(activeIconColor));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("handleAnimationEnd isSelected:", " tintColor:", Integer.toHexString(activeIconColor), zBooleanValue, " view:");
                sbM.append(imageView);
                sbM.append("]}");
                Log.d("SystemUi--QuickSettings", "OplusQSThreeStageLayout-->".concat(sbM.toString()));
            }
        }
        return Unit.INSTANCE;
    }
}

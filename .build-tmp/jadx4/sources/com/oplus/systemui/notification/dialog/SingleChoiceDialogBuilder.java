package com.oplus.systemui.notification.dialog;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleChoiceDialogBuilder extends COUIAlertDialogBuilder {
    public static final Companion Companion = new Companion();
    private static final String DEFAULT_STRING_INPUT = "%s";
    private String appName;
    private final Context context;

    public SingleChoiceDialogBuilder(Context context, int i) {
        super(context, i);
        this.context = context;
    }

    public final void setAppName(String str) {
        this.appName = str;
    }

    public final SingleChoiceDialogBuilder setSingleChoiceItemsWithLink(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, Function1 function1, Function0 function0) {
        ArrayList arrayList = new ArrayList(charSequenceArr2.length);
        for (CharSequence charSequence : charSequenceArr2) {
            showNotificationLink.1.linkInfo.1 r6 = new showNotificationLink.1.linkInfo.1();
            r6.$onLinkClick = function0;
            VarHandle.storeStoreFence();
            Pair pairLinkify = COUIAnnotationLinkSpan.Companion.linkify(this.context, charSequence, new COUIAnnotationLinkSpan.LinkInfo[]{new COUIAnnotationLinkSpan.LinkInfo("link", r6)});
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) pairLinkify.getFirst();
            String str = this.appName;
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default(spannableStringBuilder, DEFAULT_STRING_INPUT, 0, false, 6);
            if (iIndexOf$default != -1) {
                if (str == null) {
                    str = "";
                }
                spannableStringBuilder.replace(iIndexOf$default, iIndexOf$default + 2, (CharSequence) new SpannableString(str));
            }
            arrayList.add(TuplesKt.to(spannableStringBuilder, pairLinkify.getSecond()));
        }
        Pair[] pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        Context context = this.context;
        ArrayList arrayList2 = new ArrayList(pairArr.length);
        for (Pair pair : pairArr) {
            arrayList2.add((CharSequence) pair.getFirst());
        }
        createChoiceAdapter.1 r3 = new createChoiceAdapter.1(charSequenceArr, pairArr, context, (CharSequence[]) arrayList2.toArray(new CharSequence[0]), new boolean[]{true, false});
        setSingleChoiceItemsWithLink.1 r10 = new setSingleChoiceItemsWithLink.1();
        r10.$onItemClick = function1;
        VarHandle.storeStoreFence();
        setSingleChoiceItems(r3, 0, r10);
        return this;
    }
}

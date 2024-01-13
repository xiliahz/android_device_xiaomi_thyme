package org.lineageos.settings.system;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.system.Os;
import android.view.WindowManager;

import org.lineageos.settings.R;

public class KernelCheck {
    private static final String KERNEL_INFO_NODE = "/proc/version";
    private static final String LYNNRIN_KERNEL_KEYWORD = "Margatroid";

    public static void check(Context context) {
        if(!Os.uname().release.contains(LYNNRIN_KERNEL_KEYWORD))
            showDialog(context);
    }

    private static void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.kernel_warning);
        builder.setMessage(R.string.kernel_warning_info);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
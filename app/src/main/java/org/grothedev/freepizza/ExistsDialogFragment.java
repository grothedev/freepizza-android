package org.grothedev.freepizza;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by thomas on 08/08/17.
 */

public class ExistsDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        Bundle args = getArguments();
        final int siteId = args.getInt("siteId");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_exists_prompt)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Vote v = new Vote(siteId, true);
                        new VoteTask().execute(v);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Vote v = new Vote(siteId, false);
                        new VoteTask().execute(v);
                    }
                });
        return builder.create();

    }

    public static ExistsDialogFragment newInstance(int siteId){
        ExistsDialogFragment frag = new ExistsDialogFragment();

        Bundle args = new Bundle();
        args.putInt("siteId", siteId);

        frag.setArguments(args);
        return frag;
    }
}

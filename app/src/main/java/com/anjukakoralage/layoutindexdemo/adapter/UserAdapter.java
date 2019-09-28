package com.anjukakoralage.layoutindexdemo.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.anjukakoralage.layoutindexdemo.R;
import com.anjukakoralage.layoutindexdemo.model.UserDetails;
import com.anjukakoralage.layoutindexdemo.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by anjukakoralage on 26,September,2019
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<UserDetails> userDetails;
    private Context context;
    private String id, fName,lName, email, image;
    private Dialog dialog;
    PopupWindow popUp;
    LinearLayout mainL;


    public UserAdapter(Context context, ArrayList<UserDetails> retroModelArrayList) {

        this.userDetails = retroModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int i) {

        id = userDetails.get(i).getId();
        fName = userDetails.get(i).getFirst_name();
        lName = userDetails.get(i).getLast_name();
        email = userDetails.get(i).getEmail();
        image = userDetails.get(i).getAvatar();

        viewHolder.tvID.setText("000" + id);
        viewHolder.tvName.setText(fName + " " + lName);

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = userDetails.get(i).getId();
                fName = userDetails.get(i).getFirst_name();
                lName = userDetails.get(i).getLast_name();
                email = userDetails.get(i).getEmail();
                image = userDetails.get(i).getAvatar();

                loadDialogView(id, fName, lName, email, image);
                dialog.show();

            }
        });

    }

    private void loadDialogView(String id, String lName, String fName, String email, String image) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        TextView firstName = (TextView) dialog.findViewById(R.id.tvFName);
        TextView secondName = (TextView) dialog.findViewById(R.id.tvLName);
        TextView emailA = (TextView) dialog.findViewById(R.id.tvEmail);
        CircleImageView avatarImage = (CircleImageView) dialog.findViewById(R.id.profile_image);

        firstName.setText(fName);
        secondName.setText(lName);
        emailA.setText(email);
        Picasso.get().load(image).into(avatarImage);




    }


    @Override
    public int getItemCount() {
        return userDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvID;
        public TextView tvName;
        public LinearLayout llRow;
        public CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            llRow = itemView.findViewById(R.id.llRow);
            card_view = itemView.findViewById(R.id.card_view);
        }

    }
}


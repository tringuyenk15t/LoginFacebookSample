package com.app.tringuyen.loginfacebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

/**
 * Created by Tri Nguyen on 8/17/2016.
 */
public class AfterLoginFragment extends Fragment{
    private Profile profile = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_after_login,container,false);
        TextView text  = (TextView) v.findViewById(R.id.name);
        ImageView image = (ImageView) v.findViewById(R.id.image);

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            profile = (Profile) bundle.getParcelable(LoginFacebookFragment.PARCEL_KEY);
        }
        else
        {
            profile = Profile.getCurrentProfile();
        }

        String name = profile.getName();
        String url =  "https://graph.facebook.com/" +  profile.getId() + "/picture?type=large";
        Glide
            .with(getContext())
            .load(url)
            .centerCrop()
            .into(image);

        text.setText(name);

        Button btn_logout = (Button) v.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
            }
        });

        return v;
    }
}

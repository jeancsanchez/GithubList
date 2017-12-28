package com.github.jeancarlos.githublist.features.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.domain.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;

/**
 * This class represents an user adapter.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers = emptyList();
    private ArrayList<User> mCachedUsers = new ArrayList<>();
    private OnItemClickedListener<String> mListener;

    public UserAdapter(Context context) {
        mContext = context;
    }

    /**
     * Sets the item click listener
     *
     * @param listener The click listener.
     */
    public void setOnClickListener(OnItemClickedListener<String> listener) {
        mListener = listener;
    }

    /**
     * Initializes the mUsers list.
     *
     * @param users the mUsers list.
     */
    public void setUpItems(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    /**
     * Adds an mUsers list to the existent mUsers list.
     *
     * @param users The new mUsers list.
     */
    public void addItems(List<User> users) {
        if (!mUsers.isEmpty()) {
            int beforeAddIndex = users.size() - 1;
            mUsers.addAll(users);
            notifyItemRangeChanged(beforeAddIndex, users.size() - 1);
        } else {
            mUsers.addAll(users);
            notifyDataSetChanged();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);

        Picasso.with(mContext)
                .load(user.getAvatarUrl())
                .placeholder(R.drawable.ic_github_default_black)
                .into(holder.imgItemUserPic);

        if (!user.getName().isEmpty()) {
            holder.txtUserName.setText(user.getName());
        } else {
            holder.txtUserName.setText(user.getLogin());
        }

        holder.txtItemUserNick.setText(mContext.getString(R.string.item_user_nick, user.getLogin()));

        if (mListener != null) {
            holder.itemView.setOnClickListener(view -> mListener.onClicked(user.getLogin()));
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    /**
     * Caches the current items
     */
    public void cacheCurrentItems() {
        mCachedUsers.addAll(mUsers);
        mUsers.clear();
    }

    /**
     * Loads the cached items.
     */
    public void loadCachedItems() {
        mUsers.clear();
        mUsers.addAll(mCachedUsers);
        mCachedUsers.clear();
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgItemUserPicture)
        ImageView imgItemUserPic;

        @BindView(R.id.txtItemUserName)
        TextView txtUserName;

        @BindView(R.id.txtItemUserNick)
        TextView txtItemUserNick;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

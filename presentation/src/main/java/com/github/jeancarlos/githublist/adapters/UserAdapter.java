package com.github.jeancarlos.githublist.adapters;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> users = emptyList();

    public UserAdapter(Context context) {
        this.context = context;
    }

    /**
     * Initializes the users list.
     *
     * @param users the users list.
     */
    public void setUpItems(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    /**
     * Adds an users list to the existent users list.
     *
     * @param users The new users list.
     */
    public void addItems(List<User> users) {
        if (!this.users.isEmpty()) {
            int beforeAddIndex = users.size() - 1;
            this.users.addAll(users);
            notifyItemRangeChanged(beforeAddIndex, users.size() - 1);
        } else {
            this.users.addAll(users);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);

        Picasso.with(context)
                .load(user.getAvatarUrl())
                .placeholder(R.drawable.ic_github_default_black)
                .into(holder.imgItemUserPic);

        if (!user.getName().isEmpty()) {
            holder.txtUserName.setText(user.getName());
        } else {
            holder.txtUserName.setText(user.getLogin());
        }

        holder.txtItemUserNick.setText(context.getString(R.string.item_user_nick, user.getLogin()));
    }

    @Override
    public int getItemCount() {
        return users.size();
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

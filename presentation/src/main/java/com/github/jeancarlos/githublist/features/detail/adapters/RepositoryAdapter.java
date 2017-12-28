package com.github.jeancarlos.githublist.features.detail.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;

/**
 * This class represents a Repository adapter.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private Context mContext;
    private List<GithubRepo> mRepositories = emptyList();

    public RepositoryAdapter(Context context) {
        mContext = context;
    }

    /**
     * Initializes the mRepositories list.
     *
     * @param users the mRepositories list.
     */
    public void setUpItems(List<GithubRepo> users) {
        mRepositories = users;
        notifyDataSetChanged();
    }

    /**
     * Adds an mRepositories list to the existent mRepositories list.
     *
     * @param users The new mRepositories list.
     */
    public void addItems(List<GithubRepo> users) {
        if (!mRepositories.isEmpty()) {
            int beforeAddIndex = users.size() - 1;
            mRepositories.addAll(users);
            notifyItemRangeChanged(beforeAddIndex, users.size() - 1);
        } else {
            mRepositories.addAll(users);
            notifyDataSetChanged();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubRepo githubRepo = mRepositories.get(position);

        holder.txtRepoTitle.setText(githubRepo.getName());
        holder.txtRepoDescr.setText(githubRepo.getDescription());
        holder.txtRepoStars.setText(String.valueOf(githubRepo.getStargazersCount()));
        holder.txtRepoPRs.setText(String.valueOf(githubRepo.getForksCount()));
        holder.txtRepoIssues.setText(String.valueOf(githubRepo.getOpenIssuesCount()));
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtItemRepoTitle)
        TextView txtRepoTitle;

        @BindView(R.id.txtItemRepoDescription)
        TextView txtRepoDescr;

        @BindView(R.id.txtItemRepoStars)
        TextView txtRepoStars;

        @BindView(R.id.txtItemRepoPRs)
        TextView txtRepoPRs;

        @BindView(R.id.txtItemRepoIssues)
        TextView txtRepoIssues;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

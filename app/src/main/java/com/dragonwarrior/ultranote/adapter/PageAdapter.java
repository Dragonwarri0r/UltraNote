package com.dragonwarrior.ultranote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dragonwarrior.ultranote.R;
import com.dragonwarrior.ultranote.db.Page;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

    private List<Page> mPageList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.page_select_item);
        }
    }

    public PageAdapter(List<Page> pageList){
        mPageList = pageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Page page = mPageList.get(position);
        holder.textView.setText(page.getPageName());
        holder.textView.setBackgroundColor(page.getPageColor());
    }

    @Override
    public int getItemCount() {
        return mPageList.size();
    }
}

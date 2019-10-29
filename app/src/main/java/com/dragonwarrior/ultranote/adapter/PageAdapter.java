package com.dragonwarrior.ultranote.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dragonwarrior.ultranote.FlushActivity;
import com.dragonwarrior.ultranote.MainActivity;
import com.dragonwarrior.ultranote.MyApplication;
import com.dragonwarrior.ultranote.R;
import com.dragonwarrior.ultranote.SavePageNum;
import com.dragonwarrior.ultranote.db.Page;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

    private List<Page> mPageList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View pageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            pageView = view;
            textView = view.findViewById(R.id.page_select_item);
        }
    }

    public PageAdapter(List<Page> pageList){
        mPageList = pageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item,parent,false);
        final ViewHolder holder =new ViewHolder(view);
        //注册长按点击事件，长按设置为删除
        holder.pageView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                final int position = holder.getAdapterPosition();
                final Page page = mPageList.get(position);
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(parent.getContext());
                deleteDialog.setTitle("note:").setMessage("Sure to delete?");
                deleteDialog.setPositiveButton("sure",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataSupport.delete(Page.class,page.getId());
                                Toast.makeText(parent.getContext(),page.getPageName()+"删除成功!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(parent.getContext(), FlushActivity.class);
                                parent.getContext().startActivity(intent);
                            }
                        });
                deleteDialog.setNegativeButton("cancel",null);
                deleteDialog.create();
                deleteDialog.show();
                return true;
            }
        });
        //默认点击事件
        holder.pageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Page page = mPageList.get(position);
                Intent intent = new Intent(parent.getContext(), SavePageNum.class);
                Bundle bundle = new Bundle();
                bundle.putLong("Pagenum",page.getId());
                intent.putExtras(bundle);
                parent.getContext().startActivity(intent);
                Toast.makeText(view.getContext(),page.getPageName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Page page = mPageList.get(position);
        holder.textView.setText(page.getPageName());
        holder.textView.setBackgroundResource(R.drawable.page_selector);
    }

    @Override
    public int getItemCount() {
        return mPageList.size();
    }
}

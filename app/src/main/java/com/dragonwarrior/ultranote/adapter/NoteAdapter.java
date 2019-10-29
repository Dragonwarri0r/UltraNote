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
import com.dragonwarrior.ultranote.R;
import com.dragonwarrior.ultranote.SavePageNum;
import com.dragonwarrior.ultranote.db.Note;
import com.dragonwarrior.ultranote.db.Page;

import org.litepal.crud.DataSupport;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> mNoteList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View noteview;
        TextView title;
        TextView body;

        public ViewHolder(View view){
            super(view);
            noteview = view;
            title = view.findViewById(R.id.note_name);
            body = view.findViewById(R.id.note_body);
        }
    }

    public NoteAdapter(List<Note> noteList){
        mNoteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        final NoteAdapter.ViewHolder holder =new NoteAdapter.ViewHolder(view);
        //注册长按点击事件，长按设置为删除
        holder.noteview.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                final int position = holder.getAdapterPosition();
                final Note note = mNoteList.get(position);
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(parent.getContext());
                deleteDialog.setTitle("note:").setMessage("Sure to delete "+note.getNoteTitle()+"?");
                deleteDialog.setPositiveButton("sure",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataSupport.delete(Note.class,note.getId());
                                Toast.makeText(parent.getContext(),note.getNoteTitle()+"删除成功!",Toast.LENGTH_SHORT).show();
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
        holder.noteview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Note note = mNoteList.get(position);
                Toast.makeText(view.getContext(),note.getNoteBody(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.title.setText(note.getNoteTitle());
        holder.body.setText(note.getNoteBody());
        holder.noteview.setBackgroundResource(R.drawable.btn_normal);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}

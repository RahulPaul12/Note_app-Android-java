package com.example.notebook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapterClass extends RecyclerView.Adapter<NoteAdapterClass.ViewHolder>{

    List<NoteClass> note;
    Context context;
    DatabaseHelper databaseHelper;

    public NoteAdapterClass(List<NoteClass> note, Context context) {
        this.note = note;
        this.context= context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final NoteClass noteClass = note.get(position);
        holder.textViewID.setText(Integer.toString(noteClass.getId()));
        holder.editText_name.setText(noteClass.getDate());
        holder.editText_Email.setText(noteClass.getNote());
        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String stringDate = holder.editText_name.getText().toString();
               String stringNote = holder.editText_Email.getText().toString();
               databaseHelper.updateNote(new NoteClass(noteClass.getId(), stringDate,stringNote));
               notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              databaseHelper.deleteNote(noteClass.getId());
              note.remove(position);
              notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_name;
        EditText editText_Email;
        Button button_Edit;
        Button button_delete;
        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_id);
            editText_name = itemView.findViewById(R.id.edittext_name);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);


        }
    }
}

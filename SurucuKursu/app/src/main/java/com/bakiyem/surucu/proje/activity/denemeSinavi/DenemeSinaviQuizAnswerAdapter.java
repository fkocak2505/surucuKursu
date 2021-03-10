package com.bakiyem.surucu.proje.activity.denemeSinavi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bakiyem.surucu.proje.R;
import com.bakiyem.surucu.proje.model.denemeSinavi.AnswerModel;

import java.util.List;

public class DenemeSinaviQuizAnswerAdapter extends RecyclerView.Adapter<DenemeSinaviQuizAnswerAdapter.ViewHolder> {

    private List<AnswerModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    DenemeSinaviQuizAnswerAdapter(Context context, List<AnswerModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_quiz_answer_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.questionNumber.setText(String.valueOf(mData.get(position).getQuestionNumber()));
        holder.answer.setText(mData.get(position).getQuestionAnswe());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView answer;
        TextView questionNumber;

        ViewHolder(View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.tv_answer);
            questionNumber = itemView.findViewById(R.id.tv_questionNumber);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    AnswerModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}